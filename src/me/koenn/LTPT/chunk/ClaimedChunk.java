package me.koenn.LTPT.chunk;

import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.towny.Town;
import org.bukkit.Chunk;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.UUID;

public class ClaimedChunk {

    public static ArrayList<ClaimedChunk> claimedChunks = new ArrayList<>();

    private Chunk chunk;
    private int x;
    private int z;
    private Town town;
    private ChunkPermission permission;
    private PlotType type;

    private int price;
    private TownyPlayer owner;

    public ClaimedChunk(Chunk chunk, Town town) throws IllegalArgumentException {
        if (chunk.getWorld().getEnvironment() != World.Environment.NORMAL) {
            throw new IllegalArgumentException("You can only claim in the overworld");
        }
        this.chunk = chunk;
        this.town = town;
        this.x = chunk.getX();
        this.z = chunk.getZ();
        this.permission = new ChunkPermission();
        this.type = PlotType.NORMAL;
        claimedChunks.add(this);
    }

    public ClaimedChunk(Chunk chunk, Town town, ChunkPermission permission, PlotType type, int price, UUID owner) throws IllegalArgumentException {
        if (chunk.getWorld().getEnvironment() != World.Environment.NORMAL) {
            throw new IllegalArgumentException("You can only claim in the overworld");
        }
        this.chunk = chunk;
        this.town = town;
        this.x = chunk.getX();
        this.z = chunk.getZ();
        this.permission = permission;
        this.type = type;
        this.price = price;
        this.owner = TownyPlayer.getPlayer(owner);
        claimedChunks.add(this);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public TownyPlayer getOwner() {
        return owner;
    }

    public void setOwner(TownyPlayer owner) {
        this.owner = owner;
        if (this.owner != null) {
            this.permission.setDestroy(false);
            this.permission.setAccess(false);
            this.permission.setBuild(false);
        }
    }

    public PlotType getType() {
        return type;
    }

    public void setType(PlotType type) {
        this.type = type;
    }

    public ChunkPermission getPermission() {
        return permission;
    }

    public Chunk getBukkitChunk() {
        return this.chunk;
    }

    public void remove() {
        claimedChunks.remove(this);
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
