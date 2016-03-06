package me.koenn.LTPT.effects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.LineEffect;
import de.slikey.effectlib.util.DynamicLocation;
import de.slikey.effectlib.util.ParticleEffect;
import me.koenn.LTPT.LTPTowny;
import org.bukkit.Chunk;
import org.bukkit.Location;

public class ChunkEffect {

    public void play(Chunk chunk, int y) {
        Location location1 = chunk.getBlock(0, y, 0).getLocation();
        location1.add(0, 0.5, 0);
        Location location2 = chunk.getBlock(15, y, 0).getLocation();
        location2.add(1, 0.5, 0);
        Location location3 = chunk.getBlock(0, y, 15).getLocation();
        location3.add(0, 0.5, 1);
        Location location4 = chunk.getBlock(15, y, 15).getLocation();
        location4.add(1, 0.5, 1);
        drawLine(location1, location2);
        drawLine(location2, location4);
        drawLine(location3, location4);
        drawLine(location1, location3);
    }

    private void drawLine(Location location1, Location location2) {
        EffectManager effectManager = new EffectManager(LTPTowny.getPlugin());
        LineEffect effect = new LineEffect(effectManager);
        effect.particle = ParticleEffect.FLAME;
        effect.iterations = 150;
        effect.particles = 20;
        effect.setDynamicOrigin(new DynamicLocation(location1));
        effect.setDynamicTarget(new DynamicLocation(location2));
        effect.start();
    }
}
