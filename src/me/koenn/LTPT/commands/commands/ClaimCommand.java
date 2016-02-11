package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.listeners.PlayerMoveListener;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.towny.TownyPlayer;
import me.koenn.LTPT.util.ChunkUtil;

public class ClaimCommand implements ITownyCommand {

    @Override
    public String getCommand() {
        return "claim";
    }

    @Override
    public String getUsage() {
        return "/town claim";
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        if (!player.isTownLeader()) {
            player.sendMessage(Messages.NO_PERMS);
            return true;
        }
        if (ChunkUtil.isClaimed(player.getLocation())) {
            player.sendMessage(Messages.ALREADY_CLAIMED.replace("{town}", ChunkUtil.getClaimedChunk(player.getLocation()).getTown().getName()));
            return true;
        }
        try {
            player.getTown().claimChunkAt(player.getLocation());
        } catch (IllegalArgumentException ex) {
            player.sendMessage(Messages.NOT_CONNECTED);
            return true;
        }
        String chunk = ChunkUtil.getClaimedChunk(player.getLocation()).getX() + ", " + ChunkUtil.getClaimedChunk(player.getLocation()).getZ();
        player.sendMessage(Messages.CLAIMED.replace("{chunk}", chunk));
        PlayerMoveListener.removeLastLocation(player);
        return true;
    }
}
