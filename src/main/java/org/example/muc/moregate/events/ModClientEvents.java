package org.example.muc.moregate.events;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.povstalec.sgjourney.StargateJourney;
import org.example.muc.moregate.Moregate;
import org.example.muc.moregate.blockEntity.ModBlockEntities;
import org.example.muc.moregate.blockEntity.renderer.*;
import org.example.muc.moregate.screen.CameleonTransportSreen;

@EventBusSubscriber(modid = Moregate.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {

        event.registerLayerDefinition(defaultModel.LAYER_LOCATION, defaultModel::createBodyLayer);
        event.registerLayerDefinition(CameleonDHDHorned.LAYER_LOCATION, CameleonDHDHorned::createBodyLayer);
        event.registerLayerDefinition(CameleonTransportRingModel.LAYER_LOCATION, CameleonTransportRingModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {

        event.registerBlockEntityRenderer(ModBlockEntities.CAMELEON_DHD_BE.get(), CameleonDHDBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CAMELEON_TRANSPORT_RING_BE.get(), context -> new CameleonTransportRingRenderer(StargateJourney.sgjourneyLocation("textures/entity/transport_rings/goauld_transport_rings.png"), context));
    }
}
