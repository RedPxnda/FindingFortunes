package com.redpxnda.findingfortunes;

import com.redpxnda.findingfortunes.registries.FFRegistries;
import com.redpxnda.nucleus.registration.RegistryAnalyzer;

public class FindingFortunes {
    public static final String MOD_ID = "findingfortunes";
    
    public static void init() {
        RegistryAnalyzer.register(MOD_ID, () -> FFRegistries.class);
    }
}
