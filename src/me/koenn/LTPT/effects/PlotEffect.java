package me.koenn.LTPT.effects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.LineEffect;
import de.slikey.effectlib.util.DynamicLocation;
import de.slikey.effectlib.util.ParticleEffect;
import me.koenn.LTPT.LTPTowny;
import me.koenn.LTPT.util.ChunkUtil;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public class PlotEffect {

    public void play(Location location) {
        World world = location.getWorld();
        int y = (int) Math.round(location.getY());
        List<Chunk> chunks = new ArrayList<>();
        Chunk chunk1 = world.getChunkAt(location);
        chunks.add(chunk1);
        chunks.add(world.getChunkAt(Math.round(chunk1.getX() - 1), Math.round(chunk1.getZ())));
        chunks.add(world.getChunkAt(Math.round(chunk1.getX()), Math.round(chunk1.getZ() - 1)));
        chunks.add(world.getChunkAt(Math.round(chunk1.getX() - 1), Math.round(chunk1.getZ() - 1)));
        chunks.add(world.getChunkAt(Math.round(chunk1.getX() + 1), Math.round(chunk1.getZ() + 1)));
        chunks.add(world.getChunkAt(Math.round(chunk1.getX() + 1), Math.round(chunk1.getZ())));
        chunks.add(world.getChunkAt(Math.round(chunk1.getX()), Math.round(chunk1.getZ() + 1)));
        chunks.add(world.getChunkAt(Math.round(chunk1.getX() + 1), Math.round(chunk1.getZ() - 1)));
        chunks.add(world.getChunkAt(Math.round(chunk1.getX() - 1), Math.round(chunk1.getZ() + 1)));
        chunks.stream().filter(ChunkUtil::isClaimed).forEach(chunk -> drawGreen(chunk.getBlock(7, y, 7).getLocation().add(0.5, 0, 0.5)));
        chunks.stream().filter(chunk -> !ChunkUtil.isClaimed(chunk)).forEach(chunk -> drawRed(chunk.getBlock(7, y, 7).getLocation().add(0.5, 0, 0.5)));
    }

    private void drawGreen(Location location) {
        EffectManager effectManager = new EffectManager(LTPTowny.getPlugin());
        LineEffect effect = new LineEffect(effectManager);
        effect.particle = ParticleEffect.VILLAGER_HAPPY;
        effect.particles = 10;
        effect.iterations = 200;
        effect.setDynamicOrigin(new DynamicLocation(location));
        effect.setDynamicTarget(new DynamicLocation(location.add(0, 15, 0)));
        effect.start();
    }

    private void drawRed(Location location) {
        EffectManager effectManager = new EffectManager(LTPTowny.getPlugin());
        LineEffect effect = new LineEffect(effectManager);
        effect.particle = ParticleEffect.REDSTONE;
        effect.particles = 10;
        effect.iterations = 220;
        effect.setDynamicOrigin(new DynamicLocation(location));
        effect.setDynamicTarget(new DynamicLocation(location.add(0, 15, 0)));
        effect.start();
    }
}
