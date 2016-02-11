package me.koenn.LTPT.util;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class AdvancedOption extends Option {

    public AdvancedOption(Material icon, String name, Runnable action, List<String> lore) {
        super(icon, name, action);
        ItemMeta meta = super.getOption().getItemMeta();
        meta.setLore(lore);
        super.getOption().setItemMeta(meta);
    }
}
