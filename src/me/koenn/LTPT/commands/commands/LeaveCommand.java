package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;

public class LeaveCommand implements ITownyCommand {

    @Override
    public String getCommand() {
        return "leave";
    }

    @Override
    public String getUsage() {
        return "/town leave";
    }

    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        if (!player.hasTown()) {
            player.sendMessage(Messages.NO_TOWN);
            return true;
        }
        if (player.isTownLeader()) {
            player.sendMessage(Messages.LEADER);
            return true;
        }
        player.leaveTown();
        return true;
    }
}
