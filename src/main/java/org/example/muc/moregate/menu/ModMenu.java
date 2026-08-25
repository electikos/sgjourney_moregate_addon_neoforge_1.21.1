package org.example.muc.moregate.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.example.muc.moregate.Moregate;
import org.example.muc.moregate.blockEntity.CameleonTransportRingBlockEntity;

public class ModMenu {
    public static DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(Registries.MENU, Moregate.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<CameleonMenu>> CAMELEON_DHD =
            registerMenuType(CameleonMenu::new, "cameleon_dhd");
    public static final DeferredHolder<MenuType<?>, MenuType<CrystalCameleonMenu>> CAMELEON_DHD_CRYSTAL =
            registerMenuType(CrystalCameleonMenu::new, "cameleon_dhd_crystal");
    public static final DeferredHolder<MenuType<?>, MenuType<CameleonTransportMenu>> CAMELEON_TRANSPORT_MENU = registerMenuType(CameleonTransportMenu::new, "cameleon_transport_ring");

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name)
    {
        return CONTAINERS.register(name, () -> IMenuTypeExtension.create(factory));
    }


    public static void register(IEventBus eventBus)
    {
        CONTAINERS.register(eventBus);
    }
}
