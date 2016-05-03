package me.koenn.LTPT.listeners;

import me.koenn.LTPT.LTPTowny;
import me.koenn.LTPT.gui.Gui;
import me.koenn.LTPT.gui.guis.InfoGui;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.GuiReferences;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

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
        TownyPlayer player = TownyPlayer.getPlayer((e.getWhoClicked()).getUniqueId());
        String name = e.getInventory().getName();
        if (name.contains(GuiReferences.INFO_GUI_NAME) || name.startsWith("Plot Info: ") || name.contains("Towny Map") || name.startsWith("Towny Help") || name.startsWith("Plot Help")) {
            e.setCancelled(true);
            Gui gui = Gui.getOpenGui(player);
            if (gui != null) {
                gui.click(e);
            }
        }
        if (name.contains(GuiReferences.PLAYER_LIST_NAME)) {
            e.setCancelled(true);
        }
    }

    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        TownyPlayer player = TownyPlayer.getPlayer((e.getPlayer()).getUniqueId());
        if (player == null) {
            return;
        }
        String name = e.getInventory().getName();
        if (name.contains(GuiReferences.INFO_GUI_NAME) || name.startsWith("Plot Info: ") || name.startsWith("Towny Help") || name.startsWith("Plot Help") || name.contains(GuiReferences.PLAYER_LIST_NAME)) {
            Gui gui = Gui.getOpenGui(player);
            if (gui != null) {
                Gui.guis.remove(gui);
            }
        }
        if (e.getInventory().getName().contains(GuiReferences.PLAYER_LIST_NAME)) {
            InfoGui gui = new InfoGui(TownyPlayer.getPlayer(e.getPlayer().getUniqueId()), TownyPlayer.getPlayer(e.getPlayer().getUniqueId()).getTown());
            Gui.registerGui(gui);
            Bukkit.getScheduler().scheduleSyncDelayedTask(LTPTowny.getPlugin(), gui::open);
        }
    }
}
