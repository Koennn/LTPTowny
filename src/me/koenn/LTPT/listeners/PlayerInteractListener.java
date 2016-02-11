package me.koenn.LTPT.listeners;

import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.towny.TownyPlayer;
import me.koenn.LTPT.util.ChunkUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        TownyPlayer player = TownyPlayer.getPlayer(e.getPlayer());
        if (!ChunkUtil.checkPerms(player, e.getClickedBlock().getLocation())) {
            e.setCancelled(true);
            player.sendMessage(Messages.NO_USE_PERM);
        }
    }
}