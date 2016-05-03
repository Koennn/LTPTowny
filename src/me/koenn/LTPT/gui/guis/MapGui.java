package me.koenn.LTPT.gui.guis;

import me.koenn.LTPT.chunk.ClaimedChunk;
import me.koenn.LTPT.gui.Gui;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.towny.Town;
import me.koenn.LTPT.util.AdvancedOption;
import me.koenn.LTPT.util.ChunkUtil;
import me.koenn.LTPT.util.Util;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapGui extends Gui {

    private static HashMap<Town, ChatColor> colors = new HashMap<>();

    public MapGui(TownyPlayer player) {
        super(player, "Towny Map", 54);
        Location location = player.getLocation();
        World world = location.getWorld();
        Chunk chunk = world.getChunkAt(location);
        for (int z = chunk.getZ() - 3; z <= chunk.getZ() + 2; z++) {
            for (int x = chunk.getX() - 4; x <= chunk.getX() + 4; x++) {
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.YELLOW + "x = " + x + ", z = " + z);
                ClaimedChunk claimedChunk = ChunkUtil.getClaimedChunk(x, z);
                if (chunk.getX() == x && chunk.getZ() == z) {
                    this.addOption(new AdvancedOption(Material.EMERALD, ChatColor.GOLD + "You", AdvancedOption.placeHolderOption, lore, false));
                    continue;
                }
                if (claimedChunk != null) {
                    if (!colors.containsKey(claimedChunk.getTown())) {
                        colors.put(claimedChunk.getTown(), Util.randomColor());
                    }
                    this.addOption(new AdvancedOption(Material.GRASS_PATH, colors.get(claimedChunk.getTown()) + claimedChunk.getTown().getName(), AdvancedOption.placeHolderOption, lore, false));
                } else {
                    this.addOption(new AdvancedOption(Material.GRASS, ChatColor.GREEN + "Wilderness", AdvancedOption.placeHolderOption, lore, false));
                }
            }
        }
    }

    @Override
    public void open() {
        this.getPlayer().getBukkitPlayer().openInventory(super.getGui());
    }
}
