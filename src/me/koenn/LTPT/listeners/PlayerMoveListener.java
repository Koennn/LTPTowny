package me.koenn.LTPT.listeners;

import me.koenn.LTPT.chunk.ClaimedChunk;
import me.koenn.LTPT.chunk.PlotType;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.towny.Town;
import me.koenn.LTPT.util.ChunkUtil;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class PlayerMoveListener implements Listener {

    private static HashMap<TownyPlayer, Chunk> lastLocation = new HashMap<>();
    private static HashMap<TownyPlayer, Town> lastTown = new HashMap<>();

    public static void removeLastLocation(TownyPlayer player) {
        if (lastLocation.containsKey(player)) {
            lastLocation.remove(player);
        }
    }

    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        TownyPlayer player = TownyPlayer.getPlayer(e.getPlayer().getUniqueId());
        if (player == null) {
            return;
        }
        Chunk lastChunk;
        if (lastLocation.containsKey(player)) {
            lastChunk = lastLocation.get(player);
        } else {
            lastLocation.put(player, player.getLocation().getChunk());
            lastChunk = lastLocation.get(player);
        }
        Chunk newChunk = player.getLocation().getChunk();
        if (lastChunk.getX() == newChunk.getX() && lastChunk.getZ() == newChunk.getZ()) {
            lastLocation.put(player, newChunk);
            return;
        }
        lastLocation.put(player, newChunk);
        Location location = player.getLocation();
        if (!ChunkUtil.isClaimed(location)) {
            if (lastTown.containsKey(player)) {
                lastTown.remove(player);
                player.sendEmptyMessage(Messages.ENTERED_CHUNK.replace("{color}", ChatColor.DARK_GREEN.toString()).replace("{town}", "Wilderness"));
            }
            return;
        } else {
            ClaimedChunk chunk = ChunkUtil.getClaimedChunk(player.getLocation());
            if (chunk != null) {
                if (!chunk.getType().equals(PlotType.NORMAL)) {
                    String type = ChunkUtil.getClaimedChunk(location).getType().toString();
                    if (chunk.getType().equals(PlotType.FOR_SALE)) {
                        type = type + " $" + chunk.getPrice();
                    }
                    if (chunk.getType().equals(PlotType.SOLD)) {
                        type = chunk.getOwner().getName();
                    }
                    player.sendEmptyMessage(Messages.ENTERED_PLOT.replace("{type}", type));
                }
            }
        }
        ClaimedChunk chunk = ChunkUtil.getClaimedChunk(location);
        if (chunk == null) {
            return;
        }
        if (lastTown.containsKey(player)) {
            if (chunk.getTown().equals(lastTown.get(player))) {
                return;
            }
            lastTown.put(player, chunk.getTown());
        } else {
            lastTown.put(player, chunk.getTown());
        }
        String color = ChatColor.DARK_GRAY.toString();
        if (player.hasTown()) {
            if (chunk.getTown().equals(player.getTown())) {
                color = ChatColor.GREEN.toString();
            }
        }
        player.sendEmptyMessage(Messages.ENTERED_CHUNK.replace("{color}", color).replace("{town}", chunk.getTown().getName()));
    }
}
