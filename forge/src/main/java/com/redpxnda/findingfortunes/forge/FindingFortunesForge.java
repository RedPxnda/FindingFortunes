package com.redpxnda.findingfortunes.forge;

import dev.architectury.platform.forge.EventBuses;
import com.redpxnda.findingfortunes.FindingFortunes;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FindingFortunes.MOD_ID)
public class FindingFortunesForge {

    public FindingFortunesForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(FindingFortunes.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        FindingFortunes.init();
    }
}
