package me.koenn.LTPT.gui;

import me.koenn.LTPT.towny.TownyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public abstract class Gui {

    public static ArrayList<Gui> guis = new ArrayList<>();

    private Inventory gui;
    private TownyPlayer player;

    public Gui(TownyPlayer player, String guiName) {
        this.player = player;
        this.gui = Bukkit.createInventory(null, 18, guiName);
    }

    public static Gui getOpenGui(TownyPlayer player) {
        for (Gui gui : guis) {
            if (gui.getPlayer().equals(player)) {
                return gui;
            }
        }
        return null;
    }

    public abstract void click(InventoryClickEvent e);

    public abstract void open();

    public Inventory getGui() {
        return gui;
    }

    public TownyPlayer getPlayer() {
        return player;
    }
}
