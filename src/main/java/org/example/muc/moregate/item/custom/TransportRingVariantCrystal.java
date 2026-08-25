package org.example.muc.moregate.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.example.muc.moregate.component.ModDataComponent;

import java.util.List;

public class TransportRingVariantCrystal extends Item{

    public TransportRingVariantCrystal(Item.Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack)
    {
        return (stack.has(ModDataComponent.TRASNPORT_RING_VARIANT) && stack.get(ModDataComponent.TRASNPORT_RING_VARIANT) != "");
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag){
        if(stack.has(ModDataComponent.TRASNPORT_RING_VARIANT)) {tooltipComponents.add(Component.literal(stack.get(ModDataComponent.TRASNPORT_RING_VARIANT)).withStyle(ChatFormatting.GREEN));}
        else tooltipComponents.add(Component.literal(""));
    }
}
