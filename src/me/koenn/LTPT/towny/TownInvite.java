package me.koenn.LTPT.towny;

import me.koenn.LTPT.references.Messages;

public class TownInvite {

    private Town town;
    private TownyPlayer invitedPlayer;
    private TownyPlayer invitedBy;

    public TownInvite(Town town, TownyPlayer invitedPlayer, TownyPlayer invitedBy) {
        this.town = town;
        this.invitedPlayer = invitedPlayer;
        this.invitedBy = invitedBy;
    }

    public void send(){
        String message = Messages.INVITE.replace("{player}", this.invitedBy.getBukkitPlayer().getName()).replace("{town}", this.town.getName());
        this.invitedPlayer.sendMessage(message);
        this.invitedPlayer.addInvite(this);
    }
}
