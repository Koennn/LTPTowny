package me.koenn.LTPT.commands;

import me.koenn.LTPT.player.TownyPlayer;

public interface ITownyCommand {

    String getCommand();

    String getUsage();

    boolean execute(TownyPlayer player, String[] args);
}
