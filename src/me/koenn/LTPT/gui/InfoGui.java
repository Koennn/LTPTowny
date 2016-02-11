package me.koenn.LTPT.gui;

import me.koenn.LTPT.towny.Town;
import me.koenn.LTPT.towny.TownyPlayer;
import me.koenn.LTPT.util.AdvancedOption;
import me.koenn.LTPT.util.Option;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

import java.util.List;
import java.util.stream.Collectors;

public class InfoGui extends Gui {

    public InfoGui(TownyPlayer player, Town town) {
        super(player, "Town Info");
        super.addOption(new Option(Material.BOOK, "&6Town Name: &7" + town.getName(), null));
        List<String> players = town.getPlayers().keySet().stream().map(townyPlayer -> ChatColor.YELLOW + townyPlayer.getBukkitPlayer().getName()).collect(Collectors.toList());
        super.addOption(new AdvancedOption(Material.BOOK, "&6Players: &7" + town.getName(), null, players));
        super.addOption(new Option(Material.BED, "&6Teleport Home", player::teleportTownHome));
    }

    @Override
    public void open() {
        super.getPlayer().getBukkitPlayer().openInventory(super.getGui());
    }
}