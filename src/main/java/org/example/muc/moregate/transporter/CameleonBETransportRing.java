package org.example.muc.moregate.transporter;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.povstalec.sgjourney.common.block_entities.transporter.AbstractTransporterEntity;
import net.povstalec.sgjourney.common.block_entities.transporter.AncientTransportRingsEntity;
import net.povstalec.sgjourney.common.misc.Conversion;
import net.povstalec.sgjourney.common.sgjourney.TransporterID;
import net.povstalec.sgjourney.common.sgjourney.transporter.BlockEntityTransportRings;
import net.povstalec.sgjourney.common.sgjourney.transporter.GoauldTransportRings;
import net.povstalec.sgjourney.common.sgjourney.transporter.TransporterType;
import org.example.muc.moregate.blockEntity.CameleonTransportRingBlockEntity;

import javax.annotation.Nullable;

public class CameleonBETransportRing extends GoauldTransportRings implements BlockEntityTransportRings<CameleonTransportRingBlockEntity> {
    protected BlockPos blockPos;

    public CameleonBETransportRing(TransporterType<?> type, MinecraftServer server)
    {
        super(type, server);
    }

    @Override
    public BlockPos getBlockPos()
    {
        return this.blockPos;
    }

    @Override
    public void loadFromBlockEntity(AbstractTransporterEntity<?> transporterEntity)
    {
        System.out.println(
                "MOREGATE: loading transporter " +
                        transporterEntity.getBlockPos()
        );

        this.transporterID = transporterEntity.getID();

        this.dimension = transporterEntity.getLevel().dimension();
        this.blockPos = transporterEntity.getBlockPos();

        this.name = transporterEntity.getCustomName();

        this.hasNetworkRestrictions = transporterEntity.hasNetworkRestrictions();
        this.networks = transporterEntity.getNetworks();

        this.transferEfficiency = transporterEntity.getTransferEfficiency();

        this.allowInterdimensionalTransport = transporterEntity.allowInterdimensionalTransport();
    }

    @Nullable
    public CameleonTransportRingBlockEntity getTransporterEntity(MinecraftServer server)
    {
        ServerLevel level = server.getLevel(dimension);

        if(level != null && level.getBlockEntity(blockPos) instanceof CameleonTransportRingBlockEntity transporter)
            return transporter;

        return null;
    }

    @Override
    public void update()
    {
        transporterRun(server, transporter ->
        {
            this.hasNetworkRestrictions = transporter.hasNetworkRestrictions();
            this.networks = transporter.getCachedNetworks();

            this.transferEfficiency = transporter.getTransferEfficiency();

            this.allowInterdimensionalTransport = transporter.allowInterdimensionalTransport();
        });
    }

    //============================================================================================
    //*************************************Saving and Loading*************************************
    //============================================================================================

    @Override
    public void serializeNBT(CompoundTag tag, HolderLookup.Provider registries)
    {
        tag.putIntArray(COORDINATES, Conversion.blockPosToIntArray(blockPos));

        super.serializeNBT(tag, registries);
    }

    public void deserializeNBT(TransporterID transporterID, CompoundTag tag, HolderLookup.Provider registries)
    {
        blockPos = Conversion.intArrayToBlockPos(tag.getIntArray(COORDINATES));

        super.deserializeNBT(transporterID, tag, registries);
    }
}
