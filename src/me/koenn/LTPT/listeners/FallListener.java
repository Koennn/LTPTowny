package me.koenn.LTPT.listeners;

import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.util.ExpUtil;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FallListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onFallDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if (((Player) event.getEntity()).getGameMode().equals(GameMode.SURVIVAL)) {
                TownyPlayer player = TownyPlayer.getPlayer(event.getEntity().getUniqueId());
                if (player == null) {
                    return;
                }
                ExpUtil.setTotalExperience(player.getBukkitPlayer(), ExpUtil.getTotalExperience(player.getBukkitPlayer()) - (int) Math.round(event.getDamage()));
            }
        }
    }
}
