package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class KickCommand implements ITownyCommand {

    @Override
    public String getCommand() {
        return "kick";
    }

    @Override
    public String getUsage() {
        return "/town kick <player>";
    }

    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        if (!player.hasTown()) {
            player.sendMessage(Messages.NO_TOWN);
            return true;
        }
        if (player.getRankValue() < 2) {
            player.sendMessage(Messages.NO_PERMS);
            return true;
        }
        if (args.length < 2) {
            return false;
        }
        TownyPlayer target;
        try {
            Player targetPlayer = Bukkit.getPlayer(args[1]);
            target = TownyPlayer.getPlayer(targetPlayer.getUniqueId());
        } catch (Exception ex) {
            return false;
        }
        if (target == null) {
            return false;
        }
        if (!target.hasTown()) {
            player.sendMessage(Messages.PLAYER_NOT_IN_TOWN);
            return true;
        }
        if (!target.getTown().equals(player.getTown())) {
            player.sendMessage(Messages.PLAYER_NOT_IN_TOWN);
            return true;
        }
        player.getTown().kickPlayer(target);
        target.setTown(null);
        target.sendMessage(Messages.GOT_KICKED);
        return true;
    }
}
