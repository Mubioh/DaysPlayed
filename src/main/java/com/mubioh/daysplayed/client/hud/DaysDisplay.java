package com.mubioh.daysplayed.client.hud;

import com.mubioh.daysplayed.config.DaysPlayedConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class DaysDisplay implements HudElement {
    private static final int TEXT_PADDING = 5;
    private static final int PADDING = 3;
    private static final int SHADOW_OFFSET = 1;
    private static final int BACKGROUND_COLOR = 0xCC000000;

    private static final long MAX_DAYS = 89477;

    @Override
    public void render(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.options.hudHidden || client.player == null || !DaysPlayedConfig.configInstance.showDaysPlayed || client.getDebugHud().shouldShowDebugHud()) return;

        int scaledHeight = client.getWindow().getScaledHeight();
        int posY = scaledHeight / 10 + 1;
        int textHeight = client.textRenderer.fontHeight;

        World world = client.world;
        long day = world.getTimeOfDay() / 24000L;

        Text daysText = (day > MAX_DAYS)
                ? Text.translatable("hud.days_played.too_many")
                : Text.translatable("hud.days_played", day);

        int textWidth = client.textRenderer.getWidth(daysText);
        int boxTop = posY + textHeight + PADDING;
        int boxBottom = boxTop + textHeight + PADDING + 3;

        context.fill(
                0, boxTop - SHADOW_OFFSET,
                textWidth + (TEXT_PADDING * 2),
                boxBottom - SHADOW_OFFSET,
                BACKGROUND_COLOR
        );

        context.drawTextWithShadow(client.textRenderer, daysText, TEXT_PADDING, boxTop + PADDING, 0xFFFFFFFF);
    }
}

