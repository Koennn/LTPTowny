package me.koenn.LTPT.commands.commands.plotcommands;

import me.koenn.LTPT.player.TownyPlayer;

public interface IPlotCommand {

    String getCommand();

    String getUsage();

    boolean execute(TownyPlayer player, String[] args);
}
