package me.koenn.LTPT.listeners;

import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.towny.TownyPlayer;
import me.koenn.LTPT.util.ChunkUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        TownyPlayer player = TownyPlayer.getPlayer(e.getPlayer());
        if (!ChunkUtil.checkPerms(player, e.getBlock().getLocation())) {
            e.setCancelled(true);
            player.sendMessage(Messages.NO_BREAK_PERM);
        }
    }
}
