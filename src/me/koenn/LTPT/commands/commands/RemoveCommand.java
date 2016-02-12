package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.towny.Town;
import me.koenn.LTPT.towny.TownyPlayer;
import me.koenn.LTPT.util.TownUtil;

public class RemoveCommand implements ITownyCommand {

    @Override
    public String getCommand() {
        return "remove";
    }

    @Override
    public String getUsage() {
        return "/town remove";
    }

    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        if (!player.hasTown()) {
            player.sendMessage(Messages.NO_TOWN);
            return true;
        }
        if (!player.isTownLeader()) {
            player.sendMessage(Messages.NO_PERMS);
        }
        Town town = player.getTown();
        for (TownyPlayer townyPlayer : town.getPlayers().keySet()) {
            townyPlayer.setTown(null);
            town.removePlayer(townyPlayer);
            if (!townyPlayer.isTownLeader()) {
                townyPlayer.sendMessage(Messages.REMOVED);
            }
        }
        player.sendMessage(Messages.REMOVED_SUCCES);
        TownUtil.unRegisterTown(town);
        return true;
    }
}
