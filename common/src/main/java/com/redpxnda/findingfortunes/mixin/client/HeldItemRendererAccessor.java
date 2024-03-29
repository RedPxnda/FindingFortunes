package com.redpxnda.findingfortunes.mixin.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(HeldItemRenderer.class)
public interface HeldItemRendererAccessor {
    @Accessor
    ItemStack getOffHand();

    @Accessor
    ItemStack getMainHand();

    @Invoker
    void callRenderArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Arm arm);
}
