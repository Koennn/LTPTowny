package me.koenn.LTPT.gui;

import me.koenn.LTPT.player.TownyPlayer;
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

    protected Gui(TownyPlayer player, String guiName) {
        this.player = player;
        this.gui = Bukkit.createInventory(null, 9, guiName);
    }

    protected Gui(TownyPlayer player, String guiName, int size) {
        this.player = player;
        this.gui = Bukkit.createInventory(null, size, guiName);
    }

    public static void registerGui(Gui gui) {
        guis.add(gui);
        Logger.debug("Registered new gui '" + gui.toString().split("@")[1] + "'");
    }

    public static Gui getOpenGui(TownyPlayer player) {
        for (Gui gui : guis) {
            if (gui.getPlayer().getBukkitPlayer().getUniqueId().equals(player.getBukkitPlayer().getUniqueId())) {
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
        this.options.stream().filter(option -> option.getOption().getType().equals(item.getType()) && option.getOption().getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())).forEach(option -> {
            if (option != Option.placeHolderOption) {
                if (option.run()) {
                    this.player.getBukkitPlayer().closeInventory();
                }
            }
        });
    }

    public abstract void open();

    public Inventory getGui() {
        return gui;
    }

    public TownyPlayer getPlayer() {
        return player;
    }
}
