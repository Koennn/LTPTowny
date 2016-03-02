package me.koenn.LTPT.config;

import me.koenn.LTPT.towny.ClaimedChunk;
import me.koenn.LTPT.towny.Town;
import org.bukkit.World;

public class ChunkCoord {

    private int x;
    private int z;
    private World world;
    private Town town;

    public ChunkCoord(ClaimedChunk chunk) {
        this.x = chunk.getX();
        this.z = chunk.getZ();
        this.world = chunk.getBukkitChunk().getWorld();
        this.town = chunk.getTown();
    }

    public ChunkCoord(int x, int z, World world, Town town) {
        this.x = x;
        this.z = z;
        this.world = world;
        this.town = town;
    }

    public String getChunkString() {
        return x + "," + z + "," + world.getName() + "," + town.getName();
    }

    public ClaimedChunk createClaimedChunk() {
        return new ClaimedChunk(world.getChunkAt(x, z), town);
    }
}
