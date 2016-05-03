package me.koenn.LTPT.commands.commands.plotcommands;

import me.koenn.LTPT.chunk.ClaimedChunk;
import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.gui.Gui;
import me.koenn.LTPT.gui.guis.ChunkGui;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.util.ChunkUtil;

import java.util.ArrayList;

public class PlotCommand implements ITownyCommand {

    private static ArrayList<IPlotCommand> plotCommands = new ArrayList<>();

    public static void setup() {
        plotCommands.add(new SetPriceCommand());
        plotCommands.add(new BuyCommand());
    }

    @Override
    public String getCommand() {
        return "plot";
    }

    @Override
    public String getUsage() {
        return "/town plot [subcommand]";
    }

    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        if (!player.hasTown()) {
            player.sendMessage(Messages.NO_TOWN);
            return true;
        }
        if (args.length == 1) {
            showPlotGui(player);
            return true;
        }
        for (IPlotCommand command : plotCommands) {
            if (command.getCommand().equalsIgnoreCase(args[1])) {
                if (!command.execute(player, args)) {
                    player.sendMessage(command.getUsage());
                }
                return true;
            }
        }
        return false;
    }

    private void showPlotGui(TownyPlayer player) {
        if (!ChunkUtil.checkPerms(player, player.getLocation())) {
            player.sendMessage(Messages.NO_PERMS);
            return;
        }
        if (player.getRankValue() < 2) {
            player.sendMessage(Messages.NO_PERMS);
            return;
        }
        ClaimedChunk chunk = ChunkUtil.getClaimedChunk(player.getLocation());
        if (chunk == null) {
            player.sendMessage(Messages.NOT_CLAIMED);
            return;
        }
        ChunkGui gui = new ChunkGui(player, chunk);
        Gui.registerGui(gui);
        gui.open();
    }
}
