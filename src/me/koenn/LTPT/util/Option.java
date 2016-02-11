package me.koenn.LTPT.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

public class Option {

    private ItemStack option;
    private Runnable action;
    private String name;

    public Option(Material icon, String name, Runnable action) {
        this.action = action;
        this.name = translateAlternateColorCodes('&', name);
        this.option = new ItemStack(icon, 1);
        ItemMeta meta = this.option.getItemMeta();
        meta.setDisplayName(this.getName());
        this.option.setItemMeta(meta);
    }

    public void run() {
        if (this.action != null) {
            this.action.run();
        }
    }

    public String getName() {
        return name;
    }

    public ItemStack getOption() {
        return option;
    }
}
