package me.koenn.LTPT.listeners;

import me.koenn.LTPT.gui.Gui;
import me.koenn.LTPT.towny.TownyPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) {
            return;
        }
        if (!e.getCurrentItem().hasItemMeta()) {
            return;
        }
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        TownyPlayer player = TownyPlayer.getPlayer((Player) e.getWhoClicked());
        String name = e.getInventory().getName();
        if (name.contains("Town Info")) {
            e.setCancelled(true);
            Gui gui = Gui.getOpenGui(player);
            if (gui != null) {
                gui.click(e);
            }
        }
    }
}
