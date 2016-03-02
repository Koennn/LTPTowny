package me.koenn.LTPT.commands.commands;

import me.koenn.LTPT.commands.ITownyCommand;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.towny.TownInvite;

public class AcceptCommand implements ITownyCommand {

    @Override
    public String getCommand() {
        return "accept";
    }

    @Override
    public String getUsage() {
        return "/town accept";
    }

    @Override
    public boolean execute(TownyPlayer player, String[] args) {
        if (player.hasTown()) {
            player.sendMessage(Messages.HAS_TOWN);
            return true;
        }
        TownInvite invite;
        try {
            invite = TownInvite.getInvite(player);
        } catch (NullPointerException ex) {
            player.sendMessage(Messages.NO_INVITE);
            return true;
        }
        invite.accept();
        return true;
    }
}
