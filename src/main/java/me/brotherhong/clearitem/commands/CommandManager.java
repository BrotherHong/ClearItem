package me.brotherhong.clearitem.commands;

import me.brotherhong.clearitem.ClearItem;
import me.brotherhong.clearitem.Messages;
import me.brotherhong.clearitem.commands.subcommands.ClearCommand;
import me.brotherhong.clearitem.commands.subcommands.ReloadCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {

    private final ClearItem plugin;
    private static final List<SubCommand> subCommands = new ArrayList<>();
    private final Messages messages;

    public CommandManager(ClearItem plugin) {
        this.plugin = plugin;
        this.messages = plugin.getMessages();

        subCommands.add(new ReloadCommand(plugin));
        subCommands.add(new ClearCommand(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;
        Player player = (Player) sender;
        if (args.length == 0) {
            return true;
        }
        for (SubCommand subCommand : subCommands) {
            if (subCommand.getName().equalsIgnoreCase(args[0])) {
                if (!subCommand.hasPermission(player)) {
                    messages.sendNoPermission(player);
                    return true;
                }
                subCommand.execute(player, args);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (SubCommand subCommand : subCommands) {
                String commandName = subCommand.getName();
                if (commandName.startsWith(args[0])) {
                    result.add(commandName);
                }
            }
            return result;
        }
        return null;
    }

    public static List<SubCommand> getSubCommands() {
        return subCommands;
    }
}
