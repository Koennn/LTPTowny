package me.koenn.LTPT.commands;

import me.koenn.LTPT.commands.commands.CreateCommand;
import me.koenn.LTPT.commands.commands.HomeCommand;
import me.koenn.LTPT.commands.commands.SethomeCommand;
import me.koenn.LTPT.gui.TownGui;
import me.koenn.LTPT.references.Messages;
import me.koenn.LTPT.towny.TownyPlayer;
import me.koenn.LTPT.util.Logger;
import net.md_5.bungee.api.ChatColor;
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
        TownyPlayer player = TownyPlayer.getPlayer((Player) sender);
        if (args.length == 0) {
            TownGui gui = new TownGui(player);
            gui.open();
        }
        for (ITownyCommand townyCommand : commands) {
            if (townyCommand.getCommand().equalsIgnoreCase(args[0])) {
                if (!townyCommand.execute(player, args)) {
                    player.sendMessage(Messages.PREFIX_CHAT + ChatColor.GRAY + townyCommand.getUsage());
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
        Logger.info("Loaded " + commandsAmount + " commands");
    }

    private void register(ITownyCommand command) {
        this.commands.add(command);
        this.commandsAmount++;
    }
}