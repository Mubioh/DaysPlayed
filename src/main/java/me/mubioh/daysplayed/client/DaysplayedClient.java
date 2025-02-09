package me.mubioh.daysplayed.client;

import me.mubioh.daysplayed.client.gui.DaysDisplay;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class DaysplayedClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new DaysDisplay());
    }
}
