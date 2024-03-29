package com.redpxnda.findingfortunes.fabric;

import com.redpxnda.findingfortunes.FindingFortunes;
import net.fabricmc.api.ModInitializer;

public class FindingFortunesFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        FindingFortunes.init();
    }
}
