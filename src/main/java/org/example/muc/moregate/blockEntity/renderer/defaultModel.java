package org.example.muc.moregate.blockEntity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.example.muc.moregate.Moregate;

public class defaultModel<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Moregate.MODID, "default_model"), "main");
    private final ModelPart Panel;
    private final ModelPart base;

    public defaultModel(ModelPart root) {
        this.Panel = root.getChild("Panel");
        this.base = root.getChild("base");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Panel = partdefinition.addOrReplaceChild("Panel", CubeListBuilder.create().texOffs(48, 24).addBox(-3.0F, -9.0F, -5.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(48, 26).addBox(-3.0F, -9.0F, 4.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(48, 17).addBox(-5.0F, -9.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 54).addBox(4.0F, -9.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(14, 54).addBox(-1.0F, -10.0F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(34, 54).addBox(-2.0F, -10.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 54).addBox(1.0F, -10.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(26, 54).addBox(-1.0F, -11.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(44, 28).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -16.0F, 1.0F, 12.0F, 16.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(5.0F, -16.0F, 2.0F, 1.0F, 16.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(22, 28).addBox(-8.0F, -16.0F, 2.0F, 1.0F, 16.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(44, 37).addBox(-6.0F, -16.0F, 13.0F, 10.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(48, 0).addBox(-6.0F, -16.0F, 0.0F, 10.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 24.0F, -7.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int packedColor) {
        Panel.render(poseStack, vertexConsumer, packedLight, packedOverlay, packedColor);
        base.render(poseStack, vertexConsumer, packedLight, packedOverlay, packedColor);
    }
}
