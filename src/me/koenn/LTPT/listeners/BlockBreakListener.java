package me.koenn.LTPT.listeners;

import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.util.ChunkUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        TownyPlayer player = TownyPlayer.getPlayer(e.getPlayer().getUniqueId());
        if (player != null) {
            if (!ChunkUtil.checkPerms(player, e.getBlock().getLocation())) {
                e.setCancelled(true);
                player.sendMessage(Messages.NO_BREAK_PERM);
            }
            if (!ChunkUtil.getClaimedChunk(player.getLocation()).getPermission().isDestroy()) {
                if (!player.isTownLeader()) {
                    e.setCancelled(true);
                    player.sendMessage(Messages.NO_BREAK_PERM);
                }
            }
        }
    }
}
