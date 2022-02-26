package me.brotherhong.clearitem.commands.subcommands;

import me.brotherhong.clearitem.ClearItem;
import me.brotherhong.clearitem.Permissions;
import me.brotherhong.clearitem.commands.SubCommand;
import org.bukkit.entity.Player;

public class ClearCommand extends SubCommand {

    public ClearCommand(ClearItem plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "Clear the item from the ground instantly.";
    }

    @Override
    public String getUsage() {
        return "/ci clear";
    }

    @Override
    public boolean hasPermission(Player player) {
        return player.hasPermission(Permissions.OP);
    }

    @Override
    public void execute(Player player, String[] args) {
        plugin.clearDroppedItem();
        ClearItem.reset = true;
    }
}
