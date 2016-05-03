package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.towny.TownRank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SetRankCommand implements ITownyCommand {

    @Override
    public String getCommand() {
        return "setrank";
    }

    @Override
    public String getUsage() {
        return "/t setrank <player> <rank>";
    }

    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        if (!player.hasTown()) {
            player.sendMessage(Messages.NO_TOWN);
            return true;
        }
        if (!player.isTownLeader()) {
            player.sendMessage(Messages.NO_PERMS);
            return true;
        }
        if (args.length < 3) {
            return false;
        }
        Player targetPlayer;
        try {
            targetPlayer = Bukkit.getPlayer(args[1]);
        } catch (Exception ex) {
            return false;
        }
        TownyPlayer target = TownyPlayer.getPlayer(targetPlayer.getUniqueId());
        TownRank rank;
        try {
            rank = TownRank.valueOf(args[2].toUpperCase());
        } catch (Exception ex) {
            return false;
        }
        if (target == null) {
            return false;
        }
        if (rank.equals(TownRank.LEADER)) {
            player.sendMessage(Messages.NOT_SECOND_LEADER);
            return true;
        }
        if (target.isTownLeader()) {
            player.sendMessage(Messages.NOT_LEADER);
            return true;
        }
        if (!target.hasTown() || !target.getTown().equals(player.getTown())) {
            player.sendMessage(Messages.PLAYER_NOT_IN_TOWN);
            return true;
        }
        player.getTown().getPlayers().put(target, rank);
        player.sendMessage(Messages.RANK_SET.replace("{player}", target.getBukkitPlayer().getName()).replace("{rank}", rank.toString()));
        return true;
    }
}
