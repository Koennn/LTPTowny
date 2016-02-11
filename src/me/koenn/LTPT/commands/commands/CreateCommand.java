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
        for (Town t : Town.towns) {
            if (t.getName().equals(townName)) {
                player.sendMessage(Messages.TOWN_ALREADY_EXISTS);
                return true;
            }
        }
        Town town = new Town(townName, player);
        TownUtil.registerTown(town);
        player.setTown(town);
        player.sendMessage(Messages.CREATED.replace("{town}", town.getName()));
        return true;
    }
}