package me.koenn.LTPT.commands;

import me.koenn.LTPT.towny.TownyPlayer;

public interface ITownyCommand {

    String getCommand();
    String getUsage();

    boolean execute(TownyPlayer player, String[] args);
}
