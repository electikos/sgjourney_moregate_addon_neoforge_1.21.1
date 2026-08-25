package org.example.muc.moregate.blockEntity.renderer;

// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


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

public class CameleonDHDHorned<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Moregate.MODID, "cameleon_dhd_horned"), "main");
    private final ModelPart Base;
    private final ModelPart Panel;

    public CameleonDHDHorned(ModelPart root) {
        this.Base = root.getChild("Base");
        this.Panel = root.getChild("Panel");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Base = partdefinition.addOrReplaceChild("Base", CubeListBuilder.create().texOffs(0, 9).addBox(-5.0F, -6.0F, 0.0F, 10.0F, 14.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 26).addBox(-4.0F, -7.0F, 3.0F, 8.0F, 15.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 26).addBox(-5.0F, -4.0F, -3.0F, 10.0F, 12.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 46).addBox(-6.0F, -6.0F, -2.0F, 1.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(10, 46).addBox(5.0F, -6.0F, -2.0F, 1.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(30, 42).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        PartDefinition Panel = partdefinition.addOrReplaceChild("Panel", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));

        PartDefinition cube_r1 = Panel.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(26, 9).addBox(-4.0F, -1.5F, -6.5F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(58, 9).addBox(-1.0F, -3.5F, -3.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(58, 15).addBox(1.0F, -2.5F, -3.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(58, 12).addBox(-2.0F, -2.5F, -3.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 55).addBox(-1.0F, -2.5F, -4.5F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(48, 38).addBox(4.0F, -1.5F, -5.5F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(48, 0).addBox(-5.0F, -1.5F, -5.5F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(32, 55).addBox(-3.0F, -1.5F, 1.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(48, 7).addBox(-3.0F, -1.5F, -7.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 57).addBox(-9.0F, -0.5F, 0.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(48, 51).addBox(-8.0F, -0.5F, 1.5F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(26, 46).addBox(5.0F, -0.5F, 4.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 38).addBox(-6.0F, -0.5F, 4.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(48, 57).addBox(-7.0F, -0.5F, 5.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 57).addBox(4.0F, -0.5F, 5.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(32, 57).addBox(-8.0F, 1.5F, 1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 51).addBox(6.0F, 1.5F, 1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(48, 45).addBox(6.0F, -0.5F, 1.5F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(20, 46).addBox(8.0F, -0.5F, 0.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(44, 34).addBox(-5.0F, -0.5F, -8.5F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 42).addBox(-7.0F, -0.5F, -7.5F, 14.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 22).addBox(-8.0F, -0.5F, -6.5F, 16.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 30).addBox(-5.0F, -0.5F, 2.5F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 26).addBox(-6.0F, -0.5F, 1.5F, 12.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 18).addBox(-8.0F, -0.5F, 0.5F, 16.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-9.0F, -0.5F, -5.5F, 18.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.5F, 1.5F, 0.3927F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int packedColor) {
        Base.render(poseStack, vertexConsumer, packedLight, packedOverlay, packedColor);
        Panel.render(poseStack, vertexConsumer, packedLight, packedOverlay, packedColor);
    }
}
