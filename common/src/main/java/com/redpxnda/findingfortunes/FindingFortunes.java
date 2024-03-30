package com.redpxnda.findingfortunes;

import com.redpxnda.findingfortunes.client.FortuneRenamingScreen;
import com.redpxnda.findingfortunes.client.FortuneRenderer;
import com.redpxnda.findingfortunes.facet.FortuneData;
import com.redpxnda.findingfortunes.networking.FFPackets;
import com.redpxnda.findingfortunes.registries.FFRegistries;
import com.redpxnda.nucleus.facet.FacetRegistry;
import com.redpxnda.nucleus.registration.RegistrationListener;
import com.redpxnda.nucleus.registration.RegistryAnalyzer;
import dev.architectury.platform.Platform;
import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.fabricmc.api.EnvType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindingFortunes {
    public static final String MOD_ID = "findingfortunes";
    public static final Logger LOGGER = LoggerFactory.getLogger("Finding Fortunes");
    
    public static void init() {
        RegistryAnalyzer.register(MOD_ID, () -> {
            if (Platform.getEnv() == EnvType.CLIENT)
                RegistrationListener.ALL.put(FFRegistries.fortuneRenamingHandlerType, () -> MenuRegistry.registerScreenFactory(FFRegistries.fortuneRenamingHandlerType, FortuneRenamingScreen::new));
            return FFRegistries.class;
        });

        FFPackets.init();

        FortuneData.KEY = FacetRegistry.register(new Identifier(MOD_ID, "fortune_data"), FortuneData.class);
        FacetRegistry.ITEM_FACET_ATTACHMENT.register((stack, attacher) -> {
            if (stack.isOf(FFRegistries.fortune))
                attacher.add(FortuneData.KEY, new FortuneData());
        });

        EnvExecutor.runInEnv(Env.CLIENT, () -> FortuneRenderer::init);
    }
}
