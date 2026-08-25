package org.example.muc.moregate;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.povstalec.sgjourney.common.sgjourney.transporter.TransporterType;
import net.povstalec.sgjourney.common.init.TransporterInit;
import org.example.muc.moregate.transporter.CameleonBETransportRing;

public class TransporterRegister {

    public static final DeferredHolder<
            TransporterType<?>,
            TransporterType<CameleonBETransportRing>
            > CAMELEON_TRANSPORT_RINGS =
            TransporterInit.TRANSPORTER_TYPES.register(
                    "cameleon_transport_ring",
                    () -> new TransporterType<>(CameleonBETransportRing::new)
            );

    public static void init() {
        // force load hehe
    }

}