package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.towny.Town;
import me.koenn.LTPT.towny.TownyPlayer;
import me.koenn.LTPT.util.TownUtil;

public class CreateCommand implements ITownyCommand {

    @Override
    public String getCommand() {
        return "create";
    }

    @Override
    public String getUsage() {
        return "/towny create <name>";
    }

    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        if (args.length != 2) {
            return false;
        }
        String townName = args[1];
        if (player.hasTown()) {
            player.sendMessage(Messages.HAS_TOWN);
            return true;
        }
        TownUtil.registerTown(new Town(townName, player));
        return true;
    }
}