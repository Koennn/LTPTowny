package me.koenn.LTPT.listeners;

import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.towny.Town;
import me.koenn.LTPT.util.Logger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        for (TownyPlayer player : TownyPlayer.getAllRegisteredPlayers().values()) {
            if (player.getBukkitPlayer().getUniqueId().equals(e.getPlayer().getUniqueId())) {
                Logger.debug("Loaded existing playerData for player " + e.getPlayer().getName());
                return;
            }
        }
        for (Town town : Town.getAllRegisteredTowns()) {
            for (TownyPlayer player : town.getPlayers().keySet()) {
                if (player.getBukkitPlayer().getUniqueId().equals(e.getPlayer().getUniqueId())) {
                    Logger.debug("Loaded existing playerData for player " + e.getPlayer().getName());
                    return;
                }
            }
        }
        Logger.debug("Creating new playerData for player" + e.getPlayer().getName());
        new TownyPlayer(e.getPlayer().getUniqueId());
    }
}
