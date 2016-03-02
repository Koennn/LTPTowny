package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.towny.Town;

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
        town.removeTown();
        return true;
    }
}
