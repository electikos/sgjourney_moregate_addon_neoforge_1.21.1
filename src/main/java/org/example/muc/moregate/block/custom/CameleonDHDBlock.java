package org.example.muc.moregate.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.povstalec.sgjourney.common.block_entities.dhd.AbstractDHDEntity;
import net.povstalec.sgjourney.common.blocks.dhd.CrystalDHDBlock;
import net.povstalec.sgjourney.common.menu.ClassicDHDMenu;
import net.povstalec.sgjourney.common.menu.DHDCrystalMenu;
import net.povstalec.sgjourney.common.misc.NetworkUtils;
import org.example.muc.moregate.block.ModBlocks;
import org.example.muc.moregate.blockEntity.CameleonDHDBlockEntity;
import org.example.muc.moregate.blockEntity.ModBlockEntities;
import org.example.muc.moregate.menu.CameleonMenu;
import org.example.muc.moregate.menu.CrystalCameleonMenu;
import org.jetbrains.annotations.Nullable;

import java.util.Properties;

public class CameleonDHDBlock extends CrystalDHDBlock {
    public CameleonDHDBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    public static final MapCodec<CameleonDHDBlock> CODEC = simpleCodec(CameleonDHDBlock::new);
    @Override
    protected MapCodec<CameleonDHDBlock> codec() {
        return CODEC;
    }

    @Override
    protected void use(Level level, BlockPos pos, Player player, BlockHitResult hitResult)
    {
        if(level.isClientSide())
            return;

        BlockEntity blockEntity = level.getBlockEntity(pos);

        if(blockEntity instanceof CameleonDHDBlockEntity dhd)
        {
            if((hitResult.getDirection() != Direction.UP || player.isShiftKeyDown()) && dhd.hasPermissions(player, true))
            {

                MenuProvider containerProvider = new MenuProvider()
                {
                    @Override
                    public Component getDisplayName()
                    {
                        return Component.translatable("screen.sgjourney.dhd");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity)
                    {
                        return new CrystalCameleonMenu(windowId, playerInventory, dhd);
                    }
                };
                NetworkUtils.openMenu((ServerPlayer) player, containerProvider, dhd.getBlockPos());
            }
            else
            {
                MenuProvider containerProvider = new MenuProvider()
                {
                    @Override
                    public Component getDisplayName()
                    {
                        return Component.translatable("screen.sgjourney.dhd");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity)
                    {
                        return new CameleonMenu(windowId, playerInventory, dhd);
                    }
                };
                NetworkUtils.openMenu((ServerPlayer) player, containerProvider, dhd.getBlockPos());
            }
        }
        else
            throw new IllegalStateException("Our named container provider is missing!");
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult)
    {
        if(level.isClientSide())
            return InteractionResult.SUCCESS;
        use(level, pos, player, hitResult);
        return InteractionResult.SUCCESS;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        if(level.isClientSide())
            return ItemInteractionResult.SUCCESS;
        use(level, pos, player, hitResult);
        return ItemInteractionResult.SUCCESS;
    }

    @Override
    public Block getDHD() {
        return ModBlocks.CAMELEON_DHD.get();
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CameleonDHDBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return createTickerHelper(type, ModBlockEntities.CAMELEON_DHD_BE.get(), AbstractDHDEntity::tick);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
