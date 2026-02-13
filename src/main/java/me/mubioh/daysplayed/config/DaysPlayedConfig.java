package me.mubioh.daysplayed.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class DaysPlayedConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance()
            .getConfigDir()
            .resolve("daysplayed.json");

    private static DaysPlayedConfig INSTANCE = new DaysPlayedConfig();

    private boolean showDaysPlayed = true;

    public static DaysPlayedConfig instance() {
        return INSTANCE;
    }

    public boolean showDaysPlayed() {
        return showDaysPlayed;
    }

    public void setShowDaysPlayed(boolean value) {
        this.showDaysPlayed = value;
    }

    public static void load() {
        if (!Files.exists(CONFIG_PATH)) {
            save();
            return;
        }

        try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
            DaysPlayedConfig loaded = GSON.fromJson(reader, DaysPlayedConfig.class);
            if (loaded != null) {
                INSTANCE = loaded;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {
                GSON.toJson(INSTANCE, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}