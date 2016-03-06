package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;

public class ChatCommand implements ITownyCommand {

    @Override
    public String getCommand() {
        return "chat";
    }

    @Override
    public String getUsage() {
        return "/town chat";
    }

    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        if (!player.hasTown()) {
            player.sendMessage(Messages.NO_TOWN);
            return true;
        }
        if (player.isInTownChat()) {
            player.setInTownChat(false);
            player.sendMessage(Messages.TOGGLE_TOWN_CHAT.replace("{on/off}", "off"));
        } else {
            player.setInTownChat(true);
            player.sendMessage(Messages.TOGGLE_TOWN_CHAT.replace("{on/off}", "on"));
        }
        return true;
    }
}
