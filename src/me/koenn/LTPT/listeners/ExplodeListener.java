package me.koenn.LTPT.listeners;

import me.koenn.LTPT.util.ChunkUtil;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplodeListener implements Listener {

    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent e) {
        Location location = e.getLocation();
        if (ChunkUtil.isClaimed(location)) {
            e.setCancelled(true);
        }
    }
}
