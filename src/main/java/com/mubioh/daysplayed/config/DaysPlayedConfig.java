package com.mubioh.daysplayed.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DaysPlayedConfig {
    public boolean showDaysPlayed = true;

    private static final File CONFIG_FILE = new File("config/daysplayed_config.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static DaysPlayedConfig configInstance = new DaysPlayedConfig();

    public static void load() {
        if (!CONFIG_FILE.exists()) {
            save();
            return;
        }
        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            DaysPlayedConfig loadedConfig = GSON.fromJson(reader, DaysPlayedConfig.class);
            if (loadedConfig != null) {
                configInstance = loadedConfig;
            }
            System.out.println("[DaysPlayedConfig] Loaded config: showDaysPlayed=" + configInstance.showDaysPlayed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            if (!CONFIG_FILE.getParentFile().exists()) {
                boolean dirsCreated = CONFIG_FILE.getParentFile().mkdirs();
                if (dirsCreated) {
                    System.out.println("[DaysPlayedConfig] Created config directory.");
                }
            }
            try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
                GSON.toJson(configInstance, writer);
                System.out.println("[DaysPlayedConfig] Saved config: showCoordinates=" + configInstance.showDaysPlayed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DaysPlayedConfig() {}

    public DaysPlayedConfig(boolean showDaysPlayed) {
        this.showDaysPlayed = showDaysPlayed;
    }
}
