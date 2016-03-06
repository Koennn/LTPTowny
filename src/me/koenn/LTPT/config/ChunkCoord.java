package me.koenn.LTPT.config;

import me.koenn.LTPT.chunk.ChunkPermission;
import me.koenn.LTPT.chunk.ClaimedChunk;
import me.koenn.LTPT.chunk.PlotType;
import me.koenn.LTPT.towny.Town;
import org.bukkit.World;

import java.util.UUID;

public class ChunkCoord {

    private int x;
    private int z;
    private World world;
    private Town town;
    private PlotType type;
    private ChunkPermission permission;
    private int price;
    private UUID owner;

    public ChunkCoord(ClaimedChunk chunk) {
        this.x = chunk.getX();
        this.z = chunk.getZ();
        this.world = chunk.getBukkitChunk().getWorld();
        this.town = chunk.getTown();
        this.type = chunk.getType();
        this.permission = chunk.getPermission();
        this.price = 0;
        this.owner = null;
    }

    public ChunkCoord(int x, int z, World world, Town town, PlotType type, ChunkPermission permission, int price, UUID owner) {
        this.x = x;
        this.z = z;
        this.world = world;
        this.town = town;
        this.type = type;
        this.permission = permission;
        this.price = price;
        this.owner = owner;
    }

    public String getChunkString() {
        if (owner == null) {
            return x + "," + z + "," + world.getName() + "," + town.getName() + "," + type.name() + "," + permission.toString() + "," + price + "," + null;
        }
        return x + "," + z + "," + world.getName() + "," + town.getName() + "," + type.name() + "," + permission.toString() + "," + price + "," + owner.toString();
    }

    public ClaimedChunk createClaimedChunk() {
        return new ClaimedChunk(world.getChunkAt(x, z), town, permission, type);
    }
}
