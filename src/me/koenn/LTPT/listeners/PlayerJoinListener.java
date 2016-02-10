package me.koenn.LTPT.listeners;

import me.koenn.LTPT.towny.TownyPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        new TownyPlayer(e.getPlayer());
    }
}
