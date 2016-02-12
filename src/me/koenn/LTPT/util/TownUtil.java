package me.koenn.LTPT.util;

import me.koenn.LTPT.towny.ClaimedChunk;
import me.koenn.LTPT.towny.Town;
import me.koenn.LTPT.towny.TownyPlayer;
import org.bukkit.Chunk;
import org.bukkit.Location;

public class TownUtil {

    public static Town getTown(String name) {
        for (Town town : Town.towns) {
            if (town.getName().equals(name)) {
                return town;
            }
        }
        throw new IllegalArgumentException("No town with given name");
    }

    public static Town getTown(TownyPlayer leader) {
        for (Town town : Town.towns) {
            if (town.getLeader().equals(leader)) {
                return town;
            }
        }
        throw new IllegalArgumentException("Player does not own a town");
    }

    public static Town getTown(Location location) {
        for (Town town : Town.towns) {
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
        Town.towns.add(town);
        town.getLeader().setTown(town);
        Logger.debug("Player \'" + town.getLeader().getBukkitPlayer().getName() + "\' registered a new town with id \'" + town.toString().split("@")[1] + "\'");
    }

    public static void unRegisterTown(Town town) {
        Town.towns.remove(town);
        town.getPlayers().clear();
        town.getLand().clear();
        Logger.debug("Player \'" + town.getLeader().getBukkitPlayer().getName() + "\' unregistered his town with id \'" + town.toString().split("@")[1] + "\'");
    }
}
