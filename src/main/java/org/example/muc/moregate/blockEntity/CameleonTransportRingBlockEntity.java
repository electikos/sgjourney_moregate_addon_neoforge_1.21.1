package org.example.muc.moregate.blockEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.povstalec.sgjourney.common.block_entities.transporter.AbstractTransportRingsEntity;
import net.povstalec.sgjourney.common.config.CommonTransporterConfig;
import net.povstalec.sgjourney.common.sgjourney.transporter.GoauldBlockEntityTransportRings;
import net.povstalec.sgjourney.common.sgjourney.transporter.TransporterType;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.povstalec.sgjourney.client.SyncedConfig;
import net.povstalec.sgjourney.common.block_entities.transporter.AbstractTransportRingsEntity;
import net.povstalec.sgjourney.common.config.CommonTransporterConfig;
import net.povstalec.sgjourney.common.sgjourney.transporter.GoauldBlockEntityTransportRings;
import net.povstalec.sgjourney.common.sgjourney.transporter.TransporterType;
import org.example.muc.moregate.DHDVariant;
import org.example.muc.moregate.TransportRingVariant;
import org.example.muc.moregate.TransporterRegister;
import org.example.muc.moregate.component.ModDataComponent;
import org.example.muc.moregate.transporter.CameleonBETransportRing;

import java.io.IOException;

public class CameleonTransportRingBlockEntity extends AbstractTransportRingsEntity<CameleonBETransportRing> {
    public CameleonTransportRingBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CAMELEON_TRANSPORT_RING_BE.get(), TransporterRegister.CAMELEON_TRANSPORT_RINGS.get(), pos, state, 1);
    }
    private TransportRingVariant variant;

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.moregate.cameleon_transport_ring");
    }

    @Override
    public long getEnergyCapacity() {
        return 100000;
    }

    @Override
    public long getMaxEnergyReceive() {
        return CommonTransporterConfig.goauld_transport_rings_max_energy_receive.get();
    }

    @Override
    public void onLoad() {
        super.onLoad();

        if (level != null && !level.isClientSide) {
            if (!getID().isValid()) {
                addTransporterToNetwork();
            }
        }
        try {
            update();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() throws IOException {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
            ItemStack stack = variantCrystalHandler.getStackInSlot(0);
            if (!stack.isEmpty())
                this.variant = new TransportRingVariant(stack.get(ModDataComponent.TRASNPORT_RING_VARIANT), resourceManager);
            else this.variant = new TransportRingVariant(null, resourceManager);
        }
    }

    public TransportRingVariant getVariant(){
        return this.variant;
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

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);

        tag.put(
                "VariantCrystal",
                variantCrystalHandler.serializeNBT(registries)
        );

        return tag;
    }

    @Override
    public void handleUpdateTag(
            CompoundTag tag,
            HolderLookup.Provider registries) {

        super.handleUpdateTag(tag, registries);

        if (tag.contains("VariantCrystal")) {
            variantCrystalHandler.deserializeNBT(
                    registries,
                    tag.getCompound("VariantCrystal")
            );
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        tag.put("VariantCrystal",
                variantCrystalHandler.serializeNBT(registries));
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        if (tag.contains("VariantCrystal")) {
            variantCrystalHandler.deserializeNBT(
                    registries,
                    tag.getCompound("VariantCrystal")
            );
        }

        try {
            update();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
