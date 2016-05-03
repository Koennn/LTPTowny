package me.koenn.LTPT.fly;

import me.koenn.LTPT.LTPTowny;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.util.ExpUtil;
import org.bukkit.Bukkit;

public class FlyTimer {

    private TownyPlayer player;
    private int time;
    private int task;

    public FlyTimer(TownyPlayer player, int time) {
        this.player = player;
        this.time = time;
    }

    public void start() {
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(LTPTowny.plugin, () -> {
            if (player.isOnline()) {
                if (player.getBukkitPlayer().isFlying()) {
                    if (time > 0) {
                        time--;
                        ExpUtil.setTotalExperience(player.getBukkitPlayer(), ExpUtil.getTotalExperience(player.getBukkitPlayer()) - 1);
                    } else {
                        player.getBukkitPlayer().setAllowFlight(false);
                        this.stop();
                    }
                }
            }
        }, 0, 5);
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(task);
    }
}
