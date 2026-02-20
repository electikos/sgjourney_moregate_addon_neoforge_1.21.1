package org.example.muc.moregate.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.example.muc.moregate.Moregate;
import org.example.muc.moregate.component.ModDataComponent;
import org.example.muc.moregate.item.custom.ApexCoreItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Moregate.MODID);


    public static final DeferredItem<Item> APEXCORE = ITEMS.register("apex_core",
            () -> new ApexCoreItem(new Item.Properties().stacksTo(1).component(ModDataComponent.ENERGY.get(), 0)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}