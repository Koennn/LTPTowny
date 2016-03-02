package me.koenn.LTPT.util;

import me.koenn.LTPT.config.ConfigManager;
import me.koenn.LTPT.player.TownyPlayer;

import java.util.UUID;

public class PlayerUtil {

    public static void loadAllPlayersFromConfig() {
        for (String uuid : ConfigManager.getConfiguration().getConfigurationSection("players").getKeys(false)) {
            new TownyPlayer(UUID.fromString(uuid));
        }
    }
}
