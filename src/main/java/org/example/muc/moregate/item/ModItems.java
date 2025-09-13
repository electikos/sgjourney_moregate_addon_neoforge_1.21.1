package org.example.muc.moregate.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.example.muc.moregate.Moregate;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Moregate.MODID);


    public static final DeferredItem<Item> APEXCORE = ITEMS.register("apex_core",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}