package org.example.muc.moregate.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.example.muc.moregate.Moregate;
import org.example.muc.moregate.block.custom.CameleonDHDBlock;
import org.example.muc.moregate.block.custom.CameleonTransportRingBlock;
import org.example.muc.moregate.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Moregate.MODID);

    public static DeferredBlock<Block> CAMELEON_DHD = registerBlock("cameleon_dhd",
            () -> new CameleonDHDBlock(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(5.0F, 6.0F)));
    public static DeferredBlock<Block> CAMELEON_TRANSPORT_RING = registerBlock("cameleon_transport_ring",
            () -> new CameleonTransportRingBlock(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.METAL).strength(5.0F, 6.0F)));

    public static void register(IEventBus eventBus){BLOCKS.register(eventBus);


    }
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

}
