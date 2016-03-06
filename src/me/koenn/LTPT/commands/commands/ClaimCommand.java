package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.effects.ChunkEffect;
import me.koenn.LTPT.listeners.PlayerMoveListener;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
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
        if (!player.hasTown()) {
            player.sendMessage(Messages.NO_TOWN);
            return true;
        }
        if (!player.isTownLeader()) {
            player.sendMessage(Messages.NO_PERMS);
            return true;
        }
        if (ChunkUtil.isClaimed(player.getLocation())) {
            player.sendMessage(Messages.ALREADY_CLAIMED.replace("{town}", ChunkUtil.getClaimedChunk(player.getLocation()).getTown().getName()));
            return true;
        }
        try {
            try {
                player.getTown().claimChunkAt(player.getLocation());
            } catch (IndexOutOfBoundsException ex) {
                player.sendMessage(Messages.CLAIM_LIMIT.replace("{amount}", String.valueOf(player.getTown().getLand().size())));
                return true;
            }
        } catch (IllegalArgumentException ex) {
            if (ex.getMessage().contains("overworld")) {
                player.sendMessage(Messages.ONLY_OVERWORLD);
            } else {
                player.sendMessage(Messages.NOT_CONNECTED);
            }
            return true;
        }
        String chunk = ChunkUtil.getClaimedChunk(player.getLocation()).getX() + ", " + ChunkUtil.getClaimedChunk(player.getLocation()).getZ();
        player.sendMessage(Messages.CLAIMED.replace("{chunk}", chunk));
        PlayerMoveListener.removeLastLocation(player);
        new ChunkEffect().play(ChunkUtil.getClaimedChunk(player.getLocation()).getBukkitChunk(), (int) Math.round(player.getLocation().getY()));
        return true;
    }
}
