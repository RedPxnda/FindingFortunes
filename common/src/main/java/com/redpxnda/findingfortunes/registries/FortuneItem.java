package com.redpxnda.findingfortunes.registries;

import com.redpxnda.findingfortunes.client.FortuneRenamingHandler;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FortuneItem extends Item implements NamedScreenHandlerFactory {
    public FortuneItem() {
        super(new Settings());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        /*FortuneData data = FortuneData.KEY.get(stack); // todo tooltip, but wrapped
        if (data != null && !data.text.toString().isEmpty()) tooltip.add(data.text);*/
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user instanceof ServerPlayerEntity player && hand == Hand.MAIN_HAND) {
            ItemStack itemStack = user.getStackInHand(hand);
            player.openHandledScreen(this);
            return TypedActionResult.consume(itemStack);
        }
        return super.use(world, user, hand);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        FortuneRenamingHandler handler = new FortuneRenamingHandler(syncId, playerInventory);
        handler.renaming = player.getMainHandStack();
        return handler;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.findingfortunes.fortune_renamer");
    }
}
