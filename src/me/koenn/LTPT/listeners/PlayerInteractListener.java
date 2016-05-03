package me.koenn.LTPT.listeners;

import me.koenn.LTPT.chunk.ClaimedChunk;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.util.ChunkUtil;
import me.koenn.LTPT.util.Util;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (!Util.isClickable(e.getClickedBlock().getType())) {
            return;
        }
        Location location = e.getClickedBlock().getLocation();
        TownyPlayer player = TownyPlayer.getPlayer(e.getPlayer().getUniqueId());
        if (player == null) {
            return;
        }
        if (!ChunkUtil.checkPerms(player, location)) {
            e.setCancelled(true);
            player.sendMessage(Messages.NO_USE_PERM);
        }
        if (!ChunkUtil.isClaimed(location)) {
            return;
        }
        ClaimedChunk chunk = ChunkUtil.getClaimedChunk(location);
        if (!chunk.getPermission().isAccess()) {
            if (player.getRankValue() < 2 && !player.isPlotOwner(chunk)) {
                e.setCancelled(true);
                player.sendMessage(Messages.NO_USE_PERM);
            }
        }
    }
}