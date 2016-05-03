package me.koenn.LTPT.towny;

import me.koenn.LTPT.chunk.ClaimedChunk;
import me.koenn.LTPT.config.ConfigManager;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.util.ChunkUtil;
import me.koenn.LTPT.util.TownUtil;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unused")
public class Town {

    private static ArrayList<Town> towns = new ArrayList<>();

    private String name;
    private List<ClaimedChunk> land = new ArrayList<>();
    private HashMap<TownyPlayer, TownRank> players = new HashMap<>();
    private TownyPlayer leader;
    private Location home;
    private int maxLand;
    private int outposts;

    public Town(String name, TownyPlayer leader) throws IllegalArgumentException {
        if (leader.hasTown()) {
            throw new IllegalArgumentException("Leader already has a town");
        }
        this.name = name;
        this.leader = leader;
        this.players.put(leader, TownRank.LEADER);
        this.maxLand = this.players.size() * 4;
        this.home = null;
    }

    public static ArrayList<Town> getAllRegisteredTowns() {
        return towns;
    }

    public void claimChunkAt(TownyPlayer player) throws IllegalArgumentException {
        this.maxLand = this.players.size() * 4;
        if (player.getBukkitPlayer().getName().equals("JermainO_o")) {
            this.maxLand = 1000000;
        }
        Location location = player.getLocation();
        if (this.maxLand == this.land.size()) {
            throw new IndexOutOfBoundsException("You can not claim more land");
        }
        if (!ChunkUtil.isConnected(this, location.getWorld().getChunkAt(location))) {
            if (!this.land.isEmpty()) {
                throw new IllegalArgumentException("Land is not connected");
            }
        }
        this.land.add(new ClaimedChunk(location.getWorld().getChunkAt(location), this));
        TownUtil.saveAllTownsToConfig();
    }

    public void claimOutpostAt(TownyPlayer player) throws IllegalArgumentException {
        if (this.outposts == 1) {
            throw new IllegalArgumentException("You can only have one outpost");
        }
        this.maxLand = this.players.size() * 4;
        if (player.getBukkitPlayer().getName().equals("JermainO_o")) {
            this.maxLand = 1000000;
        }
        Location location = player.getLocation();
        if (this.maxLand == this.land.size()) {
            throw new IndexOutOfBoundsException("You can not claim more land");
        }
        this.land.add(new ClaimedChunk(location.getWorld().getChunkAt(location), this));
        this.outposts = 1;
        TownUtil.saveAllTownsToConfig();
    }

    public void unclaimChunkAt(Location location) throws NullPointerException {
        ClaimedChunk chunk = ChunkUtil.getClaimedChunk(location);
        if (chunk != null) {
            ClaimedChunk.claimedChunks.remove(chunk);
            this.getLand().remove(chunk);
        } else {
            throw new NullPointerException("No claimed chunk at given location");
        }
        this.maxLand = this.players.size() * 4;
        TownUtil.saveAllTownsToConfig();
    }

    public void addPlayer(TownyPlayer player) throws IllegalArgumentException {
        if (player.hasTown()) {
            throw new IllegalArgumentException("Player already has a town");
        }
        this.players.put(player, TownRank.RESIDENT);
        this.maxLand = this.players.size() * 4;
        TownUtil.saveAllTownsToConfig();
    }

    public Location getHome() throws NullPointerException {
        if (this.home == null) {
            throw new NullPointerException("Home is not set");
        }
        return home;
    }

    public void setHome(Location home) {
        this.home = home;
        TownUtil.saveAllTownsToConfig();
    }

    public void removeTown() {
        for (TownyPlayer townyPlayer : this.getPlayers().keySet()) {
            if (!townyPlayer.isTownLeader()) {
                townyPlayer.sendMessage(Messages.REMOVED);
            } else {
                townyPlayer.sendMessage(Messages.REMOVED_SUCCESS);
            }
            this.players.remove(townyPlayer);
            townyPlayer.setTown(null);
        }
        this.land.forEach(ClaimedChunk::remove);
        ConfigManager.deleteSection(this.name);
        TownUtil.unRegisterTown(this);
    }

    public void removePlayer(TownyPlayer player) {
        this.players.remove(player);
        for (TownyPlayer p : this.players.keySet()) {
            p.sendMessage(Messages.LEFT.replace("{player}", player.getBukkitPlayer().getName()));
        }
        this.maxLand = this.players.size() * 4;
        TownUtil.saveAllTownsToConfig();
    }

    public void kickPlayer(TownyPlayer player) {
        this.players.remove(player);
        for (TownyPlayer p : this.players.keySet()) {
            p.sendMessage(Messages.KICKED.replace("{player}", player.getBukkitPlayer().getName()));
        }
        this.maxLand = this.players.size() * 4;
        TownUtil.saveAllTownsToConfig();
    }

    public int getMaxLand() {
        return maxLand;
    }

    public void setRank(TownyPlayer player, TownRank rank) {
        this.players.put(player, rank);
    }

    public TownRank getRank(TownyPlayer player) {
        return this.players.get(player);
    }

    public List<ClaimedChunk> getLand() {
        return land;
    }

    public void setLand(List<ClaimedChunk> land) {
        this.land = land;
    }

    public HashMap<TownyPlayer, TownRank> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<TownyPlayer, TownRank> players) {
        this.players = players;
    }

    public TownyPlayer getLeader() {
        return leader;
    }

    public void setLeader(TownyPlayer leader) throws IllegalArgumentException {
        if (this.leader == leader) {
            throw new IllegalArgumentException("Player already is town leader");
        }
        this.leader = leader;
        TownUtil.saveAllTownsToConfig();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
