package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.gui.Gui;
import me.koenn.LTPT.gui.guis.HelpGui;
import me.koenn.LTPT.player.TownyPlayer;

public class HelpCommand implements ITownyCommand {

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public String getUsage() {
        return "/town help";
    }

    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        HelpGui gui = new HelpGui(player);
        Gui.registerGui(gui);
        gui.open();
        return true;
    }
}
