package me.koenn.LTPT.listeners;

import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.towny.Town;
import me.koenn.LTPT.util.Logger;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player joined = e.getPlayer();
        if (!joined.hasPlayedBefore()) {
            joined.getInventory().addItem(
                    new ItemStack(Material.STONE_SWORD, 1),
                    new ItemStack(Material.STONE_PICKAXE, 1),
                    new ItemStack(Material.STONE_AXE, 1),
                    new ItemStack(Material.STONE_SPADE, 1),
                    new ItemStack(Material.STONE_HOE, 1),
                    new ItemStack(Material.SHIELD, 1),
                    new ItemStack(Material.BOAT, 1),
                    new ItemStack(Material.AIR),
                    new ItemStack(Material.COOKED_BEEF, 16)
            );
        }
        for (TownyPlayer player : TownyPlayer.getAllRegisteredPlayers().values()) {
            if (player.getBukkitPlayer() == null) {
                continue;
            }
            if (player.getBukkitPlayer().getUniqueId().equals(e.getPlayer().getUniqueId())) {
                Logger.debug("Loaded existing playerData for player " + e.getPlayer().getName());
                return;
            }
        }
        for (Town town : Town.getAllRegisteredTowns()) {
            for (TownyPlayer player : town.getPlayers().keySet()) {
                if (player.getBukkitPlayer() == null) {
                    continue;
                }
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
