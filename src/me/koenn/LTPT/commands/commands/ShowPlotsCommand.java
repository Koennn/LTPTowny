package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.effects.PlotEffect;
import me.koenn.LTPT.player.TownyPlayer;

public class ShowPlotsCommand implements ITownyCommand {

    @Override
    public String getCommand() {
        return "showplots";
    }

    @Override
    public String getUsage() {
        return "/town showplots";
    }

    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        new PlotEffect().play(player.getLocation());
        return true;
    }
}
