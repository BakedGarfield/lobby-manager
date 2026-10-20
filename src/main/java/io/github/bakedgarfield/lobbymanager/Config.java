package io.github.bakedgarfield.lobbymanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/** Server settings, including the text displayed by lobby commands. */
public final class Config {
    /** Worlds uses the minecraft: namespace for worlds without an explicit namespace. */
    public String lobbyDimension = "minecraft:lobby";
    public String defaultSurvivalDimension = "minecraft:overworld";
    public String allowedNpcTag = "lobbymanager_npc";
    public boolean lobbyOnJoin = true;
    public boolean lobbyAdventure = true;
    public boolean noBlockBreak = true;
    public boolean noBlockPlaceOrUse = true;
    public boolean noPlayerAttacks = true;
    public boolean noDamage = true;
    public boolean noHunger = true;
    public boolean noItemPickup = true;
    public boolean noItemDrop = true;
    /** Currently handled through lobby-specific gamerules; see the README. */
    public boolean noMobSpawning = true;
    public boolean separateInventories = true;
    public int backupIntervalTicks = 200;
    public int joinDelayTicks = 6;
    public Messages messages = new Messages();

    public static final class Messages {
        public String prefix = "[Lobby Manager] ";
        public String lobbySuccess = "Teleported to the lobby.";
        public String lobbyTeleportFailed = "Could not teleport you to the lobby.";
        public String lobbyWorldUnavailable = "The lobby world {dimension} is not loaded. Contact an administrator.";
        public String buildModeEnabled = "Build mode enabled.";
        public String buildModeDisabled = "Build mode disabled.";
        public String buildModeLobbyOnly = "Build mode can only be used in the lobby.";
        public String survivalSuccess = "Returned to survival.";
        public String survivalLobbyOnly = "You can only use this command in the lobby.";
        public String survivalWorldUnavailable = "The survival world is not available.";
        public String survivalTeleportFailed = "Could not teleport you to survival.";
        public String inventorySwapFailed = "Could not switch inventories. Contact an administrator.";
        public String playerDataLoadFailed = "Your player data could not be loaded. Contact an administrator.";
        public String playerDataUnavailable = "Your player data is unavailable. Contact an administrator.";
    }

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static Config load() throws IOException {
        Path path = FabricLoader.getInstance().getConfigDir().resolve("lobbymanager.json");
        Files.createDirectories(path.getParent());
        if (!Files.exists(path)) {
            Config config = new Config();
            Files.writeString(path, GSON.toJson(config), StandardCharsets.UTF_8);
            return config;
        }
        try {
            Config config = GSON.fromJson(Files.readString(path, StandardCharsets.UTF_8), Config.class);
            if (config == null || config.lobbyDimension == null || config.lobbyDimension.isBlank()
                    || config.defaultSurvivalDimension == null || config.defaultSurvivalDimension.isBlank()
                    || config.allowedNpcTag == null || config.allowedNpcTag.isBlank()
                    || config.messages == null) {
                throw new IOException("Invalid lobbymanager.json; required fields are missing");
            }
            return config;
        } catch (RuntimeException ex) {
            throw new IOException("Could not read lobbymanager.json", ex);
        }
    }
}
