package me.koenn.LTPT.gui;

import me.koenn.LTPT.towny.TownyPlayer;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TownGui extends Gui {


    public TownGui(TownyPlayer player) {
        super(player, "Town Info");
    }

    @Override
    public void click(InventoryClickEvent e) {
        e.setCancelled(true);
    }

    @Override
    public void open() {
        super.getPlayer().getBukkitPlayer().openInventory(super.getGui());
    }
}
