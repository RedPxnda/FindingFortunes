package com.redpxnda.findingfortunes.registries;

import com.redpxnda.findingfortunes.client.FortuneRenamingHandler;
import com.redpxnda.nucleus.registration.ItemGroupCreator;
import com.redpxnda.nucleus.registration.RegistryId;
import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;

public class FFRegistries {
    @RegistryId("fortune_cookie")
    public static final FortuneCookieItem fortuneCookie = new FortuneCookieItem();

    @RegistryId("fortune")
    public static final FortuneItem fortune = new FortuneItem();

    @RegistryId("findingfortunes_tab")
    public static final ItemGroup creativeTab = ItemGroupCreator.populate(
            CreativeTabRegistry.create(
                Text.translatable("itemGroup.findingfortunes.findingfortunes_tab"),
                fortuneCookie::getDefaultStack),
            fortuneCookie, fortune);

    @RegistryId("fortune_renamer")
    public static final ScreenHandlerType<FortuneRenamingHandler> fortuneRenamingHandlerType = new ScreenHandlerType<>(FortuneRenamingHandler::new, FeatureFlags.VANILLA_FEATURES);
}
