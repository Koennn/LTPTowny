package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.effects.ChunkEffect;
import me.koenn.LTPT.player.TownyPlayer;
import org.bukkit.Chunk;

public class OverlayCommand implements ITownyCommand {

    @Override
    public String getCommand() {
        return "overlay";
    }

    @Override
    public String getUsage() {
        return "/town overlay";
    }

    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        Chunk chunk = player.getBukkitPlayer().getWorld().getChunkAt(player.getLocation());
        new ChunkEffect().play(chunk, (int) Math.round(player.getLocation().getY()));
        return true;
    }
}
