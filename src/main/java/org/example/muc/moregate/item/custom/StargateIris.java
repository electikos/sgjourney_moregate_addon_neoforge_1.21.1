package org.example.muc.moregate.item.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.povstalec.sgjourney.common.init.DataComponentInit;

public class StargateIris extends Item {
    private final int durability;
    private final ResourceLocation irisTexture;

    public StargateIris(Properties properties, int durability, ResourceLocation irisTexture) {
        super(properties);
        this.durability = durability;
        this.irisTexture = irisTexture;
    }

    public int getDurability() {
        return durability;
    }

    public ResourceLocation getIrisTexture() {
        return irisTexture;
    }

    public static boolean hasCustomTexture(ItemStack stack)
    {
        return stack.has(DataComponentInit.IRIS_TEXTURE);
    }
}
