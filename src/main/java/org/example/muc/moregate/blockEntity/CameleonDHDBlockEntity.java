package org.example.muc.moregate.blockEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.povstalec.sgjourney.client.SyncedConfig;
import net.povstalec.sgjourney.common.block_entities.StructureGenEntity;
import net.povstalec.sgjourney.common.block_entities.dhd.AbstractDHDEntity;
import net.povstalec.sgjourney.common.block_entities.dhd.CrystalDHDEntity;
import net.povstalec.sgjourney.common.block_entities.stargate.AbstractStargateEntity;
import net.povstalec.sgjourney.common.block_entities.tech.EnergyBlockEntity;
import net.povstalec.sgjourney.common.config.CommonDHDConfig;
import net.povstalec.sgjourney.common.config.CommonNaquadahGeneratorConfig;
import net.povstalec.sgjourney.common.init.ItemInit;
import net.povstalec.sgjourney.common.init.SoundInit;
import net.povstalec.sgjourney.common.items.NaquadahFuelRodItem;
import net.povstalec.sgjourney.common.misc.CoordinateHelper;
import net.povstalec.sgjourney.common.sgjourney.PointOfOrigin;
import net.povstalec.sgjourney.common.sgjourney.Symbols;
import org.example.muc.moregate.DHDVariant;
import org.example.muc.moregate.block.ModBlocks;
import org.example.muc.moregate.component.ModDataComponent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CameleonDHDBlockEntity extends CrystalDHDEntity {
    public CameleonDHDBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CAMELEON_DHD_BE.get(), pos, state);
    }

    public DHDVariant variant ;

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.loadAdditional(tag, registries);
        symbolInfo().loadFromCompoundTag(tag, POINT_OF_ORIGIN, SYMBOLS);
        if (tag.contains("VariantCrystal")) {variantCrystalHandler.deserializeNBT(registries, tag.getCompound("VariantCrystal"));}


    }
    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);

        tag.put("VariantCrystal", variantCrystalHandler.serializeNBT(registries));

        return tag;
    }
    @Override
    public void handleUpdateTag(
            CompoundTag tag,
            HolderLookup.Provider registries)
    {
        super.handleUpdateTag(tag, registries);

        if (tag.contains("VariantCrystal")) {
            variantCrystalHandler.deserializeNBT(
                    registries,
                    tag.getCompound("VariantCrystal")
            );

            try {
                update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet, HolderLookup.Provider registries)
    {
        CompoundTag tag = packet.getTag();
        if(tag != null)
            handleUpdateTag(tag, registries);
    }
    @Override
    public void onLoad() {

        super.onLoad();

        if (level != null && level.isClientSide) {
            try {
                update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void tick(Level level, BlockPos pos, BlockState state, AbstractDHDEntity dhd)
    {
        if(level.isClientSide())
            return;

        dhd.outputEnergy(null);

        if(level.getGameTime() % 20 == 0) dhd.stargateCache.markDirty();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.Provider registries)
    {
        super.saveAdditional(tag, registries);
        symbolInfo().saveToCompoundTag(tag, POINT_OF_ORIGIN, SYMBOLS);
        tag.put("VariantCrystal", variantCrystalHandler.serializeNBT(registries));
    }

    @Override
    protected long buttonPressEnergyCost() {
        return CommonDHDConfig.classic_dhd_button_press_energy_cost.get();
    }

    @Override
    public long maxEnergyTransfer() {
        return this.maxEnergyTransfer < 0 ? CommonDHDConfig.classic_dhd_max_energy_extract.get() : this.maxEnergyTransfer;
    }

    @Override
    protected SoundEvent getEnterSound() {
        return SoundInit.CLASSIC_DHD_ENTER.get();
    }

    @Override
    protected SoundEvent getPressSound() {
        return SoundInit.CLASSIC_DHD_PRESS.get();
    }

    @Override
    protected void generateEnergyCore() {
        energyItemHandler.setStackInSlot(0, new ItemStack(ItemInit.NAQUADAH_GENERATOR_CORE.get()));
        energyItemHandler.setStackInSlot(1, NaquadahFuelRodItem.randomFuelRod(CommonNaquadahGeneratorConfig.naquadah_rod_max_fuel.get() / 2, CommonNaquadahGeneratorConfig.naquadah_rod_max_fuel.get()));
    }

    @Override
    public long getEnergyCapacity() {
        return level != null && level.isClientSide() ? SyncedConfig.classicDHDEnergyCapacity : CommonDHDConfig.classic_dhd_energy_buffer_capacity.get();
    }
    private void update() throws IOException {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
            ItemStack stack = variantCrystalHandler.getStackInSlot(0);
            if (!stack.isEmpty()) variant = new DHDVariant(stack.get(ModDataComponent.DHD_VARIANT), resourceManager);
            else variant = new DHDVariant(null, resourceManager);
        }

    }

    @Override
    public long getMaxEnergyReceive() {
        return CommonDHDConfig.classic_dhd_max_energy_receive.get();
    }

    public final ItemStackHandler variantCrystalHandler =
            new ItemStackHandler(1) {

                @Override
                protected void onContentsChanged(int slot) {
                    setChanged();
                    if (level != null && level.isClientSide){
                    try {
                        update();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }}


                    if (level != null && !level.isClientSide) {
                        level.sendBlockUpdated(
                                worldPosition,
                                getBlockState(),
                                getBlockState(),
                                Block.UPDATE_CLIENTS
                        );
                    }
                }
            };


    //============================================================================================
    //*****************************************Generation*****************************************
    //============================================================================================

    @Override
    public void generateAdditional(StructureGenEntity.Step generationStep)
    {
        if(generationStep == StructureGenEntity.Step.SETUP) // Set empty symbols before it's generated in a structure
        {
            if(!PointOfOrigin.isValid(level.getServer(), symbolInfo().pointOfOrigin()))
                symbolInfo().setPointOfOrigin(null);

            if(!Symbols.isValid(level.getServer(), symbolInfo().symbols()))
                symbolInfo().setSymbols(null);
        }
        else if(stargateCache.isPresent()) // Copy from connected Stargate
            setSymbolsFromStargate();
        else // Generate from Dimension
            setLocalSymbols();

        crystalCache.recalculateCrystals();
    }

    @Override
    protected void generateCrystals()
    {
        crystalHandler.setStackInSlot(0, new ItemStack(ItemInit.LARGE_CONTROL_CRYSTAL.get()));
        crystalHandler.setStackInSlot(1, new ItemStack(ItemInit.ENERGY_CRYSTAL.get()));
        crystalHandler.setStackInSlot(2, new ItemStack(ItemInit.COMMUNICATION_CRYSTAL.get()));
        crystalHandler.setStackInSlot(3, new ItemStack(ItemInit.ENERGY_CRYSTAL.get()));
        crystalHandler.setStackInSlot(5, new ItemStack(ItemInit.ENERGY_CRYSTAL.get()));
        crystalHandler.setStackInSlot(7, new ItemStack(ItemInit.TRANSFER_CRYSTAL.get()));
    }



}
