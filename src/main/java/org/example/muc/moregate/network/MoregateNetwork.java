package org.example.muc.moregate.network;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.povstalec.sgjourney.common.block_entities.CartoucheEntity;
import net.povstalec.sgjourney.common.sgjourney.Address;
import net.povstalec.sgjourney.common.sgjourney.Galaxy;

import java.util.Optional;

public class MoregateNetwork {

    public static void register(RegisterPayloadHandlersEvent event) {

        PayloadRegistrar registrar =
                event.registrar("1");

        registrar.playToServer(
                SetCartridgeAddressPayload.TYPE,
                SetCartridgeAddressPayload.STREAM_CODEC,
                (payload, context) -> {
                    context.enqueueWork(() -> {

                        var player = context.player();

                        if (player == null)
                            return;

                        var level = player.level();

                        if (!(level.getBlockEntity(payload.pos())
                                instanceof CartoucheEntity cartouche))
                            return;

                        Address.Dimension address = new Address.Dimension(level.dimension(), Optional.empty(), payload.address());
                        cartouche.setAddress(address);

                        cartouche.setChanged();
                    });
                }
        );
    }
}