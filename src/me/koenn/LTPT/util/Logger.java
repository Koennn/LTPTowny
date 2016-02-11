package me.koenn.LTPT.util;

import me.koenn.LTPT.LTPTowny;
import org.bukkit.Bukkit;

public class Logger {

    public static void info(String message) {
        String prefix = String.format("[%s] ", LTPTowny.getPlugin().getName());
        Bukkit.getLogger().info(prefix + message);
    }

    public static void debug(String message) {
        if (LTPTowny.debug) {
            String prefix = String.format("[%s] [DEBUG] ", LTPTowny.getPlugin().getName());
            Bukkit.getLogger().info(prefix + message);
        }
    }
}
