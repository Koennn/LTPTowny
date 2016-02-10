package me.koenn.LTPT.towny;

import me.koenn.LTPT.references.Messages;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static net.md_5.bungee.api.ChatColor.translateAlternateColorCodes;

public class TownyPlayer {

    private static HashMap<Player, TownyPlayer> players = new HashMap<>();
    private Player player;
    private Town town;
    private List<TownInvite> townInvites = new ArrayList<>();

    public TownyPlayer(Player player) throws IllegalArgumentException {
        if (players.containsKey(player)) {
            throw new IllegalArgumentException("Player can only be created once");
        }
        players.put(player, this);
        this.player = player;
        this.town = null;
    }

    public void addInvite(TownInvite invite){
        this.getTownInvites().add(invite);
    }

    public static TownyPlayer getPlayer(Player player) {
        if (players.containsKey(player)) {
            return players.get(player);
        } else {
            players.put(player, new TownyPlayer(player));
            return players.get(player);
        }
    }

    public void teleportTownHome() {
        if (this.hasTown()) {
            this.sendMessage(Messages.NO_TOWN);
            return;
        }
        try {
            this.getBukkitPlayer().teleport(this.getTown().getHome());
        } catch (IllegalArgumentException ex) {
            this.sendMessage(Messages.NO_HOME);
        }
    }

    public void sendMessage(String message) {
        this.getBukkitPlayer().sendMessage(translateAlternateColorCodes('&', Messages.PREFIX_CHAT + message));
    }

    public List<TownInvite> getTownInvites() {
        return townInvites;
    }

    public boolean isTownLeader(){
        return this.town.getLeader().equals(this);
    }

    public boolean hasTown() {
        return this.town != null;
    }

    public Player getBukkitPlayer() {
        return player;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
