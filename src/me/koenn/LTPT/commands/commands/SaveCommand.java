package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.util.TownUtil;

public class SaveCommand implements ITownyCommand {

    @Override
    public String getCommand() {
        return "save";
    }

    @Override
    public String getUsage() {
        return "/t save";
    }

    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        if (!player.getBukkitPlayer().isOp()) {
            player.sendMessage(Messages.NO_PERMS);
            return true;
        }
        TownUtil.saveAllTownsToConfig();
        player.sendMessage(Messages.SAVED);
        return true;
    }
}
