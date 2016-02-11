package me.koenn.LTPT.towny;

import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.util.Logger;

import java.util.ArrayList;

public class TownInvite {

    private static ArrayList<TownInvite> invites = new ArrayList<>();
    private Town town;
    private TownyPlayer invitedPlayer;
    private TownyPlayer invitedBy;
    public TownInvite(Town town, TownyPlayer invitedPlayer, TownyPlayer invitedBy) {
        this.town = town;
        this.invitedPlayer = invitedPlayer;
        this.invitedBy = invitedBy;
    }

    public static void registerInvite(TownInvite invite) {
        invites.add(invite);
        Logger.debug("Registered new invite '" + invite.toString().split("@")[1] + "'");
    }

    public static TownInvite getInvite(TownyPlayer player) {
        for (TownInvite invite : invites) {
            if (invite.invitedPlayer.equals(player)) {
                return invite;
            }
        }
        throw new NullPointerException("Player is not invited");
    }

    public void send() {
        String message = Messages.INVITE.replace("{player}", this.invitedBy.getBukkitPlayer().getName()).replace("{town}", this.town.getName());
        this.invitedPlayer.sendMessage(message);
        this.invitedPlayer.addInvite(this);
        this.invitedBy.sendMessage(Messages.INVITED.replace("{player}", this.invitedPlayer.getBukkitPlayer().getName()));
    }

    public void accept() {
        for (TownyPlayer player : this.town.getPlayers().keySet()) {
            player.sendMessage(Messages.JOINED.replace("{player}", this.invitedPlayer.getBukkitPlayer().getName()));
        }
        try {
            this.town.addPlayer(this.invitedPlayer);
            this.invitedPlayer.setTown(this.town);
        } catch (IllegalArgumentException ex) {
            this.invitedPlayer.sendMessage(Messages.HAS_TOWN);
        }
        this.invitedPlayer.sendMessage(Messages.YOU_JOINED.replace("{town}", this.town.getName()));
        invites.remove(this);
    }
}
