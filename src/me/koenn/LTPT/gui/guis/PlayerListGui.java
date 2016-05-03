package me.koenn.LTPT.gui.guis;

import me.koenn.LTPT.gui.Gui;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.GuiReferences;
import me.koenn.LTPT.towny.Town;
import me.koenn.LTPT.util.AdvancedOption;
import me.koenn.LTPT.util.Option;
import me.koenn.LTPT.util.Util;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

public class PlayerListGui extends Gui {

    public PlayerListGui(TownyPlayer player) {
        super(player, GuiReferences.PLAYER_LIST_NAME, Util.getGuiSize(player.getTown().getPlayers().size()));
        Town town = player.getTown();
        String leader = town.getLeader().getOfflinePlayer().getName();
        ItemStack lskull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta lsm = (SkullMeta) lskull.getItemMeta();
        lsm.setOwner(leader);
        lsm.setDisplayName(translateAlternateColorCodes('&', GuiReferences.PLAYER_SKULL_NAME).replace("{player}", leader).replace("{rank}", town.getPlayers().get(town.getLeader()).toString()));
        lskull.setItemMeta(lsm);
        this.addOption(new AdvancedOption(lskull, Option.placeHolderOption, false));
        for (TownyPlayer resident : town.getPlayers().keySet()) {
            if (resident.isTownLeader()) {
                continue;
            }
            String pl = resident.getOfflinePlayer().getName();
            ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta sm = (SkullMeta) skull.getItemMeta();
            sm.setOwner(pl);
            sm.setDisplayName(translateAlternateColorCodes('&', GuiReferences.PLAYER_SKULL_NAME).replace("{player}", pl).replace("{rank}", town.getPlayers().get(resident).toString()));
            skull.setItemMeta(sm);
            this.addOption(new AdvancedOption(skull, Option.placeHolderOption, false));
        }
    }

    @Override
    public void open() {
        this.getPlayer().getBukkitPlayer().openInventory(super.getGui());
    }
}
