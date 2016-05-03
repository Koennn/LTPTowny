package me.koenn.LTPT.config;

import me.koenn.LTPT.chunk.ChunkPermission;
import me.koenn.LTPT.chunk.PlotType;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.towny.Town;
import me.koenn.LTPT.towny.TownRank;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * This class will be created to save a town to the config file or load a town from the config file.
 * The method for converting a String to a Location can be found in the ConfigManager class.
 *
 * @see ConfigManager
 */
public class TownSection {

    private String name;
    private List<ChunkCoord> land = new ArrayList<>();
    private List<String> players = new ArrayList<>();
    private TownyPlayer leader;
    private String home;
    private Town town;

    public TownSection(Town town) {
        this.name = town.getName();
        this.land.addAll(town.getLand().stream().map(ChunkCoord::new).collect(Collectors.toList()));
        this.leader = town.getLeader();
        Location home;
        try {
            home = town.getHome();
            this.home = "world=" + home.getWorld().getName() + ",x=" + home.getX() + ",y=" + home.getY() + ",z=" + home.getZ() + ",yaw=" + home.getYaw() + ",pitch=" + home.getPitch();
        } catch (NullPointerException ex) {
            this.home = null;
        }
        this.players = town.getPlayers().keySet().stream().map(player -> player.getOfflinePlayer().getUniqueId().toString() + "," + town.getPlayers().get(player)).collect(Collectors.toList());
    }

    public TownSection(String name, List<String> land, List<String> players, String leader, String home) {
        UUID uuid = UUID.fromString(leader);
        TownyPlayer townLeader = TownyPlayer.getPlayer(uuid);
        if (townLeader == null) {
            townLeader = new TownyPlayer(uuid);
        }
        Town town = new Town(name, townLeader);
        townLeader.setTown(town);

        List<ChunkCoord> chunkCoords = new ArrayList<>();
        for (String coord : land) {
            String[] split = coord.split(",");
            ChunkPermission permission;
            permission = new ChunkPermission(Boolean.valueOf(split[5].toUpperCase()), Boolean.valueOf(split[6].toUpperCase()), Boolean.valueOf(split[7].toUpperCase()), Boolean.valueOf(split[8].toUpperCase()));
            UUID owner = null;
            if (!split[10].contains("null")) {
                owner = UUID.fromString(split[10]);
            }
            chunkCoords.add(new ChunkCoord(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Bukkit.getWorld(split[2]), town, PlotType.valueOf(split[4].toUpperCase()), permission, Integer.parseInt(split[9]), owner));
        }
        town.setLand(chunkCoords.stream().map(ChunkCoord::createClaimedChunk).collect(Collectors.toList()));

        HashMap<TownyPlayer, TownRank> townPlayers = new HashMap<>();
        for (String player : players) {
            TownyPlayer townyPlayer = TownyPlayer.getPlayer(UUID.fromString(player.split(",")[0]));
            if (townyPlayer == null) {
                townyPlayer = new TownyPlayer(UUID.fromString(player.split(",")[0]));
            }
            townPlayers.put(townyPlayer, TownRank.valueOf(player.split(",")[1].toUpperCase()));
        }
        town.setPlayers(townPlayers);

        town.setHome(ConfigManager.getLocationFromString(home));
        this.town = town;
    }

    public Town getTown() {
        return town;
    }

    public void saveToConfig() {
        ConfigManager.setString(leader.getOfflinePlayer().getUniqueId().toString(), "leader", name);
        ConfigManager.setList(land.stream().map(ChunkCoord::getChunkString).collect(Collectors.toList()), "land", name);
        ConfigManager.setString(home, "home", name);
        ConfigManager.setList(players, "players", name);
    }
}
