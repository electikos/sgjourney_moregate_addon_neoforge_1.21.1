package org.example.muc.moregate.blockEntity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.povstalec.sgjourney.client.models.block_entity.SGJourneyModel;
import net.povstalec.sgjourney.common.block_entities.transporter.AbstractTransportRingsEntity;
import org.joml.Matrix4f;

public class CameleonTransportRingsModel<TransportRingsEntity extends AbstractTransportRingsEntity<?>> {
    public final ResourceLocation texture;
    public final int sides;
    public final float height;
    public final float outerRadius;
    public final float innerRadius;
    public final float angle;
    public final float outerSideLength;
    public final float innerSideLength;
    public final float ringHalfwayPoint;
    public final float dividerLength;
    public final float dividerWidth;
    public final float dividerHeight;

    public CameleonTransportRingsModel(ResourceLocation texture, int sides, float height, float outerRadius, float innerRadius) {
        this.texture = texture;
        this.sides = sides;
        this.height = height;
        this.outerRadius = outerRadius;
        this.innerRadius = innerRadius;
        this.angle = 360.0F / (float)sides;
        this.outerSideLength = sideLength(sides, outerRadius);
        this.innerSideLength = sideLength(sides, innerRadius);
        float ringThickness = outerRadius - innerRadius;
        this.ringHalfwayPoint = ringThickness / 2.0F + innerRadius;
        this.dividerLength = ringThickness + 0.0625F;
        this.dividerWidth = 0.0625F;
        this.dividerHeight = height + 0.0625F;
    }

    public static float sideLength(int sides, float radius) {
        return (float)((double)(2.0F * radius) * Math.tan(Math.PI / (double)sides));
    }

    private void renderRingSegment(VertexConsumer ringTexture, Matrix4f matrix4, PoseStack.Pose pose, int combinedLight) {
        SGJourneyModel.createQuad(ringTexture, matrix4, pose, combinedLight, 0.0F, 0.0F, 1.0F, -this.outerSideLength / 2.0F, this.height / 2.0F, this.outerRadius, 0.0F, 0.40625F, -this.outerSideLength / 2.0F, -this.height / 2.0F, this.outerRadius, 0.0F, 0.5625F, this.outerSideLength / 2.0F, -this.height / 2.0F, this.outerRadius, 0.125F, 0.5625F, this.outerSideLength / 2.0F, this.height / 2.0F, this.outerRadius, 0.125F, 0.40625F);
        SGJourneyModel.createQuad(ringTexture, matrix4, pose, combinedLight, 0.0F, 0.0F, -1.0F, this.innerSideLength / 2.0F, this.height / 2.0F, this.innerRadius, 0.015625F, 0.0F, this.innerSideLength / 2.0F, -this.height / 2.0F, this.innerRadius, 0.015625F, 0.15625F, -this.innerSideLength / 2.0F, -this.height / 2.0F, this.innerRadius, 0.109375F, 0.15625F, -this.innerSideLength / 2.0F, this.height / 2.0F, this.innerRadius, 0.109375F, 0.0F);
        SGJourneyModel.createQuad(ringTexture, matrix4, pose, combinedLight, 0.0F, 1.0F, 0.0F, -this.innerSideLength / 2.0F, this.height / 2.0F, this.innerRadius, 0.015625F, 0.15625F, -this.outerSideLength / 2.0F, this.height / 2.0F, this.outerRadius, 0.0F, 0.40625F, this.outerSideLength / 2.0F, this.height / 2.0F, this.outerRadius, 0.125F, 0.40625F, this.innerSideLength / 2.0F, this.height / 2.0F, this.innerRadius, 0.109375F, 0.15625F);
        SGJourneyModel.createQuad(ringTexture, matrix4, pose, combinedLight, 0.0F, -1.0F, 0.0F, -this.outerSideLength / 2.0F, -this.height / 2.0F, this.outerRadius, 0.0F, 0.5625F, -this.innerSideLength / 2.0F, -this.height / 2.0F, this.innerRadius, 0.015625F, 0.8125F, this.innerSideLength / 2.0F, -this.height / 2.0F, this.innerRadius, 0.109375F, 0.8125F, this.outerSideLength / 2.0F, -this.height / 2.0F, this.outerRadius, 0.125F, 0.5625F);
    }

