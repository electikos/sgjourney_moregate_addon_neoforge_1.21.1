package org.example.muc.moregate.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ChiselMenu extends AbstractContainerMenu {

    private final BlockPos cartridgePos;

    public ChiselMenu(int containerId, Inventory inventory, BlockPos cartridgePos) {
        super(ModMenu.CHISEL_MENU.get(), containerId);
        this.cartridgePos = cartridgePos;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public BlockPos getCartridgePos() {
        return cartridgePos;
    }
}
