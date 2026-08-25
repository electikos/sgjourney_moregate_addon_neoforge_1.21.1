package org.example.muc.moregate.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.povstalec.sgjourney.common.block_entities.dhd.ClassicDHDEntity;
import net.povstalec.sgjourney.common.init.BlockInit;
import net.povstalec.sgjourney.common.init.MenuInit;
import net.povstalec.sgjourney.common.menu.AbstractDHDMenu;
import org.example.muc.moregate.DHDVariant;
import org.example.muc.moregate.block.ModBlocks;
import org.example.muc.moregate.blockEntity.CameleonDHDBlockEntity;

public class CameleonMenu extends AbstractDHDMenu<CameleonDHDBlockEntity> {
    private DHDVariant variant;
    public CameleonMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData)
    {
        this(containerId, inventory, (CameleonDHDBlockEntity) inventory.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public CameleonMenu(int containerId, Inventory inventory, CameleonDHDBlockEntity dhd)
    {
        super(ModMenu.CAMELEON_DHD.get(), containerId, inventory, dhd);
        this.variant = dhd.variant;
    }

    @Override
    public boolean stillValid(Player player)
    {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.CAMELEON_DHD.get());
    }
    public DHDVariant getVariant(){
        return this.variant;
    }
}