    private void renderDivider(VertexConsumer ringTexture, Matrix4f matrix4, PoseStack.Pose pose, int combinedLight) {
        SGJourneyModel.createQuad(ringTexture, matrix4, pose, combinedLight, 0.0F, 0.0F, 1.0F, -this.dividerWidth / 2.0F, this.dividerHeight / 2.0F, this.ringHalfwayPoint + this.dividerLength / 2.0F, 0.265625F, 0.28125F, -this.dividerWidth / 2.0F, -this.dividerHeight / 2.0F, this.ringHalfwayPoint + this.dividerLength / 2.0F, 0.265625F, 0.46875F, this.dividerWidth / 2.0F, -this.dividerHeight / 2.0F, this.ringHalfwayPoint + this.dividerLength / 2.0F, 0.28125F, 0.46875F, this.dividerWidth / 2.0F, this.dividerHeight / 2.0F, this.ringHalfwayPoint + this.dividerLength / 2.0F, 0.28125F, 0.28125F);
        SGJourneyModel.createQuad(ringTexture, matrix4, pose, combinedLight, 0.0F, 0.0F, -1.0F, this.dividerWidth / 2.0F, this.dividerHeight / 2.0F, this.ringHalfwayPoint - this.dividerLength / 2.0F, 0.40625F + 0.03125F, 0.28125F, this.dividerWidth / 2.0F, -this.dividerHeight / 2.0F, this.ringHalfwayPoint - this.dividerLength / 2.0F, 0.40625F + 0.03125F, 0.46875F, -this.dividerWidth / 2.0F, -this.dividerHeight / 2.0F, this.ringHalfwayPoint - this.dividerLength / 2.0F, 0.421875F + 0.03125F, 0.46875F, -this.dividerWidth / 2.0F, this.dividerHeight / 2.0F, this.ringHalfwayPoint - this.dividerLength / 2.0F, 0.421875F + 0.03125F, 0.28125F);
        SGJourneyModel.createQuad(ringTexture, matrix4, pose, combinedLight, 0.0F, 1.0F, 0.0F, -this.dividerWidth / 2.0F, this.dividerHeight / 2.0F, this.ringHalfwayPoint - this.dividerLength / 2.0F, 0.265625F, 0.0F, -this.dividerWidth / 2.0F, this.dividerHeight / 2.0F, this.ringHalfwayPoint + this.dividerLength / 2.0F, 0.265625F, 0.28125F, this.dividerWidth / 2.0F, this.dividerHeight / 2.0F, this.ringHalfwayPoint + this.dividerLength / 2.0F, 0.28125F, 0.28125F, this.dividerWidth / 2.0F, this.dividerHeight / 2.0F, this.ringHalfwayPoint - this.dividerLength / 2.0F, 0.28125F, 0.0F);
        SGJourneyModel.createQuad(ringTexture, matrix4, pose, combinedLight, 0.0F, -1.0F, 0.0F, -this.dividerWidth / 2.0F, -this.dividerHeight / 2.0F, this.ringHalfwayPoint + this.dividerLength / 2.0F, 0.28125F, 0.0F, -this.dividerWidth / 2.0F, -this.dividerHeight / 2.0F, this.ringHalfwayPoint - this.dividerLength / 2.0F, 0.28125F, 0.28125F, this.dividerWidth / 2.0F, -this.dividerHeight / 2.0F, this.ringHalfwayPoint - this.dividerLength / 2.0F, 0.296875F, 0.28125F, this.dividerWidth / 2.0F, -this.dividerHeight / 2.0F, this.ringHalfwayPoint + this.dividerLength / 2.0F, 0.296875F, 0.0F);
        SGJourneyModel.createQuad(ringTexture, matrix4, pose, combinedLight, -1.0F, 0.0F, 0.0F, -this.dividerWidth / 2.0F, this.dividerHeight / 2.0F, this.ringHalfwayPoint - this.dividerLength / 2.0F, 0.125F, 0.28125F, -this.dividerWidth / 2.0F, -this.dividerHeight / 2.0F, this.ringHalfwayPoint - this.dividerLength / 2.0F, 0.125F, 0.46875F, -this.dividerWidth / 2.0F, -this.dividerHeight / 2.0F, this.ringHalfwayPoint + this.dividerLength / 2.0F, 0.265625F, 0.46875F, -this.dividerWidth / 2.0F, this.dividerHeight / 2.0F, this.ringHalfwayPoint + this.dividerLength / 2.0F, 0.265625F, 0.28125F);
        SGJourneyModel.createQuad(ringTexture, matrix4, pose, combinedLight, 1.0F, 0.0F, 0.0F, this.dividerWidth / 2.0F, this.dividerHeight / 2.0F, this.ringHalfwayPoint + this.dividerLength / 2.0F, 0.28125F, 0.28125F, this.dividerWidth / 2.0F, -this.dividerHeight / 2.0F, this.ringHalfwayPoint + this.dividerLength / 2.0F, 0.28125F, 0.46875F, this.dividerWidth / 2.0F, -this.dividerHeight / 2.0F, this.ringHalfwayPoint - this.dividerLength / 2.0F, 0.421875F, 0.46875F, this.dividerWidth / 2.0F, this.dividerHeight / 2.0F, this.ringHalfwayPoint - this.dividerLength / 2.0F, 0.421875F, 0.28125F);
    }

    public void render(TransportRingsEntity transportRings, float partialTick, PoseStack stack, MultiBufferSource source, int combinedLight, int combinedOverlay) {
        VertexConsumer ringTexture = source.getBuffer(RenderType.entityTranslucent(this.texture));

        for(int i = 0; i < this.sides; ++i) {
            stack.pushPose();
            stack.mulPose(Axis.YP.rotationDegrees((float)i * this.angle));
            Matrix4f matrix4 = stack.last().pose();
            this.renderRingSegment(ringTexture, matrix4, stack.last(), combinedLight);
            stack.popPose();
            stack.pushPose();
            stack.mulPose(Axis.YP.rotationDegrees((float)i * this.angle - this.angle / 2.0F));
            matrix4 = stack.last().pose();
            this.renderDivider(ringTexture, matrix4, stack.last(), combinedLight);
            stack.popPose();
        }

    }

    public static LayerDefinition createRingLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        float radius = 39.31936F;
        float sideLength = sideLength(36, radius);
        float height = 5.0F;

        for(int i = 0; i < 36; ++i) {
            partdefinition.addOrReplaceChild("ring_" + i, CubeListBuilder.create().texOffs(0, 0).addBox(-sideLength / 2.0F, -height / 2.0F, radius - 8.0F, sideLength, height, 8.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, (float)Math.toRadians((double)(10 * i)), 0.0F));
        }

        return LayerDefinition.create(meshdefinition, 64, 32);
    }
}
