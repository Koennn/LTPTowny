package me.koenn.LTPT.util;

import me.koenn.LTPT.chunk.ClaimedChunk;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.towny.Town;
import org.bukkit.Chunk;
import org.bukkit.Location;

public class ChunkUtil {

    public static boolean isClaimed(Location location) {
        Chunk chunk = location.getWorld().getChunkAt(location);
        for (ClaimedChunk claimedChunk : ClaimedChunk.claimedChunks) {
            if (claimedChunk.getX() == chunk.getX() && claimedChunk.getZ() == chunk.getZ()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isClaimed(Chunk chunk) {
        for (ClaimedChunk claimedChunk : ClaimedChunk.claimedChunks) {
            if (claimedChunk.getX() == chunk.getX() && claimedChunk.getZ() == chunk.getZ()) {
                return true;
            }
        }
        return false;
    }

    public static ClaimedChunk getClaimedChunk(Location location) {
        Chunk chunk = location.getWorld().getChunkAt(location);
        for (ClaimedChunk claimedChunk : ClaimedChunk.claimedChunks) {
            if (claimedChunk.getX() == chunk.getX() && claimedChunk.getZ() == chunk.getZ()) {
                return claimedChunk;
            }
        }
        return null;
    }

    public static ClaimedChunk getClaimedChunk(int chunkx, int chunkz) {
        for (ClaimedChunk claimedChunk : ClaimedChunk.claimedChunks) {
            if (claimedChunk.getX() == chunkx && claimedChunk.getZ() == chunkz) {
                return claimedChunk;
            }
        }
        return null;
    }

    public static boolean checkPerms(TownyPlayer player, Location location) {
        Chunk chunk = location.getWorld().getChunkAt(location);
        for (ClaimedChunk claimedChunk : ClaimedChunk.claimedChunks) {
            if (chunk.getX() == claimedChunk.getX() && chunk.getZ() == claimedChunk.getZ()) {
                if (!claimedChunk.getTown().getPlayers().containsKey(player)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isConnected(Town town, Chunk chunk) {
        for (ClaimedChunk claimedChunk : town.getLand()) {
            if (chunk.getX() - 1 == claimedChunk.getX() && chunk.getZ() == claimedChunk.getZ()) {
                return true;
            }
            if (chunk.getX() + 1 == claimedChunk.getX() && chunk.getZ() == claimedChunk.getZ()) {
                return true;
            }
            if (chunk.getX() == claimedChunk.getX() && chunk.getZ() - 1 == claimedChunk.getZ()) {
                return true;
            }
            if (chunk.getX() == claimedChunk.getX() && chunk.getZ() + 1 == claimedChunk.getZ()) {
                return true;
            }
        }
        return false;
    }
}
