package me.koenn.LTPT.player;

import me.koenn.LTPT.chunk.ClaimedChunk;
import me.koenn.LTPT.config.ConfigManager;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.towny.Town;
import me.koenn.LTPT.towny.TownInvite;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

@SuppressWarnings("unused")
public class TownyPlayer {

    private static HashMap<UUID, TownyPlayer> players = new HashMap<>();
    private UUID player;
    private Town town;
    private List<TownInvite> townInvites = new ArrayList<>();
    private boolean townChat;

    public TownyPlayer(UUID player) {
        players.put(player, this);
        this.player = player;
        this.town = null;
        this.saveToConfig();
        this.townChat = false;
    }

    public static TownyPlayer getPlayer(UUID player) {
        for (UUID townyPlayer : players.keySet()) {
            if (townyPlayer.equals(player)) {
                return players.get(townyPlayer);
            }
        }
        return null;
    }

    public static HashMap<UUID, TownyPlayer> getAllRegisteredPlayers() {
        return players;
    }

    public void addInvite(TownInvite invite) {
        this.getTownInvites().add(invite);
    }

    public void teleportTownHome() {
        if (!this.hasTown()) {
            this.sendMessage(Messages.NO_TOWN);
            return;
        }
        try {
            this.getBukkitPlayer().teleport(this.getTown().getHome());
        } catch (NullPointerException ex) {
            this.sendMessage(Messages.NO_HOME);
        }
    }

    public void leaveTown() {
        if (this.isTownLeader()) {
            this.sendMessage(Messages.LEADER);
            return;
        }
        this.sendMessage(Messages.YOU_LEFT.replace("{town}", this.getTown().getName()));
        this.town.removePlayer(this);
        this.setTown(null);
    }

    public void sendMessage(String message) {
        if (!this.isOnline()) {
            return;
        }
        this.getBukkitPlayer().sendMessage(translateAlternateColorCodes('&', Messages.PREFIX_CHAT + message));
    }

    public void sendEmptyMessage(String message) {
        if (!this.isOnline()) {
            return;
        }
        this.getBukkitPlayer().sendMessage(translateAlternateColorCodes('&', message));
    }

    public boolean isPlotOwner(ClaimedChunk chunk) {
        return chunk.getOwner() == this;
    }

    public Location getLocation() {
        return this.getBukkitPlayer().getLocation();
    }

    public List<TownInvite> getTownInvites() {
        return townInvites;
    }

    public boolean isTownLeader() {
        return this.town.getLeader().equals(this);
    }

    public boolean hasTown() {
        return town != null;
    }

    public Player getBukkitPlayer() {
        Player player = Bukkit.getPlayer(this.player);
        if (player == null) {
            return null;
        }
        return player;
    }

    public OfflinePlayer getOfflinePlayer() {
        OfflinePlayer player = Bukkit.getOfflinePlayer(this.player);
        if (player == null) {
            throw new NullPointerException("Player doesn't exist");
        }
        return player;
    }

    public boolean isInTownChat() {
        return townChat;
    }

    public void setInTownChat(boolean townChat) {
        this.townChat = townChat;
    }

    public boolean isOnline() {
        return Bukkit.getPlayer(this.player) != null;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public void saveToConfig() {
        String uuid = this.player.toString();
        String town = "-";
        if (this.town != null) {
            town = this.town.getName();
        }
        ConfigManager.setString(town, "town", "players", uuid);
    }
}
