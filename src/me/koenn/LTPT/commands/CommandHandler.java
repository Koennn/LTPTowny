package me.koenn.LTPT.commands;

import me.koenn.LTPT.commands.commands.*;
import me.koenn.LTPT.commands.commands.plotcommands.PlotCommand;
import me.koenn.LTPT.gui.Gui;
import me.koenn.LTPT.gui.guis.InfoGui;
import me.koenn.LTPT.player.TownyPlayer;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.util.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandHandler implements CommandExecutor {

    private ArrayList<ITownyCommand> commands = new ArrayList<>();

    private int commandsAmount = 0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        TownyPlayer player = TownyPlayer.getPlayer(((Player) sender).getUniqueId());
        if (player == null) {
            return false;
        }
        if (args.length == 0) {
            if (!player.hasTown()) {
                player.sendMessage(Messages.NO_TOWN);
                return true;
            }
            InfoGui gui = new InfoGui(player, player.getTown());
            Gui.registerGui(gui);
            gui.open();
            return true;
        }
        for (ITownyCommand townyCommand : commands) {
            if (townyCommand.getCommand().equalsIgnoreCase(args[0])) {
                if (!townyCommand.execute(player, args)) {
                    player.sendMessage(ChatColor.GRAY + townyCommand.getUsage());
                }
                return true;
            }
        }
        player.sendMessage(Messages.UNKNOWN_COMMAND);
        return true;
    }

    public void setupCommands() {
        this.register(new CreateCommand());
        this.register(new SethomeCommand());
        this.register(new HomeCommand());
        this.register(new ClaimCommand());
        this.register(new LeaveCommand());
        this.register(new AcceptCommand());
        this.register(new InviteCommand());
        this.register(new RemoveCommand());
        this.register(new UnclaimCommand());
        this.register(new PlotCommand());
        this.register(new HelpCommand());
        this.register(new OverlayCommand());
        this.register(new ChatCommand());
        this.register(new ShowPlotsCommand());
        this.register(new SaveCommand());
        this.register(new SetRankCommand());
        this.register(new KickCommand());
        this.register(new OutpostCommand());
        this.register(new MapCommand());
        Logger.info("Loaded " + commandsAmount + " commands.");
    }

    private void register(ITownyCommand command) {
        this.commands.add(command);
        this.commandsAmount++;
    }
}