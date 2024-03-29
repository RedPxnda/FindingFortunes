package com.redpxnda.findingfortunes.registries;

import com.redpxnda.nucleus.registration.RegistryId;
import net.minecraft.world.item.Item;

public class FFRegistries {
    @RegistryId("fortune_cookie")
    public static final FortuneCookieItem fortuneCookie = new FortuneCookieItem();
}
