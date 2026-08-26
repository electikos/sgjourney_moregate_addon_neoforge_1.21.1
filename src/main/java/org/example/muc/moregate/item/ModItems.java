package org.example.muc.moregate.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.povstalec.sgjourney.common.items.StargateIrisItem;
import org.example.muc.moregate.Moregate;
import org.example.muc.moregate.component.ModDataComponent;
import org.example.muc.moregate.item.custom.ApexCoreItem;
import org.example.muc.moregate.item.custom.ChiselItem;
import org.example.muc.moregate.item.custom.DHDVariantCrystal;
import org.example.muc.moregate.item.custom.TransportRingVariantCrystal;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Moregate.MODID);


    public static final DeferredItem<Item> APEXCORE = ITEMS.register("apex_core",
            () -> new ApexCoreItem(new Item.Properties().stacksTo(1).component(ModDataComponent.ENERGY.get(), 0)));
    public static final DeferredItem<Item> DHD_VARIANT_CRYSTAL = ITEMS.register("dhd_variant_crystal",
            () -> new DHDVariantCrystal(new Item.Properties().stacksTo(1).component(ModDataComponent.DHD_VARIANT.get(), "")));
    public static final DeferredItem<Item> TRANSPORT_RING_VARIANT_CRYSTAL = ITEMS.register("transport_ring_variant_crystal",
            () -> new TransportRingVariantCrystal(new Item.Properties().stacksTo(1).component(ModDataComponent.TRASNPORT_RING_VARIANT.get(), "")));
    public static final DeferredItem<Item> CAMELEON_TRANSPORT_RING = ITEMS.register("cameleon_transport_ring_item",
            () -> new Item(new Item.Properties().stacksTo(64)));
    public static final DeferredItem<Item> STARGATE_SHEILD = ITEMS.register("stargate_sheild", () ->
            new StargateIrisItem(new Item.Properties(), ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/stargate/iris/stargate_sheild.png"), () -> 100000));
    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}