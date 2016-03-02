package me.koenn.LTPT.util;

import me.koenn.LTPT.config.ConfigManager;
import me.koenn.LTPT.config.TownSection;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.towny.ClaimedChunk;
import me.koenn.LTPT.towny.Town;
import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.List;

public class TownUtil {

    private static int towns;

    public static Town getTown(String name) {
        for (Town town : Town.getAllRegisteredTowns()) {
            if (town.getName().equals(name)) {
                return town;
            }
        }
        throw new IllegalArgumentException("No town with given name");
    }

    public static Town getTown(TownyPlayer leader) {
        for (Town town : Town.getAllRegisteredTowns()) {
            if (town.getLeader().equals(leader)) {
                return town;
            }
        }
        throw new IllegalArgumentException("Player does not own a town");
    }

    public static Town getTown(Location location) {
        for (Town town : Town.getAllRegisteredTowns()) {
            for (ClaimedChunk chunk : town.getLand()) {
                Chunk locChunk = location.getWorld().getChunkAt(location);
                if (chunk.getX() == locChunk.getX() && chunk.getZ() == locChunk.getZ()) {
                    return town;
                }
            }
        }
        throw new IllegalArgumentException("No town at given location");
    }

    public static void registerTown(Town town) {
        Town.getAllRegisteredTowns().add(town);
        town.getLeader().setTown(town);
        Logger.debug("Player \'" + town.getLeader().getBukkitPlayer().getName() + "\' registered a new town with id \'" + town.toString().split("@")[1] + "\'");
        TownUtil.saveAllTownsToConfig();
    }

    private static void registerExistingTown(Town town) {
        Town.getAllRegisteredTowns().add(town);
        towns++;
    }

    public static void unRegisterTown(Town town) {
        Town.getAllRegisteredTowns().remove(town);
        town.getPlayers().clear();
        town.getLand().clear();
        Logger.debug("Player \'" + town.getLeader().getBukkitPlayer().getName() + "\' unregistered his town with id \'" + town.toString().split("@")[1] + "\'");
        town.setLeader(null);
        TownUtil.saveAllTownsToConfig();
    }

    public static void saveAllTownsToConfig() {
        Logger.info("Saving all towns to config...");
        for (Town town : Town.getAllRegisteredTowns()) {
            new TownSection(town).saveToConfig();
        }
        Logger.info("All " + Town.getAllRegisteredTowns().size() + " town(s) are saved!");
    }

    public static void loadAllTownsFromConfig() {
        Logger.info("Loading all towns...");
        towns = 0;
        for (String section : ConfigManager.getConfiguration().getKeys(false)) {
            if (section.equalsIgnoreCase("players")) {
                continue;
            }
            List<String> land = ConfigManager.getList("land", section);
            List<String> players = ConfigManager.getList("players", section);
            String leader = ConfigManager.getString("leader", section);
            String home;
            try {
                home = ConfigManager.getString("home", section);
            } catch (NullPointerException ex) {
                home = null;
            }
            Town town = new TownSection(section, land, players, leader, home).getTown();
            for (TownyPlayer player : town.getPlayers().keySet()) {
                player.setTown(town);
            }
            registerExistingTown(town);
        }
        Logger.info("Loaded " + towns + " town(s)!");
    }
}
