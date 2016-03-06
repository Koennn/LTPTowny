package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.chunk.ClaimedChunk;
import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.util.ChunkUtil;

public class UnclaimCommand implements ITownyCommand {

    @Override
    public String getCommand() {
        return "unclaim";
    }

    @Override
    public String getUsage() {
        return "/town unclaim";
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        if (!player.hasTown()) {
            player.sendMessage(Messages.NO_TOWN);
            return true;
        }
        if (!player.isTownLeader()) {
            player.sendMessage(Messages.NO_PERMS);
            return true;
        }
        if (!ChunkUtil.isClaimed(player.getLocation())) {
            player.sendMessage(Messages.NOT_CLAIMED);
            return true;
        }
        ClaimedChunk claimedChunk = ChunkUtil.getClaimedChunk(player.getLocation());
        if (claimedChunk == null) {
            player.sendMessage(Messages.NOT_CLAIMED);
            return true;
        }
        if (!claimedChunk.getTown().equals(player.getTown())) {
            player.sendMessage(Messages.NOT_CLAIMED);
            return true;
        }
        String chunk = ChunkUtil.getClaimedChunk(player.getLocation()).getX() + ", " + ChunkUtil.getClaimedChunk(player.getLocation()).getZ();
        player.getTown().unclaimChunkAt(player.getLocation());
        player.sendMessage(Messages.UNCLAIMED.replace("{chunk}", chunk));
        return true;
    }
}
