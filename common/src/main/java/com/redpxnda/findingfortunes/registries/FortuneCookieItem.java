package com.redpxnda.findingfortunes.registries;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FortuneCookieItem extends Item {
    public FortuneCookieItem() {
        super(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.25f).build()));
    }

    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        return livingEntity.eat(level, itemStack);
    }
}
