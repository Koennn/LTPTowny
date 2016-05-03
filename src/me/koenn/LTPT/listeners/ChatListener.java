package me.koenn.LTPT.listeners;

import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import static net.md_5.bungee.api.ChatColor.translateAlternateColorCodes;

public class ChatListener implements Listener {

    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        TownyPlayer player = TownyPlayer.getPlayer(e.getPlayer().getUniqueId());
        if (player == null) {
            return;
        }
        e.setCancelled(true);
        if (!player.isInTownChat()) {
            PermissionUser user = PermissionsEx.getUser(player.getBukkitPlayer());
            String message = Messages.CHAT_FORMAT.replace("{rank}", user.getPrefix().replace("[", "").replace("]", "").replace(" ", ""));
            message = message.replace("{player}", ChatColor.stripColor(player.getBukkitPlayer().getDisplayName()).replace("~", "")).replace("{message}", ChatColor.stripColor(translateAlternateColorCodes('&', e.getMessage())));
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (TownyPlayer.getPlayer(online.getUniqueId()) == null) {
                    continue;
                }
                TownyPlayer.getPlayer(online.getUniqueId()).sendEmptyMessage(translateAlternateColorCodes('&', message));
            }
        } else {
            PermissionUser user = PermissionsEx.getUser(player.getBukkitPlayer());
            String message = Messages.TOWN_CHAT_FORMAT.replace("{rank}", user.getPrefix().replace("[", "").replace("]", "").replace(" ", ""));
            message = message.replace("{player}", ChatColor.stripColor(player.getBukkitPlayer().getDisplayName()).replace("~", "")).replace("{message}", ChatColor.stripColor(translateAlternateColorCodes('&', e.getMessage()))).replace("{town}", player.getTown().getName());
            for (TownyPlayer online : player.getTown().getPlayers().keySet()) {
                online.sendEmptyMessage(translateAlternateColorCodes('&', message));
            }
        }

    }
}
