package me.koenn.LTPT.listeners;

import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PvpListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        TownyPlayer victim = TownyPlayer.getPlayer((e.getEntity()).getUniqueId());
        if (victim == null) {
            return;
        }
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        TownyPlayer damager = TownyPlayer.getPlayer((e.getDamager()).getUniqueId());
        if (damager == null) {
            return;
        }
        if (!victim.hasTown()) {
            return;
        }
        if (!damager.hasTown()) {
            return;
        }
        if (victim.getTown().equals(damager.getTown())) {
            e.setCancelled(true);
            damager.sendMessage(Messages.NO_PVP_FRIENDLY);
        }
    }
}
