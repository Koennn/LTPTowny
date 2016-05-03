package me.koenn.LTPT.gui.guis;

import me.koenn.LTPT.gui.Gui;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.GuiReferences;
import me.koenn.LTPT.towny.Town;
import me.koenn.LTPT.util.AdvancedOption;
import me.koenn.LTPT.util.Option;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

import java.util.List;
import java.util.stream.Collectors;

public class InfoGui extends Gui {

    public InfoGui(TownyPlayer player, Town town) {
        super(player, GuiReferences.INFO_GUI_NAME);
        this.addOption(new Option(Material.BOOK, "&6Town Name: &7" + town.getName(), Option.placeHolderOption));
        List<String> players = town.getPlayers().keySet().stream().map(townyPlayer -> ChatColor.YELLOW + townyPlayer.getOfflinePlayer().getName()).collect(Collectors.toList());
        this.addOption(new AdvancedOption(Material.BOOK, "&6Players:", () -> {
            PlayerListGui gui = new PlayerListGui(player);
            Gui.registerGui(gui);
            gui.open();
        }, players, false));
        this.addOption(new Option(Material.BED, "&6Teleport Home", player::teleportTownHome));
        this.addOption(new Option(Material.WOOD_DOOR, "&6Leave Town", player::leaveTown));
        int maxLand = town.getPlayers().size() * 4;
        if (player.getBukkitPlayer().getName().equals("JermainO_o")) {
            maxLand = 1000000;
        }
        if (player.isTownLeader()) {
            this.addOption(new Option(Material.BARRIER, "&c&lDelete Town!", town::removeTown));
            this.addOption(new AdvancedOption(Material.SIGN, "&6Maximum amount of plots: &e" + maxLand, Option.placeHolderOption, false));
        }
    }

    @Override
    public void open() {
        this.getPlayer().getBukkitPlayer().openInventory(super.getGui());
    }
}
