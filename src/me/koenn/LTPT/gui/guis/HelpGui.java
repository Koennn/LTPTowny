package me.koenn.LTPT.gui.guis;

import me.koenn.LTPT.gui.Gui;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.util.AdvancedOption;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class HelpGui extends Gui {

    public HelpGui(TownyPlayer player) {
        super(player, "Towny Help", 18);

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Create a town");
        this.addOption(new AdvancedOption(Material.SMOOTH_BRICK, "&b&l/t create", AdvancedOption.placeHolderOption, lore, false));

        lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Remove your town");
        this.addOption(new AdvancedOption(Material.BARRIER, "&b&l/t remove", AdvancedOption.placeHolderOption, lore, false));

        lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Invite a player to your town");
        this.addOption(new AdvancedOption(Material.MONSTER_EGG, "&b&l/t invite", AdvancedOption.placeHolderOption, lore, false));

        lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Accept a town invite");
        this.addOption(new AdvancedOption(Material.PAPER, "&b&l/t accept", AdvancedOption.placeHolderOption, lore, false));

        lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "See and change plot settings");
        this.addOption(new AdvancedOption(Material.GRASS_PATH, "&b&l/t plot", AdvancedOption.placeHolderOption, lore, false));

        lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Set your town home");
        this.addOption(new AdvancedOption(Material.WOOD, "&b&l/t sethome", AdvancedOption.placeHolderOption, lore, false));

        lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Teleport to your town home");
        this.addOption(new AdvancedOption(Material.BED, "&b&l/t home", AdvancedOption.placeHolderOption, lore, false));

        lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Leave your town");
        this.addOption(new AdvancedOption(Material.WOOD_DOOR, "&b&l/t leave", AdvancedOption.placeHolderOption, lore, false));

        lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Claim a plot");
        this.addOption(new AdvancedOption(Material.IRON_FENCE, "&b&l/t claim", AdvancedOption.placeHolderOption, lore, false));

        lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Unclaim a plot");
        this.addOption(new AdvancedOption(Material.IRON_DOOR, "&b&l/t unclaim", AdvancedOption.placeHolderOption, lore, false));

        lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "See the plot borders");
        this.addOption(new AdvancedOption(Material.FENCE, "&b&l/t overlay", AdvancedOption.placeHolderOption, lore, false));

        lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Toggle town chat");
        this.addOption(new AdvancedOption(Material.BOOK_AND_QUILL, "&b&l/t chat", AdvancedOption.placeHolderOption, lore, false));
    }

    @Override
    public void open() {
        this.getPlayer().getBukkitPlayer().openInventory(super.getGui());
    }
}
