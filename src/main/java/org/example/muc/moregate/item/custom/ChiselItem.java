package org.example.muc.moregate.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.povstalec.sgjourney.common.block_entities.CartoucheEntity;
import org.example.muc.moregate.menu.ChiselMenu;

public class ChiselItem extends Item {
    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        if (!level.isClientSide) {
            if (level.getBlockEntity(pos) instanceof CartoucheEntity cartouche) {
                ServerPlayer player = (ServerPlayer) context.getPlayer();

                MenuProvider provider = new SimpleMenuProvider((containerId, inventory, p)
                        -> new ChiselMenu(containerId, inventory, pos), Component.literal("Chisel"));

                player.openMenu(provider, buffer -> {
                    buffer.writeBlockPos(pos);
                });
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
