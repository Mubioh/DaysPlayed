package me.mubioh.daysplayed.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class DaysDisplay implements HudRenderCallback {

    private static final int TEXT_PADDING = 5;
    private static final int PADDING = 3;
    private static final int SHADOW_OFFSET = 1;
    private static final int BACKGROUND_COLOR = 0xCC000000;

    private static final long MAX_DAYS = 89477;

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        World world = client.world;

        if (world != null) {
            int scaledHeight = client.getWindow().getScaledHeight();
            int posY = scaledHeight / 10;
            int textHeight = client.textRenderer.fontHeight;

            long day = world.getTimeOfDay() / 24000L;
            String daysText = day > MAX_DAYS ? "Days played: Too many to count!" : String.format("Days played: %d", day);

            int textWidth = client.textRenderer.getWidth(daysText);

            int boxTop = posY + textHeight + PADDING;
            int boxBottom = boxTop + textHeight + PADDING + 3;

            drawContext.fill(
                    0, boxTop - SHADOW_OFFSET,
                    textWidth + (TEXT_PADDING * 2),
                    boxBottom - SHADOW_OFFSET,
                    BACKGROUND_COLOR
            );

            drawContext.drawTextWithShadow(client.textRenderer, daysText, TEXT_PADDING, boxTop + PADDING, 0xFFFFFF);
        }
    }
}
