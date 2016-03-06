package me.koenn.LTPT.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

public class Option {

    public static Runnable placeHolderOption = null;
    protected boolean close = true;
    private ItemStack option;
    private Runnable action;
    private String name;

    public Option(Material icon, String name, Runnable action) {
        this.action = action;
        this.name = translateAlternateColorCodes('&', name);
        this.option = new ItemStack(icon, 1);
        ItemMeta meta = this.option.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.setDisplayName(this.getName());
        this.option.setItemMeta(meta);
    }

    protected Option(ItemStack icon, Runnable action) {
        this.action = action;
        this.option = icon;
    }

    public boolean run() {
        if (this.action != null) {
            this.action.run();
            return close;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public ItemStack getOption() {
        return option;
    }
}
