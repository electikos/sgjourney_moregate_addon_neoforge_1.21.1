package org.example.muc.moregate.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.energy.ComponentEnergyStorage;
import org.example.muc.moregate.Moregate;
import org.example.muc.moregate.component.ModDataComponent;
import org.example.muc.moregate.item.ModItems;

import java.util.List;

@EventBusSubscriber(modid = Moregate.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ApexCoreItem extends Item {

    public static final int MAX_ENERGY = 270_000_000;

    public ApexCoreItem(Properties properties) {
        super(properties);
    }


    @SubscribeEvent
    public static void registerCaps(RegisterCapabilitiesEvent event) {
        event.registerItem(
                Capabilities.EnergyStorage.ITEM,
                (stack, context) -> new ComponentEnergyStorage(
                        stack,
                        ModDataComponent.ENERGY.get(),
                        MAX_ENERGY,
                        1_000_000,
                        1_000_000
                ),
                ModItems.APEXCORE.get()
        );
    }


    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        int energy = stack.getOrDefault(ModDataComponent.ENERGY.get(), 0);
        return Math.round(13.0F * (float) energy / (float) MAX_ENERGY);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return 0xFF0000;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        int energy = stack.getOrDefault(ModDataComponent.ENERGY.get(), 0);

        double currentMfE = energy / 1_000_000.0;
        double maxMfE = MAX_ENERGY / 1_000_000.0;

        String text = String.format("%.2f MfE / %.2f MfE", currentMfE, maxMfE);
        tooltip.add(Component.literal(text).withStyle(ChatFormatting.AQUA));

        if (energy >= 135_000_000) {
            tooltip.add(Component.translatable("moregate.tooltip.apex_core.energy_too_high").withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
    }
}