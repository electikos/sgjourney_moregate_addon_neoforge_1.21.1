package org.example.muc.moregate;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.povstalec.sgjourney.common.init.TabInit;
import net.povstalec.sgjourney.common.init.TransporterInit;
import org.example.muc.moregate.block.ModBlocks;
import org.example.muc.moregate.blockEntity.ModBlockEntities;
import org.example.muc.moregate.component.ModDataComponent;
import org.example.muc.moregate.item.ModItems;
import org.example.muc.moregate.menu.ModMenu;
import org.example.muc.moregate.network.MoregateNetwork;
import org.example.muc.moregate.network.SetCartridgeAddressPayload;
import org.slf4j.Logger;

import java.util.Map;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Moregate.MODID)
public class Moregate {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "moregate";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();



    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Moregate(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        //Coucou à tout les français qui lisent ça.

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (Moregate) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModItems.register(modEventBus);
        ModDataComponent.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenu.register(modEventBus);
        TransporterRegister.init();
        modEventBus.addListener(MoregateNetwork::register);
        modEventBus.addListener(this::addCreative);


        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock) LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }


    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        MinecraftServer server = event.getServer();

    CreativeModeTabs.tryRebuildTabContents(server.getWorldData().enabledFeatures(), false, server.registryAccess());

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

        LOGGER.info("HELLO from server starting");
    }
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        /* if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.APEXCORE);
        } */
        if (event.getTabKey().equals(TabInit.STARGATE_ITEMS.getKey())){
            event.accept(ModItems.APEXCORE);
            event.accept(ModItems.CAMELEON_TRANSPORT_RING);
        }
        else if (event.getTabKey().equals(TabInit.STARGATE_STUFF.getKey())){

            event.accept(ModBlocks.CAMELEON_DHD);
            event.accept(ModItems.DHD_VARIANT_CRYSTAL);
            MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
            ResourceManager resourceManager = server.getResourceManager();
            if (resourceManager == null) {

                return;
            }

            Map<ResourceLocation, Resource> resources =
                    resourceManager.listResources(
                            "moregate/dhd",
                            path -> path.getPath().endsWith(".json")
                    );


            for (ResourceLocation location : resources.keySet()) {
                String namespace = location.getNamespace();
                String path = location.getPath();

                String variant = path.substring("moregate/dhd/".length()).replace(".json", "");

                ResourceLocation variantId = ResourceLocation.fromNamespaceAndPath(namespace, variant);
                ItemStack stack = new ItemStack(ModItems.DHD_VARIANT_CRYSTAL.get());

                stack.set(ModDataComponent.DHD_VARIANT, variantId.toString());

                event.accept(stack);
            }

            event.accept(ModBlocks.CAMELEON_TRANSPORT_RING);
            event.accept(ModItems.TRANSPORT_RING_VARIANT_CRYSTAL);

            Map<ResourceLocation, Resource> resource =
                    resourceManager.listResources(
                            "moregate/ring",
                            path -> path.getPath().endsWith(".json")
                    );

            for (ResourceLocation location : resource.keySet()) {
                String namespace = location.getNamespace();
                String path = location.getPath();

                String variant = path.substring("moregate/ring/".length()).replace(".json", "");

                ResourceLocation variantId = ResourceLocation.fromNamespaceAndPath(namespace, variant);
                ItemStack stack = new ItemStack(ModItems.TRANSPORT_RING_VARIANT_CRYSTAL.get());

                stack.set(ModDataComponent.TRASNPORT_RING_VARIANT, variantId.toString());

                event.accept(stack);
            }

            event.accept(ModItems.STARGATE_SHEILD);
        }

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
    public class MoregateNetwork {

        public static void register(RegisterPayloadHandlersEvent event) {

            PayloadRegistrar registrar =
                    event.registrar("moregate");

            registrar.playToServer(
                    SetCartridgeAddressPayload.TYPE,
                    SetCartridgeAddressPayload.STREAM_CODEC,
                    SetCartridgeAddressPayload::handle
            );
        }
    }
}
