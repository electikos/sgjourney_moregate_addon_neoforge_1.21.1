package org.example.muc.moregate.blockEntity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import org.example.muc.moregate.block.custom.CameleonDHDBlock;
import org.example.muc.moregate.blockEntity.CameleonDHDBlockEntity;

import java.util.Optional;

public class CameleonDHDBlockEntityRenderer implements BlockEntityRenderer<CameleonDHDBlockEntity> {
    final defaultModel<?> model;
    final CameleonDHDHorned<?> hornedModel;
    public CameleonDHDBlockEntityRenderer(BlockEntityRendererProvider.Context context){
        this.model = new defaultModel<>(context.bakeLayer(defaultModel.LAYER_LOCATION));
        this.hornedModel = new CameleonDHDHorned<>(context.bakeLayer(CameleonDHDHorned.LAYER_LOCATION));
    }


    @Override
    public void render(CameleonDHDBlockEntity BlockEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
        if (BlockEntity.variant == null) {
            return;
        }
        poseStack.pushPose();

        poseStack.translate(0.5F, 1.5F, 0.5F);

        poseStack.mulPose(Axis.XP.rotationDegrees(180));

        Direction facing = BlockEntity.getBlockState().getValue(CameleonDHDBlock.FACING);
        float rotation = switch (facing) {
            case NORTH -> 180.0F;
            case SOUTH -> 0.0F;
            case WEST  -> 90.0F;
            case EAST  -> -90.0F;
            default -> 0.0F;
        };
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));

        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityCutout(BlockEntity.variant.getTexture()));

        if (!BlockEntity.variant.IsHorned()) this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, 0xFFFFFFFF);
        else this.hornedModel.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, 0xFFFFFFFF);


        poseStack.popPose();
    }
}
