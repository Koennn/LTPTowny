package me.koenn.LTPT;

import me.koenn.LTPT.commands.CommandHandler;
import me.koenn.LTPT.listeners.BlockBreakListener;
import me.koenn.LTPT.listeners.GuiListener;
import me.koenn.LTPT.listeners.PlayerInteractListener;
import me.koenn.LTPT.listeners.PlayerJoinListener;
import me.koenn.LTPT.util.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class LTPTowny extends JavaPlugin {

    public static Plugin plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        Logger.info("All credits for this plugin go to Koenn");
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new GuiListener(), this);
        CommandHandler commandHandler = new CommandHandler();
        getCommand("towny").setExecutor(commandHandler);
        getCommand("town").setExecutor(commandHandler);
        getCommand("t").setExecutor(commandHandler);
        commandHandler.setupCommands();
    }

    @Override
    public void onDisable() {
        Logger.info("All credits for this plugin go to Koenn");
    }
}