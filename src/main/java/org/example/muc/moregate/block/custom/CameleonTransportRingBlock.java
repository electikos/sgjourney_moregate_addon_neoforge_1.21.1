package org.example.muc.moregate.block.custom;

import com.mojang.serialization.MapCodec;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.povstalec.sgjourney.common.block_entities.transporter.AbstractTransportRingsEntity;
import net.povstalec.sgjourney.common.blocks.transporter.AbstractTransportRingsBlock;
import net.povstalec.sgjourney.common.misc.NetworkUtils;
import org.example.muc.moregate.TransporterRegister;
import org.example.muc.moregate.blockEntity.CameleonTransportRingBlockEntity;
import org.example.muc.moregate.blockEntity.ModBlockEntities;
import org.example.muc.moregate.menu.CameleonTransportMenu;
import org.example.muc.moregate.menu.ModMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CameleonTransportRingBlock extends AbstractTransportRingsBlock {
    public static final MapCodec<CameleonTransportRingBlock> CODEC = simpleCodec(CameleonTransportRingBlock::new);
    public CameleonTransportRingBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }

    @Override
    public void openMenu(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if(blockEntity instanceof CameleonTransportRingBlockEntity transportRings)
        {
            if(transportRings.hasPermissions(player, true))
            {
                MenuProvider containerProvider = new MenuProvider()
                {
                    @Override
                    public @NotNull Component getDisplayName()
                    {
                        return transportRings.hasCustomName() ? transportRings.getCustomName() : Component.translatable("screen.sgjourney.goauld_transport_rings");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity)
                    {
                        return new CameleonTransportMenu(windowId, playerInventory, transportRings);
                    }
                };
                NetworkUtils.openMenu((ServerPlayer) player, containerProvider, blockEntity.getBlockPos());
            }
        }
        else
            throw new IllegalStateException("Our named container provider is missing!");
    }


    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CameleonTransportRingBlockEntity(blockPos, blockState);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getNearestLookingVerticalDirection().getOpposite());
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return createTickerHelper(type, ModBlockEntities.CAMELEON_TRANSPORT_RING_BE.get(), AbstractTransportRingsEntity::tick);
    }
}
