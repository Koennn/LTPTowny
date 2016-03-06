package me.koenn.LTPT.gui.guis;

import me.koenn.LTPT.chunk.ClaimedChunk;
import me.koenn.LTPT.chunk.PlotType;
import me.koenn.LTPT.gui.Gui;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.util.AdvancedOption;
import me.koenn.LTPT.util.Option;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;

public class ChunkGui extends Gui {

    public ChunkGui(TownyPlayer player, ClaimedChunk chunk) {
        super(player, "Plot Info: " + chunk.getX() + ", " + chunk.getZ() + ", " + chunk.getType().toString(chunk.getPrice()));

        if (player.isTownLeader()) {
            this.addOption(new AdvancedOption(Material.EMERALD, "&6&lSet ForSale", () -> {
                chunk.setType(PlotType.FOR_SALE);
                chunk.setPrice(0);
                this.sync(player, chunk);
            }, false));

            this.addOption(new AdvancedOption(Material.DIAMOND, "&6&lSet Shop", () -> {
                chunk.setType(PlotType.SHOP);
                this.sync(player, chunk);
            }, false));

            this.addOption(new AdvancedOption(Material.QUARTZ_BLOCK, "&6&lSet Embassy", () -> {
                chunk.setType(PlotType.EMBASSY);
                this.sync(player, chunk);
            }, false));

            this.addOption(new AdvancedOption(Material.GRASS, "&6&lSet Normal", () -> {
                chunk.setType(PlotType.NORMAL);
                this.sync(player, chunk);
            }, false));
        }

        if (player.isPlotOwner(chunk)) {
            this.addOption(new AdvancedOption(Material.GOLD_INGOT, "&6&lSell Plot", () -> {
                chunk.setType(PlotType.FOR_SALE);
                chunk.setOwner(null);
                this.sync(player, chunk);
            }, false));
        }

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Currently: " + chunk.getPermission().getPvp());
        this.addOption(new AdvancedOption(Material.DIAMOND_SWORD, "&6Toggle PvP", () -> {
            chunk.getPermission().togglePvp();
            player.sendMessage(Messages.TOGGLE_PERM.replace("{perm}", "PvP").replace("{on/off}", chunk.getPermission().getPvp().toLowerCase()));
            this.sync(player, chunk);
        }, lore, false));

        lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Currently: " + chunk.getPermission().getBuild());
        this.addOption(new AdvancedOption(Material.WOOD, "&6Toggle Build", () -> {
            chunk.getPermission().toggleBuild();
            player.sendMessage(Messages.TOGGLE_PERM.replace("{perm}", "Build").replace("{on/off}", chunk.getPermission().getBuild().toLowerCase()));
            this.sync(player, chunk);
        }, lore, false));

        lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Currently: " + chunk.getPermission().getDestroy());
        this.addOption(new AdvancedOption(Material.DIAMOND_PICKAXE, "&6Toggle Destroy", () -> {
            chunk.getPermission().toggleDestroy();
            player.sendMessage(Messages.TOGGLE_PERM.replace("{perm}", "Destroy").replace("{on/off}", chunk.getPermission().getDestroy().toLowerCase()));
            this.sync(player, chunk);
        }, lore, false));

        lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Currently: " + chunk.getPermission().getAccess());
        this.addOption(new AdvancedOption(Material.CHEST, "&6Toggle Access", () -> {
            chunk.getPermission().toggleAccess();
            player.sendMessage(Messages.TOGGLE_PERM.replace("{perm}", "Access").replace("{on/off}", chunk.getPermission().getAccess().toLowerCase()));
            this.sync(player, chunk);
        }, lore, false));

        this.addOption(new Option(Material.BARRIER, "&c&lUnclaim Plot", () -> {
            player.getTown().unclaimChunkAt(player.getLocation());
            String chunkName = chunk.getX() + ", " + chunk.getZ();
            player.sendMessage(Messages.UNCLAIMED.replace("{chunk}", chunkName));
        }));
    }

    private void sync(TownyPlayer player, ClaimedChunk chunk) {
        ChunkGui gui = new ChunkGui(player, chunk);
        Gui.registerGui(gui);
        gui.open();
    }

    @Override
    public void open() {
        this.getPlayer().getBukkitPlayer().openInventory(super.getGui());
    }
}
