package me.koenn.LTPT.util;

import me.koenn.LTPT.towny.ClaimedChunk;
import me.koenn.LTPT.towny.Town;
import org.bukkit.Chunk;

public class ChunkUtil {

    public static boolean isConnected(Town town, Chunk chunk) {
        for (ClaimedChunk claimedChunk : town.getLand()) {
            if (chunk.getX() - 1 == claimedChunk.getX() && chunk.getZ() == claimedChunk.getX()) {
                return true;
            }
            if (chunk.getX() + 1 == claimedChunk.getX() && chunk.getZ() == claimedChunk.getX()) {
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
