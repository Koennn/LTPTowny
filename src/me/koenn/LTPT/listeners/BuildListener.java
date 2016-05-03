package me.koenn.LTPT.listeners;

import me.koenn.LTPT.chunk.ClaimedChunk;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.util.ChunkUtil;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildListener implements Listener {

    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        TownyPlayer player = TownyPlayer.getPlayer(e.getPlayer().getUniqueId());
        Location location = e.getBlockPlaced().getLocation();
        if (player != null) {
            if (!ChunkUtil.isClaimed(location)) {
                return;
            }
            if (!ChunkUtil.checkPerms(player, location)) {
                e.setCancelled(true);
                player.sendMessage(Messages.NO_PLACE_PERM);
                player.getBukkitPlayer().updateInventory();
            }
            ClaimedChunk chunk = ChunkUtil.getClaimedChunk(location);
            if (!chunk.getPermission().isBuild()) {
                if (player.getRankValue() < 2 && !player.isPlotOwner(chunk)) {
                    e.setCancelled(true);
                    player.sendMessage(Messages.NO_PLACE_PERM);
                    player.getBukkitPlayer().updateInventory();
                }
            }
        }
    }
}
