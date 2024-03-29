package com.redpxnda.findingfortunes;

import com.redpxnda.findingfortunes.client.FortuneRenderer;
import com.redpxnda.findingfortunes.registries.FFRegistries;
import com.redpxnda.nucleus.registration.RegistryAnalyzer;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;

public class FindingFortunes {
    public static final String MOD_ID = "findingfortunes";
    
    public static void init() {
        RegistryAnalyzer.register(MOD_ID, () -> FFRegistries.class);

        EnvExecutor.runInEnv(Env.CLIENT, () -> FortuneRenderer::init);
    }
}
