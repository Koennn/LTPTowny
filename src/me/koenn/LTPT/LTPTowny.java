package me.koenn.LTPT;

import me.koenn.LTPT.commands.CommandHandler;
import me.koenn.LTPT.listeners.BlockBreakListener;
import me.koenn.LTPT.listeners.PlayerInteractListener;
import me.koenn.LTPT.listeners.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class LTPTowny extends JavaPlugin {

    public static Plugin plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    public void log(String msg) {
        String prefix = String.format("[%s] ", this.getName());
        getLogger().info(prefix + msg);
    }

    @Override
    public void onEnable() {
        this.log("All credits for this plugin go to Koenn");
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        CommandHandler commandHandler = new CommandHandler();
        getCommand("towny").setExecutor(commandHandler);
        getCommand("town").setExecutor(commandHandler);
        getCommand("t").setExecutor(commandHandler);
        commandHandler.setupCommands();
    }

    @Override
    public void onDisable() {
        this.log("All credits for this plugin go to Koenn");
    }
}