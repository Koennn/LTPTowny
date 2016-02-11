package me.koenn.LTPT.gui;

import me.koenn.LTPT.towny.Town;
import me.koenn.LTPT.towny.TownyPlayer;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CreateGui extends Gui {

    private Town town;

    public CreateGui(TownyPlayer player, Town town) {
        super(player, "Create Town");
        this.town = town;
    }

    @Override
    public void click(InventoryClickEvent e) {
        e.setCancelled(true);

    }

    @Override
    public void open() {

    }
}
