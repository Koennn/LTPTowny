package me.koenn.LTPT.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class AdvancedOption extends Option {

    public AdvancedOption(Material icon, String name, Runnable action, List<String> lore, boolean close) {
        super(icon, name, action);
        ItemMeta meta = super.getOption().getItemMeta();
        meta.setLore(lore);
        super.getOption().setItemMeta(meta);
        super.close = close;
    }

    public AdvancedOption(ItemStack icon, Runnable action, boolean close) {
        super(icon, action);
        ItemMeta meta = super.getOption().getItemMeta();
        super.getOption().setItemMeta(meta);
        super.close = close;
    }

    public AdvancedOption(Material icon, String name, Runnable action, boolean close) {
        super(icon, name, action);
        super.close = close;
    }
}
