package me.koenn.LTPT.listeners;

import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.towny.ClaimedChunk;
import me.koenn.LTPT.towny.TownyPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (e.getPlayer().isOp()) {
            return;
        }
        TownyPlayer player = TownyPlayer.getPlayer(e.getPlayer());
        if (!ClaimedChunk.checkPerms(player, e.getBlock().getLocation())) {
            e.setCancelled(true);
            player.sendMessage(Messages.NO_BREAK_PERM);
        }
    }
}
