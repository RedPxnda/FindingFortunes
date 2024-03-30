package com.redpxnda.findingfortunes.client;

import com.redpxnda.findingfortunes.facet.FortuneData;
import com.redpxnda.findingfortunes.mixin.client.HeldItemRendererAccessor;
import com.redpxnda.findingfortunes.registries.FFRegistries;
import com.redpxnda.nucleus.client.ArmRenderer;
import com.redpxnda.nucleus.event.RenderEvents;
import com.redpxnda.nucleus.util.Color;
import dev.architectury.event.EventResult;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.OrderedText;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;

import java.util.List;

import static com.redpxnda.findingfortunes.FindingFortunes.MOD_ID;

public class FortuneRenderer {
    public static final Identifier BACKGROUND = new Identifier(MOD_ID, "textures/gui/fortune_background.png");
    public static final RenderLayer BACKGROUND_LAYER = RenderLayer.getText(BACKGROUND);
    public static final Color TEXT_COLOR = new Color(38, 126, 209, 255);

    public static void init() {
        RenderEvents.RENDER_ARM_WITH_ITEM.register((stage, armRenderer, player, matrices, buffer, stack, hand, partialTicks, pitch, swingProgress, equippedProgress, combinedLight) -> {
            if (stage == RenderEvents.ArmRenderStage.PUSHED && stack.isOf(FFRegistries.fortune)) {
                ItemStack offhand = ((HeldItemRendererAccessor) MinecraftClient.getInstance().getEntityRenderDispatcher().getHeldItemRenderer()).getOffHand();
                if (hand == Hand.MAIN_HAND && offhand.isEmpty()) {
                    renderInBothHands(armRenderer, matrices, buffer, combinedLight, pitch, equippedProgress, swingProgress);
                    return EventResult.interruptFalse();
                }
            }
            return EventResult.pass();
        });
    }

    public static float getAngle(float tickDelta) {
        float f = 1.0f - tickDelta / 45.0f + 0.1f;
        f = MathHelper.clamp(f, 0.0f, 1.0f);
        f = -MathHelper.cos(f * (float)Math.PI) * 0.5f + 0.5f;
        return f;
    }

    public static void renderInBothHands(ArmRenderer arms, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, float pitch, float equipProgress, float swingProgress) {
        MinecraftClient client = MinecraftClient.getInstance();
        HeldItemRendererAccessor hir = (HeldItemRendererAccessor) MinecraftClient.getInstance().getEntityRenderDispatcher().getHeldItemRenderer();

        float f = MathHelper.sqrt(swingProgress);
        float g = -0.2f * MathHelper.sin(swingProgress * (float)Math.PI);
        float h = -0.4f * MathHelper.sin(f * (float)Math.PI);

        matrices.translate(0.0f, -g / 2.0f, h);
        float i = getAngle(pitch);
        matrices.translate(0.0f, 0.04f + equipProgress * -1.2f + i * -0.5f, -0.72f);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(i * -85.0f));
        if (!client.player.isInvisible()) {
            matrices.push();
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0f));
            hir.callRenderArm(matrices, vertexConsumers, light, Arm.RIGHT);
            hir.callRenderArm(matrices, vertexConsumers, light, Arm.LEFT);
            matrices.pop();
        }
        float j = MathHelper.sin(f * (float)Math.PI);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(j * 20.0f));
        matrices.scale(2.0f, 2.0f, 2.0f);
        renderFirstPersonFortune(matrices, vertexConsumers, light, hir.getMainHand());
    }

    public static void renderFirstPersonFortune(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ItemStack stack) {
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180.0f));
        matrices.scale(0.38f, 0.38f, 0.38f);
        matrices.translate(-0.5f, -0.5f, 0.0f);
        matrices.scale(0.0078125f, 0.0078125f, 0.0078125f);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(BACKGROUND_LAYER);
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        vertexConsumer.vertex(matrix4f, -7.0f, 135.0f, 0.0f).color(255, 255, 255, 255).texture(0.0f, 1.0f).light(light).next();
        vertexConsumer.vertex(matrix4f, 135.0f, 135.0f, 0.0f).color(255, 255, 255, 255).texture(1.0f, 1.0f).light(light).next();
        vertexConsumer.vertex(matrix4f, 135.0f, -7.0f, 0.0f).color(255, 255, 255, 255).texture(1.0f, 0.0f).light(light).next();
        vertexConsumer.vertex(matrix4f, -7.0f, -7.0f, 0.0f).color(255, 255, 255, 255).texture(0.0f, 0.0f).light(light).next();

        FortuneData data = FortuneData.KEY.get(stack);
        if (data != null) {
            matrices.translate(0, 0, -0.1f);
            TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
            List<OrderedText> texts = textRenderer.wrapLines(data.text, 130);
            int i = 0;
            for (OrderedText text : texts) {
                int x = 64 - textRenderer.getWidth(text) / 2;
                int y = 64 + i * 10 - texts.size() * 5;
                textRenderer.draw(text, x, y, TEXT_COLOR.argb(), false, matrices.peek().getPositionMatrix(), vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, light);
                i++;
            }
        }
    }
}
