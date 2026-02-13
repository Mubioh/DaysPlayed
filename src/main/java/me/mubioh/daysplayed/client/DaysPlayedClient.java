package me.mubioh.daysplayed.client;

import me.mubioh.daysplayed.client.hud.DaysDisplay;
import me.mubioh.daysplayed.config.DaysPlayedConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.util.Identifier;

public class DaysPlayedClient implements ClientModInitializer {

    public static final String MOD_ID = "daysplayed";
    public static final Identifier HUD_ID = Identifier.of(MOD_ID, "display");

    @Override
    public void onInitializeClient() {
        DaysPlayedConfig.load();
        HudElementRegistry.addLast(HUD_ID, new DaysDisplay());
    }
}
