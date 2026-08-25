package org.example.muc.moregate.screen;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import org.example.muc.moregate.Moregate;
import org.example.muc.moregate.menu.ModMenu;

@EventBusSubscriber(modid = Moregate.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ModScreen {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(
                ModMenu.CAMELEON_DHD.get(),
                CameleonDHDScreen::new
        );

        event.register(
                ModMenu.CAMELEON_DHD_CRYSTAL.get(),
                CrystalCameleonDHDScreen::new
        );

        event.register(
                ModMenu.CAMELEON_TRANSPORT_MENU.get(),
                CameleonTransportSreen::new
        );
    }
}