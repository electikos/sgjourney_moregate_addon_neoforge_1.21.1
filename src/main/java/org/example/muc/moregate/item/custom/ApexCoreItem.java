package org.example.muc.moregate.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.energy.ComponentEnergyStorage;
import org.example.muc.moregate.Moregate;
import org.example.muc.moregate.component.ModDataComponent;
import org.example.muc.moregate.item.ModItems;

import java.util.List;

public class ApexCoreItem extends Item
{
    public ApexCoreItem(Properties properties)
    {
        super(properties);
    }
    @EventBusSubscriber(modid = Moregate.MODID, bus = EventBusSubscriber.Bus.MOD)
    public class MyEnergyItem extends Item {
        public MyEnergyItem(Properties props) {
            super(props);
        }

        @SubscribeEvent
        public static void registerCaps(RegisterCapabilitiesEvent event) {
            event.registerItem(
                    Capabilities.EnergyStorage.ITEM,
                    (stack, context) -> new ComponentEnergyStorage(stack, ModDataComponent.ENERGY.get(), 270000000, 1000000, 1000000),
                    ModItems.APEXCORE.get()
            );
        }
    }
    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        int energy = stack.getOrDefault(ModDataComponent.ENERGY.get(), 0);
        int maxEnergy = 270000000; // max capacity
        return Math.round(13.0F * (float)energy / (float)maxEnergy);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return 0xFF0000;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        int energy = stack.getOrDefault(ModDataComponent.ENERGY.get(), 0);

        // put "135.00 MfE / 270.00 MfE" in the tooltips
        String text = String.format("%.2f MfE / 270.00 MfE", energy / 1_000_000.0);

        tooltip.add(Component.literal(text).withStyle(ChatFormatting.AQUA));
    }
}
