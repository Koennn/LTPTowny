package me.koenn.LTPT.towny;

import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.util.ChunkUtil;
import me.koenn.LTPT.util.TownRank;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unused")
public class Town {

    public static ArrayList<Town> towns = new ArrayList<>();

    private String name;
    private List<ClaimedChunk> land = new ArrayList<>();
    private HashMap<TownyPlayer, TownRank> players = new HashMap<>();
    private TownyPlayer leader;
    private Location home;

    public Town(String name, TownyPlayer leader) throws IllegalArgumentException {
        if (leader.hasTown()) {
            throw new IllegalArgumentException("Leader already has a town");
        }
        this.name = name;
        this.leader = leader;
        this.players.put(leader, TownRank.LEADER);
        this.home = null;
    }

    public void claimChunkAt(Location location) throws IllegalArgumentException {
        if (!ChunkUtil.isConnected(this, location.getWorld().getChunkAt(location))) {
            if (!this.land.isEmpty()) {
                throw new IllegalArgumentException("Land is not connected");
            }
        }
        this.land.add(new ClaimedChunk(location.getWorld().getChunkAt(location), this));
    }

    public void unclaimChunkAt(Location location) throws NullPointerException {
        ClaimedChunk chunk = ChunkUtil.getClaimedChunk(location);
        if (chunk != null) {
            ClaimedChunk.claimedChunks.remove(chunk);
            this.getLand().remove(chunk);
        } else {
            throw new NullPointerException("No claimed chunk at given location");
        }
    }

    public void addPlayer(TownyPlayer player) throws IllegalArgumentException {
        if (player.hasTown()) {
            throw new IllegalArgumentException("Player already has a town");
        }
        this.players.put(player, TownRank.MEMBER);
    }

    public Location getHome() throws NullPointerException {
        if (this.home == null) {
            throw new NullPointerException("Home is not set");
        }
        return home;
    }

    public void setHome(Location home) {
        this.home = home;
    }

    public void removePlayer(TownyPlayer player) {
        this.players.remove(player);
        for (TownyPlayer p : this.players.keySet()) {
            p.sendMessage(Messages.LEFT.replace("{player}", player.getBukkitPlayer().getName()));
        }
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

    public HashMap<TownyPlayer, TownRank> getPlayers() {
        return players;
    }

    public TownyPlayer getLeader() {
        return leader;
    }

    public void setLeader(TownyPlayer leader) throws IllegalArgumentException {
        if (this.leader == leader) {
            throw new IllegalArgumentException("Player already is town leader");
        }
        this.leader = leader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
