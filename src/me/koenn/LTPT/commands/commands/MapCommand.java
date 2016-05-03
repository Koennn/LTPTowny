package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.gui.Gui;
import me.koenn.LTPT.gui.guis.MapGui;
import me.koenn.LTPT.player.TownyPlayer;

public class MapCommand implements ITownyCommand {

    @Override
    public String getCommand() {
        return "map";
    }

    @Override
    public String getUsage() {
        return "/t map";
    }

    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        MapGui gui = new MapGui(player);
        Gui.registerGui(gui);
        gui.open();
        return true;
    }
}
