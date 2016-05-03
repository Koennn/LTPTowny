package me.koenn.LTPT.commands.commands.plotcommands;

import me.koenn.LTPT.LTPTowny;
import me.koenn.LTPT.chunk.ClaimedChunk;
import me.koenn.LTPT.chunk.PlotType;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.util.ChunkUtil;

public class BuyCommand implements IPlotCommand {

    @Override
    public String getCommand() {
        return "buy";
    }

    @Override
    public String getUsage() {
        return "/town plot buy";
    }

    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        if (!ChunkUtil.isClaimed(player.getLocation())) {
            player.sendMessage(Messages.NOT_CLAIMED);
            return true;
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
        if (LTPTowny.econ.getBalance(player.getBukkitPlayer()) < chunk.getPrice()) {
            player.sendMessage(Messages.NOT_ENOUGH_MONEY.replace("{amount}", String.valueOf(chunk.getPrice())));
            return true;
        }
        LTPTowny.econ.withdrawPlayer(player.getBukkitPlayer(), chunk.getPrice());
        LTPTowny.econ.depositPlayer(player.getTown().getLeader().getOfflinePlayer(), chunk.getPrice());
        chunk.setOwner(player);
        chunk.setType(PlotType.SOLD);
        player.sendMessage(Messages.BOUGHT_PLOT);
        return true;
    }
}
