package me.koenn.LTPT.gui.guis;

import me.koenn.LTPT.gui.Gui;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.util.AdvancedOption;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class PlotHelpGui extends Gui {

    public PlotHelpGui(TownyPlayer player) {
        super(player, "Plot Help", 9);
        List<String> lore;

        lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "See and change plot settings");
        this.addOption(new AdvancedOption(Material.GRASS_PATH, "&b&l/t plot", AdvancedOption.placeHolderOption, lore, false));

        lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Set the price of a plot");
        lore.add(ChatColor.YELLOW + "Note: Only works if the plot is for sale!");
        this.addOption(new AdvancedOption(Material.EMERALD, "&b&l/t plot setprice", AdvancedOption.placeHolderOption, lore, false));

        lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Buy a plot");
        lore.add(ChatColor.YELLOW + "Note: Only works if the plot is for sale!");
        this.addOption(new AdvancedOption(Material.GOLD_INGOT, "&b&l/t plot buy", AdvancedOption.placeHolderOption, lore, false));
    }

    @Override
    public void open() {
        this.getPlayer().getBukkitPlayer().openInventory(super.getGui());
    }
}
