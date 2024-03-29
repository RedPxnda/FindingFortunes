package com.redpxnda.findingfortunes.registries;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FortuneCookieItem extends Item {
    public FortuneCookieItem() {
        super(new Item.Settings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.25f).build()));
    }

    public ItemStack finishUsing(ItemStack itemStack, World world, LivingEntity livingEntity) {
        ItemStack result = livingEntity.eatFood(world, itemStack);
        if (result.isEmpty())
            return new ItemStack(FFRegistries.fortune);
        else if (livingEntity instanceof PlayerEntity player)
            player.getInventory().insertStack(new ItemStack(FFRegistries.fortune));
        return result;
    }
}
