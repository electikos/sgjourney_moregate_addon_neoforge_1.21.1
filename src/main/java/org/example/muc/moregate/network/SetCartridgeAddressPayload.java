package org.example.muc.moregate.network;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.povstalec.sgjourney.common.block_entities.CartoucheEntity;
import net.povstalec.sgjourney.common.blocks.CartoucheBlock;
import net.povstalec.sgjourney.common.blockstates.Orientation;
import net.povstalec.sgjourney.common.sgjourney.Address;

import java.util.Optional;

public record SetCartridgeAddressPayload(
        BlockPos pos,
        int[] address
) implements CustomPacketPayload {

    public static final Type<SetCartridgeAddressPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("moregate", "set_cartridge_address"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SetCartridgeAddressPayload> STREAM_CODEC =
            StreamCodec.of(
                    (buf, payload) -> {
                        buf.writeBlockPos(payload.pos());

                        buf.writeVarInt(payload.address().length);

                        for (int value : payload.address()) {
                            buf.writeVarInt(value);
                        }
                    },
                    buf -> {
                        BlockPos pos = buf.readBlockPos();

                        int length = buf.readVarInt();
                        int[] address = new int[length];

                        for (int i = 0; i < length; i++) {
                            address[i] = buf.readVarInt();
                        }

                        return new SetCartridgeAddressPayload(pos, address);
                    }
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(SetCartridgeAddressPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {

            ServerPlayer player = (ServerPlayer) context.player();
            ServerLevel level = player.serverLevel();

            BlockPos pos = payload.pos();
            BlockEntity blockEntity = level.getBlockEntity(pos);

            if (blockEntity instanceof CartoucheEntity cartouche) {

                if (cartouche.getHalf() == DoubleBlockHalf.UPPER) {
                    Direction direction = cartouche.getBlockState().getValue(CartoucheBlock.FACING);
                    Orientation orientation = cartouche.getBlockState().getValue(CartoucheBlock.ORIENTATION);

                    pos = pos.relative(
                            Orientation.getMultiDirection(
                                    direction,
                                    Direction.DOWN,
                                    orientation
                            )
                    );

                    blockEntity = level.getBlockEntity(pos);
                }

                if (blockEntity instanceof CartoucheEntity lowerCartouche) {
                    Address.Immutable address = new Address.Immutable(payload.address());

                    cartouche.setAddress(address);
                    cartouche.setChanged();

                    lowerCartouche.setAddress(address);
                    lowerCartouche.setChanged();
                }
            }
        });
    }
}