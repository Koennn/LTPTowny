package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;

public class HomeCommand implements ITownyCommand {

    @Override
    public String getCommand() {
        return "home";
    }

    @Override
    public String getUsage() {
        return "/town home";
    }

    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        if (!player.hasTown()) {
            player.sendMessage(Messages.NO_TOWN);
            return true;
        }
        player.teleportTownHome();
        player.sendMessage(Messages.TELEPORTED_HOME);
        return true;
    }
}