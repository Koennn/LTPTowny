package me.koenn.LTPT.commands.commands.plotcommands;

import me.koenn.LTPT.chunk.ClaimedChunk;
import me.koenn.LTPT.chunk.PlotType;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.util.ChunkUtil;

public class SetPriceCommand implements IPlotCommand {

    @Override
    public String getCommand() {
        return "setprice";
    }

    @Override
    public String getUsage() {
        return "/t plot setprice <price>";
    }

    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        if (player.getRankValue() < 2) {
            player.sendMessage(Messages.NO_PERMS);
            return true;
        }
        if (args.length < 3) {
            return false;
        }
        ClaimedChunk chunk = ChunkUtil.getClaimedChunk(player.getLocation());
        if (chunk == null) {
            player.sendMessage(Messages.NOT_CLAIMED);
            return true;
        }
        if (!chunk.getTown().equals(player.getTown())) {
            player.sendMessage(Messages.NOT_CLAIMED);
            return true;
        }
        if (!chunk.getType().equals(PlotType.FOR_SALE)) {
            player.sendMessage(Messages.NOT_FOR_SALE);
            return true;
        }
        int price;
        try {
            price = Integer.parseInt(args[2]);
        } catch (NumberFormatException ex) {
            return false;
        }
        chunk.setPrice(price);
        player.sendMessage(Messages.SET_PRICE.replace("{price}", String.valueOf(price)));
        return true;
    }
}
