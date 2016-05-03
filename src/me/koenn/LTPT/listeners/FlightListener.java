package me.koenn.LTPT.listeners;

import me.koenn.LTPT.fly.FlyTimer;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.util.ExpUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.HashMap;

public class FlightListener implements Listener {

    private static HashMap<TownyPlayer, FlyTimer> flyTimers = new HashMap<>();

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent e) {
        if (e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            TownyPlayer player = TownyPlayer.getPlayer(e.getPlayer().getUniqueId());
            if (player == null) {
                return;
            }
            if (e.isFlying()) {
                flyTimers.put(player, new FlyTimer(player, ExpUtil.getTotalExperience(player.getBukkitPlayer())));
                flyTimers.get(player).start();
            } else {
                if (flyTimers.containsKey(player)) {
                    flyTimers.get(player).stop();
                    flyTimers.remove(player);
                }
            }
        }
    }
}
