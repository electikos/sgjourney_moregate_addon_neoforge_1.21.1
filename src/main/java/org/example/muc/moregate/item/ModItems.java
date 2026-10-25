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
    public static final DeferredItem<Item> STARGATE_SHEILD_GRAY = ITEMS.register("stargate_sheild_gray", () ->
            new StargateIrisItem(new Item.Properties(), ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/stargate/iris/stargate_sheild_gray.png"), () -> 100000));
    public static final DeferredItem<Item> STARGATE_SHEILD_YELLOW = ITEMS.register("stargate_sheild_yellow", () ->
            new StargateIrisItem(new Item.Properties(), ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/stargate/iris/stargate_sheild_yellow.png"), () -> 100000));
    public static final DeferredItem<Item> STARGATE_SHEILD_ORANGE = ITEMS.register("stargate_sheild_orange", () ->
            new StargateIrisItem(new Item.Properties(), ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/stargate/iris/stargate_sheild_orange.png"), () -> 100000));
    public static final DeferredItem<Item> STARGATE_SHEILD_RED = ITEMS.register("stargate_sheild_red", () ->
            new StargateIrisItem(new Item.Properties(), ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/stargate/iris/stargate_sheild_red.png"), () -> 100000));
    public static final DeferredItem<Item> STARGATE_SHEILD_MAGENTA = ITEMS.register("stargate_sheild_magenta", () ->
            new StargateIrisItem(new Item.Properties(), ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/stargate/iris/stargate_sheild_magenta.png"), () -> 100000));
    public static final DeferredItem<Item> STARGATE_SHEILD_PURPLE = ITEMS.register("stargate_sheild_purple", () ->
            new StargateIrisItem(new Item.Properties(), ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/stargate/iris/stargate_sheild_purple.png"), () -> 100000));
    public static final DeferredItem<Item> STARGATE_SHEILD_BLUE = ITEMS.register("stargate_sheild_blue", () ->
            new StargateIrisItem(new Item.Properties(), ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/stargate/iris/stargate_sheild_blue.png"), () -> 100000));
    public static final DeferredItem<Item> STARGATE_SHEILD_LIGHT_BLUE = ITEMS.register("stargate_sheild_lblue", () ->
            new StargateIrisItem(new Item.Properties(), ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/stargate/iris/stargate_sheild_lblue.png"), () -> 100000));
    public static final DeferredItem<Item> STARGATE_SHEILD_CYAN = ITEMS.register("stargate_sheild_cyan", () ->
            new StargateIrisItem(new Item.Properties(), ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/stargate/iris/stargate_sheild_cyan.png"), () -> 100000));
    public static final DeferredItem<Item> STARGATE_SHEILD_LIME = ITEMS.register("stargate_sheild_lime", () ->
            new StargateIrisItem(new Item.Properties(), ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/stargate/iris/stargate_sheild_lime.png"), () -> 100000));
    public static final DeferredItem<Item> STARGATE_SHEILD_GREEN = ITEMS.register("stargate_sheild_green", () ->
            new StargateIrisItem(new Item.Properties(), ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/stargate/iris/stargate_sheild_green.png"), () -> 100000));
    public static final DeferredItem<Item> STARGATE_SHEILD_BLACK = ITEMS.register("stargate_sheild_black", () ->
            new StargateIrisItem(new Item.Properties(), ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/stargate/iris/stargate_sheild_black.png"), () -> 100000));
    public static final DeferredItem<Item> STARGATE_SHEILD_BROWN = ITEMS.register("stargate_sheild_brown", () ->
            new StargateIrisItem(new Item.Properties(), ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/stargate/iris/stargate_sheild_brown.png"), () -> 100000));
    public static final DeferredItem<Item> STARGATE_SHEILD_PINK = ITEMS.register("stargate_sheild_pink", () ->
            new StargateIrisItem(new Item.Properties(), ResourceLocation.fromNamespaceAndPath("moregate", "textures/entity/stargate/iris/stargate_sheild_ugly.png"), () -> 100000));
    /*public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().stacksTo(1))); */

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}