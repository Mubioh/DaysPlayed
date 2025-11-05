package com.mubioh.daysplayed.mixin;

import com.mojang.serialization.Codec;
import com.mubioh.daysplayed.config.DaysPlayedConfig;
import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(AccessibilityOptionsScreen.class)
public class AccessibilityOptionsScreenMixin {
    @Inject(method = "getOptions", at = @At("RETURN"), cancellable = true)
    private static void injectDaysPlayedToggle(GameOptions gameOptions, CallbackInfoReturnable<SimpleOption<?>[]> cir) {
        SimpleOption<?>[] originalOptions = cir.getReturnValue();

        SimpleOption<Boolean> coordinatesToggle = new SimpleOption<>(
                "Show Days Played",
                SimpleOption.emptyTooltip(),
                (text, value) -> Text.translatable(value ? "options.on" : "options.off"),
                new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(true, false), Codec.BOOL),
                DaysPlayedConfig.configInstance.showDaysPlayed,
                newValue -> {
                    DaysPlayedConfig.configInstance.showDaysPlayed = newValue;
                    DaysPlayedConfig.save();
                }
        );

        SimpleOption<?>[] newOptions = Arrays.copyOf(originalOptions, originalOptions.length + 1);
        newOptions[originalOptions.length] = coordinatesToggle;

        cir.setReturnValue(newOptions);
    }
}
