package me.brotherhong.clearitem.commands.subcommands;

import me.brotherhong.clearitem.ClearItem;
import me.brotherhong.clearitem.Permissions;
import me.brotherhong.clearitem.commands.SubCommand;
import org.bukkit.entity.Player;

public class ReloadCommand extends SubCommand {

    public ReloadCommand(ClearItem plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reload configuration file.";
    }

    @Override
    public String getUsage() {
        return "/ci reload";
    }

    @Override
    public boolean hasPermission(Player player) {
        return player.hasPermission(Permissions.OP);
    }

    @Override
    public void execute(Player player, String[] args) {
        config.reload();
        plugin.getModuleManager().unloadModules();
        plugin.getModuleManager().loadModules();
        messages.send(player, "&aReload complete.");
    }
}
