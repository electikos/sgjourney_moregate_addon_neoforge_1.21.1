package org.example.muc.moregate.blockEntity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.AABB;
import net.povstalec.sgjourney.client.models.block_entity.TransportRingModel;
import net.povstalec.sgjourney.common.block_entities.transporter.AbstractTransportRingsEntity;
import org.example.muc.moregate.TransportRingVariant;
import org.example.muc.moregate.blockEntity.CameleonTransportRingBlockEntity;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CameleonTransportRingRenderer  implements BlockEntityRenderer<CameleonTransportRingBlockEntity> {

    protected final List<TransportRingModel<CameleonTransportRingBlockEntity>> transportRings = new ArrayList<>(5);

    private final BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

    private final CameleonTransportRingModel model;

    private Boolean ringModelConstructed;
    private TransportRingVariant actualVariant;
    private Boolean wasActive;

    public CameleonTransportRingRenderer(ResourceLocation texture, BlockEntityRendererProvider.Context context)
    {
        this.model = new CameleonTransportRingModel(context.bakeLayer(CameleonTransportRingModel.LAYER_LOCATION));
        this.ringModelConstructed = false;
        this.wasActive = false;
    }



    @Override
    public void render(CameleonTransportRingBlockEntity blockEntity, float partialTick, PoseStack stack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        TransportRingVariant variant = blockEntity.getVariant();

        if (variant == null)
            return;

        if (!this.ringModelConstructed || this.actualVariant != variant || blockEntity.isConnected() != this.wasActive){
            transportRings.clear();
            for(int i = 0; i < 5; i++)
            {
                if(!blockEntity.isConnected()) transportRings.add(new TransportRingModel<>(variant.getRingTexture(), 36, 5F / 16F, 2.498F, 2F));
                else transportRings.add(new TransportRingModel<>(variant.getRingActiveTexture(), 36, 5F / 16F, 2.498F, 2F));
            }
            this.ringModelConstructed = true;
            this.wasActive = !this.wasActive;
        }
        this.actualVariant = variant;

        stack.pushPose();
        stack.translate(0.5, 0.5, 0.5);

        VertexConsumer centralConsumer;

        if (!blockEntity.isConnected()) {
            centralConsumer = buffer.getBuffer(RenderType.entityCutout(blockEntity.getVariant().getTexture()));
        }
        else {
            centralConsumer = buffer.getBuffer(RenderType.entityCutout(blockEntity.getVariant().getActiveTexture()));
        }

        model.renderToBuffer(stack, centralConsumer, packedLight, packedOverlay, 0xFFFFFFFF);

        for(int i = 0; i < this.transportRings.size(); i++)
        {
            float ringHeight = blockEntity.getRingHeight(partialTick, i) / 16F;
            if(ringHeight == 0 && i != 4) // Don't render overlapping rings when Transport Rings are idle
                continue;

            mutablePos.set(blockEntity.getBlockPos().getX(), blockEntity.getBlockPos().getY() + Math.round(ringHeight), blockEntity.getBlockPos().getZ());
            int transportLight = blockEntity.getLevel() != null ? LevelRenderer.getLightColor(blockEntity.getLevel(), mutablePos) : packedLight;

            stack.pushPose();
            stack.translate(0, ringHeight, 0);

            this.transportRings.get(i).render(blockEntity, partialTick, stack, buffer, transportLight, packedOverlay);

            stack.popPose();
        }

        stack.popPose();
    }

    @Override
    public @NotNull AABB getRenderBoundingBox(CameleonTransportRingBlockEntity transportRings)
    {
        BlockPos pos = transportRings.getBlockPos();
        return new AABB(pos.getX() - 3, pos.getY() - (3 + AbstractTransportRingsEntity.MAX_TRANSPORT_HEIGHT), pos.getZ() - 3,
                pos.getX() + 4, pos.getY() + (4 + AbstractTransportRingsEntity.MAX_TRANSPORT_HEIGHT), pos.getZ() + 4);
    }

}
