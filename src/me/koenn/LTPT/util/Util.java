package me.koenn.LTPT.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

import java.util.Random;

public class Util {

    public static int getGuiSize(int options) {
        for (int i = options; i < 81; i++) {
            if (i % 9 == 0) {
                return i;
            }
        }
        return 81;
    }

    public static boolean isClickable(Material block) {
        String name = block.name().toLowerCase();
        return name.endsWith("chest") || name.endsWith("door") || block.equals(Material.DROPPER) || block.equals(Material.DISPENSER) || name.endsWith("gate") || name.endsWith("button") || block.equals(Material.LEVER) || name.endsWith("furnace") || block.equals(Material.ANVIL) || block.equals(Material.HOPPER) || block.equals(Material.ENCHANTMENT_TABLE);
    }

    public static ChatColor randomColor() {
        int color = new Random().nextInt(14) + 1;
        switch (color) {
            case 1:
                return ChatColor.DARK_BLUE;
            case 2:
                return ChatColor.DARK_GREEN;
            case 3:
                return ChatColor.DARK_AQUA;
            case 4:
                return ChatColor.DARK_RED;
            case 5:
                return ChatColor.DARK_PURPLE;
            case 6:
                return ChatColor.GOLD;
            case 7:
                return ChatColor.GRAY;
            case 8:
                return ChatColor.DARK_GRAY;
            case 9:
                return ChatColor.BLUE;
            case 10:
                return ChatColor.LIGHT_PURPLE;
            case 11:
                return ChatColor.AQUA;
            case 12:
                return ChatColor.RED;
            case 13:
                return ChatColor.YELLOW;
            case 14:
                return ChatColor.WHITE;
            default:
                return ChatColor.BLACK;
        }
    }
}
