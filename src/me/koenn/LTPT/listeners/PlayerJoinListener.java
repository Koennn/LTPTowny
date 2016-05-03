package me.koenn.LTPT.listeners;

import me.koenn.LTPT.LTPTowny;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.towny.Town;
import me.koenn.LTPT.util.ExpUtil;
import me.koenn.LTPT.util.Logger;
import org.bukkit.Bukkit;
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
                Bukkit.getScheduler().scheduleSyncDelayedTask(LTPTowny.plugin, () -> player.sendMessage(Messages.WARN), 10);
                startExpCheck(player);
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
                    Bukkit.getScheduler().scheduleSyncDelayedTask(LTPTowny.plugin, () -> player.sendMessage(Messages.WARN), 10);
                    startExpCheck(player);
                    return;
                }
            }
        }
        Logger.debug("Creating new playerData for player" + e.getPlayer().getName());
    }

    private void startExpCheck(TownyPlayer player) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LTPTowny.plugin, () -> {
            if (player.isOnline()) {
                if (ExpUtil.getTotalExperience(player.getBukkitPlayer()) > 0) {
                    player.getBukkitPlayer().setAllowFlight(true);
                }
            }
        }, 0, 10);
    }
}
