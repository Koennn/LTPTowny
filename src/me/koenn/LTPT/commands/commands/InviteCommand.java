package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.towny.TownInvite;
import org.bukkit.Bukkit;

public class InviteCommand implements ITownyCommand {

    @Override
    public String getCommand() {
        return "invite";
    }

    @Override
    public String getUsage() {
        return "/town invite <player>";
    }

    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        if (!player.hasTown()) {
            player.sendMessage(Messages.NO_TOWN);
            return true;
        }
        if (args.length != 2) {
            return false;
        }
        TownyPlayer invitedPlayer;
        try {
            invitedPlayer = TownyPlayer.getPlayer(Bukkit.getPlayer(args[1]).getUniqueId());
        } catch (Exception ex) {
            return false;
        }
        TownInvite invite = new TownInvite(player.getTown(), invitedPlayer, player);
        TownInvite.registerInvite(invite);
        invite.send();
        return true;
    }
}
