package me.koenn.LTPT.util;

import me.koenn.LTPT.LTPTowny;
import org.bukkit.Bukkit;

public class Logger {

    public static void info(String message) {
        Bukkit.getLogger().info(String.format("[%s] %s", LTPTowny.getPlugin().getName(), message));
    }
}
