package me.koenn.LTPT.gui;

import me.koenn.LTPT.towny.TownyPlayer;
import me.koenn.LTPT.util.Logger;
import me.koenn.LTPT.util.Option;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public abstract class Gui {

    public static ArrayList<Gui> guis = new ArrayList<>();
    private ArrayList<Option> options = new ArrayList<>();
    private Inventory gui;
    private TownyPlayer player;

    public Gui(TownyPlayer player, String guiName) {
        this.player = player;
        this.gui = Bukkit.createInventory(null, 18, guiName);
    }

    public static void registerGui(Gui gui) {
        guis.add(gui);
        Logger.debug("Registered new gui '" + gui.toString().split("@")[1] + "'");
    }

    public static Gui getOpenGui(TownyPlayer player) {
        for (Gui gui : guis) {
            if (gui.getPlayer().equals(player)) {
                return gui;
            }
        }
        return null;
    }

    protected void addOption(Option option) {
        this.options.add(option);
        this.gui.addItem(option.getOption());
    }

    public void click(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        this.options.stream().filter(option -> option.getOption().getType().equals(item.getType())).forEach(me.koenn.LTPT.util.Option::run);
    }

    public abstract void open();

    public Inventory getGui() {
        return gui;
    }

    public TownyPlayer getPlayer() {
        return player;
    }
}
