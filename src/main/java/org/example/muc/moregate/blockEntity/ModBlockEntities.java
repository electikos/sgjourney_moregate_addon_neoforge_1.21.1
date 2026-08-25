package org.example.muc.moregate.blockEntity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.example.muc.moregate.Moregate;
import org.example.muc.moregate.block.ModBlocks;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Moregate.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CameleonDHDBlockEntity>> CAMELEON_DHD_BE = BLOCK_ENTITIES.register("cameleon_dhd",
            () -> BlockEntityType.Builder.of(CameleonDHDBlockEntity::new, ModBlocks.CAMELEON_DHD.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CameleonTransportRingBlockEntity>> CAMELEON_TRANSPORT_RING_BE = BLOCK_ENTITIES.register("cameleon_transport_ring",
            () -> BlockEntityType.Builder.of(CameleonTransportRingBlockEntity::new, ModBlocks.CAMELEON_TRANSPORT_RING.get()).build(null));

    public static void register(IEventBus event){
        BLOCK_ENTITIES.register(event);
    }
}
