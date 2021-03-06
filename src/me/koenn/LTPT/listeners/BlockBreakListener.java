package me.koenn.LTPT.listeners;

import me.koenn.LTPT.chunk.ClaimedChunk;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.util.ChunkUtil;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        TownyPlayer player = TownyPlayer.getPlayer(e.getPlayer().getUniqueId());
        Location location = e.getBlock().getLocation();
        if (player != null) {
            if (!ChunkUtil.isClaimed(location)) {
                return;
            }
            if (!ChunkUtil.checkPerms(player, location)) {
                e.setCancelled(true);
                player.sendMessage(Messages.NO_BREAK_PERM);
            }
            ClaimedChunk chunk = ChunkUtil.getClaimedChunk(location);
            if (!chunk.getPermission().isDestroy()) {
                if (player.getRankValue() < 2 && !player.isPlotOwner(chunk)) {
                    e.setCancelled(true);
                    player.sendMessage(Messages.NO_BREAK_PERM);
                }
            }
        }
    }
}
