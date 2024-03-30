package com.redpxnda.findingfortunes.client;

import com.redpxnda.findingfortunes.facet.FortuneData;
import com.redpxnda.findingfortunes.registries.FFRegistries;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;

public class FortuneRenamingHandler extends ScreenHandler {
    public ItemStack renaming = ItemStack.EMPTY;

    public FortuneRenamingHandler(int syncId, PlayerInventory inventory) {
        super(FFRegistries.fortuneRenamingHandlerType, syncId);
    }

    public void rename(Text newName) {
        if (renaming.isEmpty()) return;
        FortuneData facet = FortuneData.KEY.get(renaming);
        if (facet != null) {
            facet.text = newName;
            facet.update(renaming);
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
