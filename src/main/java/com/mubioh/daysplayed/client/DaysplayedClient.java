package com.mubioh.daysplayed.client;

import com.mubioh.daysplayed.client.hud.DaysDisplay;
import com.mubioh.daysplayed.config.DaysPlayedConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.util.Identifier;

public class DaysplayedClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        DaysPlayedConfig.load();

        HudElementRegistry.addLast(
                Identifier.of("mubioh-daysplayed", "display"),
                new DaysDisplay()
        );
    }
}
