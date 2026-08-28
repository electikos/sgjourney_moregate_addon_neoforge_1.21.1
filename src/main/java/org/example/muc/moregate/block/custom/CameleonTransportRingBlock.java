package org.example.muc.moregate.block.custom;

import com.mojang.serialization.MapCodec;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.povstalec.sgjourney.common.block_entities.tech.EnergyBlockEntity;
import net.povstalec.sgjourney.common.block_entities.transporter.AbstractTransportRingsEntity;
import net.povstalec.sgjourney.common.blocks.transporter.AbstractTransportRingsBlock;
import net.povstalec.sgjourney.common.config.CommonTransporterConfig;
import net.povstalec.sgjourney.common.init.ItemInit;
import net.povstalec.sgjourney.common.items.PowerCellItem;
import net.povstalec.sgjourney.common.items.crystals.EnergyCrystalItem;
import net.povstalec.sgjourney.common.misc.InventoryUtil;
import net.povstalec.sgjourney.common.misc.NetworkUtils;
import org.example.muc.moregate.TransporterRegister;
import org.example.muc.moregate.block.ModBlocks;
import org.example.muc.moregate.blockEntity.CameleonTransportRingBlockEntity;
import org.example.muc.moregate.blockEntity.ModBlockEntities;
import org.example.muc.moregate.menu.CameleonTransportMenu;
import org.example.muc.moregate.menu.ModMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CameleonTransportRingBlock extends AbstractTransportRingsBlock {
    public static final MapCodec<CameleonTransportRingBlock> CODEC = simpleCodec(CameleonTransportRingBlock::new);
    public CameleonTransportRingBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }

    @Override
    public void openMenu(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if(blockEntity instanceof CameleonTransportRingBlockEntity transportRings)
        {
            if(transportRings.hasPermissions(player, true))
            {
                MenuProvider containerProvider = new MenuProvider()
                {
                    @Override
                    public @NotNull Component getDisplayName()
                    {
                        return transportRings.hasCustomName() ? transportRings.getCustomName() : Component.translatable("screen.sgjourney.goauld_transport_rings");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity)
                    {
                        return new CameleonTransportMenu(windowId, playerInventory, transportRings);
                    }
                };
                NetworkUtils.openMenu((ServerPlayer) player, containerProvider, blockEntity.getBlockPos());
            }
        }
        else
            throw new IllegalStateException("Our named container provider is missing!");
    }


    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CameleonTransportRingBlockEntity(blockPos, blockState);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getNearestLookingVerticalDirection().getOpposite());
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return createTickerHelper(type, ModBlockEntities.CAMELEON_TRANSPORT_RING_BE.get(), AbstractTransportRingsEntity::tick);
    }

    public static ItemStack transportRingsItemSetup(HolderLookup.Provider registries)
    {
        ItemStack stack = new ItemStack(ModBlocks.CAMELEON_TRANSPORT_RING.get());
        CompoundTag blockEntityTag = new CompoundTag();

        blockEntityTag.putString("id", "moregate:cameleon_transport_ring");
        blockEntityTag.putLong(EnergyBlockEntity.ENERGY, CommonTransporterConfig.goauld_transport_rings_energy_capacity.get());

        CompoundTag crystalInventory = new CompoundTag();
        crystalInventory.putInt("Size", 9);
        crystalInventory.put("Items", setupCrystalInventory(registries));
        blockEntityTag.put(AbstractTransportRingsEntity.CRYSTAL_INVENTORY, crystalInventory);

        CompoundTag energyInventory = new CompoundTag();
        energyInventory.putInt("Size", 1);
        energyInventory.put("Items", setupEnergyInventory(registries));
        blockEntityTag.put(AbstractTransportRingsEntity.ENERGY_INVENTORY, energyInventory);

        stack.set(DataComponents.BLOCK_ENTITY_DATA, CustomData.of(blockEntityTag));

        return stack;
    }

    private static ListTag setupEnergyInventory(HolderLookup.Provider registries)
    {
        ListTag nbtTagList = new ListTag();

        ItemStack stack = PowerCellItem.liquidNaquadahSetup();
        nbtTagList.add(InventoryUtil.addItem(registries, 0, stack));

        return nbtTagList;
    }

    private static ListTag setupCrystalInventory(HolderLookup.Provider registries)
    {
        ListTag nbtTagList = new ListTag();

        nbtTagList.add(InventoryUtil.addItem(registries, 0, new ItemStack(ItemInit.MATERIALIZATION_CRYSTAL.get())));
        nbtTagList.add(InventoryUtil.addItem(registries, 1, EnergyCrystalItem.energySetup(ItemInit.ENERGY_CRYSTAL.get().getCapacity())));
        nbtTagList.add(InventoryUtil.addItem(registries, 2, new ItemStack(ItemInit.TRANSFER_CRYSTAL.get())));

        return nbtTagList;
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player){
        return CameleonTransportRingBlock.transportRingsItemSetup(level.registryAccess());
    }
}
