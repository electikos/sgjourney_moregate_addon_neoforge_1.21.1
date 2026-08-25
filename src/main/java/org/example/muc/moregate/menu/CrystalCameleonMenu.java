package org.example.muc.moregate.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.povstalec.sgjourney.common.init.BlockInit;
import net.povstalec.sgjourney.common.init.MenuInit;
import net.povstalec.sgjourney.common.menu.DHDCrystalMenu;
import org.example.muc.moregate.block.ModBlocks;
import org.example.muc.moregate.blockEntity.CameleonDHDBlockEntity;
import org.jetbrains.annotations.NotNull;

public class CrystalCameleonMenu extends DHDCrystalMenu<CameleonDHDBlockEntity> {
    public CrystalCameleonMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData)
    {
        this(containerId, inventory, (CameleonDHDBlockEntity) inventory.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public CrystalCameleonMenu(int containerId, Inventory inventory, CameleonDHDBlockEntity blockEntity)
    {
        super(ModMenu.CAMELEON_DHD_CRYSTAL.get(), containerId, inventory, blockEntity);
        addSlot(new SlotItemHandler(blockEntity.variantCrystalHandler, 0, 44, 35));
    }

    @Override
    public boolean stillValid(@NotNull Player player)
    {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.CAMELEON_DHD.get());
    }
}

