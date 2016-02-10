package me.koenn.LTPT.towny;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;

public class ClaimedChunk {

    public static ArrayList<ClaimedChunk> claimedChunks = new ArrayList<>();

    private Chunk chunk;
    private int x;
    private int z;
    private Town town;

    public ClaimedChunk(Chunk chunk, Town town) throws IllegalArgumentException {
        if (chunk.getWorld().getEnvironment() != World.Environment.NORMAL) {
            throw new IllegalArgumentException("You cannot claim in the nether");
        }
        this.chunk = chunk;
        this.town = town;
        this.x = chunk.getX();
        this.z = chunk.getZ();
        claimedChunks.add(this);
    }

    public static ClaimedChunk getClaimedChunk(Location location) {
        Chunk chunk = location.getWorld().getChunkAt(location);
        for (ClaimedChunk claimedChunk : claimedChunks) {
            if (claimedChunk.getX() == chunk.getX() && claimedChunk.getZ() == chunk.getZ()) {
                return claimedChunk;
            }
        }
        return null;
    }

    public static boolean checkPerms(TownyPlayer player, Location location) {
        Chunk chunk = location.getWorld().getChunkAt(location);
        for (ClaimedChunk claimedChunk : claimedChunks) {
            if (chunk.getX() == claimedChunk.getX() && chunk.getZ() == claimedChunk.getZ()) {
                if (!claimedChunk.getTown().getPlayers().containsKey(player)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Chunk getChunk() {
        return chunk;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public Town getTown() {
        return town;
    }
}
