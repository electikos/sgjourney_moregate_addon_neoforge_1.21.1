package org.example.muc.moregate.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.povstalec.sgjourney.common.block_entities.transporter.AbstractTransportRingsEntity;
import net.povstalec.sgjourney.common.menu.TransportRingsMenu;
import org.example.muc.moregate.block.ModBlocks;
import org.example.muc.moregate.blockEntity.CameleonTransportRingBlockEntity;

public class CameleonTransportMenu extends TransportRingsMenu {
    public CameleonTransportRingBlockEntity entity;
    public CameleonTransportMenu(int containerId, Inventory inventory, FriendlyByteBuf data) {
        this(containerId, inventory, (CameleonTransportRingBlockEntity) inventory.player.level().getBlockEntity(data.readBlockPos()));
    }

    public CameleonTransportMenu(int containerId, Inventory inventory, CameleonTransportRingBlockEntity blockEntity) {
        super(ModMenu.CAMELEON_TRANSPORT_MENU.get(), containerId, inventory, blockEntity);
        addSlot(new SlotItemHandler(blockEntity.variantCrystalHandler, 0, 44, 35));
        this.entity = blockEntity;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.CAMELEON_TRANSPORT_RING.get());
    }

    public long getMaxEnergy() {
        return this.entity.getEnergyCapacity();
    }
}
