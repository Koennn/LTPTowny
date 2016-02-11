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
        if (e.getClickedInventory().getName().equals("Town Info")) {
            Gui.guis.stream().filter(gui -> gui.getPlayer().equals(player)).forEach(gui -> gui.click(e));
        }
    }
}
