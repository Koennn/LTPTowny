package me.koenn.LTPT;

import me.koenn.LTPT.commands.CommandHandler;
import me.koenn.LTPT.commands.commands.plotcommands.PlotCommand;
import me.koenn.LTPT.config.ConfigManager;
import me.koenn.LTPT.listeners.*;
import me.koenn.LTPT.util.Logger;
import me.koenn.LTPT.util.PlayerUtil;
import me.koenn.LTPT.util.TownUtil;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class LTPTowny extends JavaPlugin {

    public static final boolean debug_mode = true;
    public static Plugin plugin;
    public static Economy econ = null;

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        Logger.info("All credits for this plugin go to Koenn");
        if (!(setupEconomy())) {
            Bukkit.getLogger().severe(String.format("[%s] Disabled due to no Vault dependency found!", getDescription().getName()));
            Bukkit.getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (debug_mode) {
            Logger.debug("Loading plugin in debug mode!");
        }
        ConfigManager configManager = new ConfigManager(this.getConfig(), this);
        configManager.setupConfig();
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new BuildListener(), this);
        Bukkit.getPluginManager().registerEvents(new FallListener(), this);
        Bukkit.getPluginManager().registerEvents(new GuiListener(), this);
        Bukkit.getPluginManager().registerEvents(new FlightListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new ExplodeListener(), this);
        Bukkit.getPluginManager().registerEvents(new PvpListener(), this);
        CommandHandler commandHandler = new CommandHandler();
        getCommand("towny").setExecutor(commandHandler);
        getCommand("town").setExecutor(commandHandler);
        getCommand("t").setExecutor(commandHandler);
        commandHandler.setupCommands();
        PlotCommand.setup();
        PlayerUtil.loadAllPlayersFromConfig();
        TownUtil.loadAllTownsFromConfig();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, TownUtil::saveAllTownsToConfig, 20, 1200);
    }

    private boolean setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    @Override
    public void onDisable() {
        Logger.info("All credits for this plugin go to Koenn");
        TownUtil.saveAllTownsToConfig();
    }
}