package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.towny.TownyPlayer;

public class SethomeCommand implements ITownyCommand{

    @Override
    public String getCommand() {
        return "sethome";
    }

    @Override
    public String getUsage() {
        return "/towny sethome";
    }

    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        if (!player.hasTown()){
            player.sendMessage(Messages.NO_TOWN);
            return true;
        }
        if (!player.isTownLeader()){
            player.sendMessage(Messages.NO_PERMS);
            return true;
        }
        player.getTown().setHome(player.getBukkitPlayer().getLocation());
        player.sendMessage(Messages.HOME_SET);
        for (TownyPlayer townyPlayer : player.getTown().getPlayers().keySet()){
            townyPlayer.sendMessage(Messages.HOME_IS_SET);
        }
        return true;
    }
}