package me.mubioh.daysplayed.client.hud;

import me.mubioh.daysplayed.config.DaysPlayedConfig;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class DaysDisplay implements HudElement {

    private static final int TEXT_PADDING_X = 5;
    private static final int TEXT_PADDING_Y = 3;

    private static final int BACKGROUND_COLOR = 0xCC000000;
    private static final int TEXT_COLOR = 0xFFFFFFFF;

    private static final long MAX_DAYS = 89477L;

    @Override
    public void render(DrawContext context, RenderTickCounter tickCounter) {
        final MinecraftClient client = MinecraftClient.getInstance();

        if (client.options.hudHidden) return;
        if (client.player == null) return;
        if (client.world == null) return;
        if (client.getDebugHud().shouldShowDebugHud()) return;
        if (!DaysPlayedConfig.instance().showDaysPlayed()) return;

        final World world = client.world;
        final long day = world.getTimeOfDay() / 24000L;

        final Text daysText = (day > MAX_DAYS)
                ? Text.translatable("hud.days_played.too_many")
                : Text.translatable("hud.days_played", day);

        final int textWidth = client.textRenderer.getWidth(daysText);
        final int textHeight = client.textRenderer.fontHeight;

        final int y = (client.getWindow().getScaledHeight() / 10) + textHeight + 6;
        final int x = 0;

        final int bgLeft = x;
        final int bgTop = y - TEXT_PADDING_Y;
        final int bgRight = x + textWidth + (TEXT_PADDING_X * 2);
        final int bgBottom = y + textHeight + TEXT_PADDING_Y;

        context.fill(bgLeft, bgTop, bgRight, bgBottom, BACKGROUND_COLOR);
        context.drawTextWithShadow(client.textRenderer, daysText, x + TEXT_PADDING_X, y, TEXT_COLOR);
    }
}