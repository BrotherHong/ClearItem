package me.brotherhong.clearitem.commands;

import me.brotherhong.clearitem.ClearItem;
import me.brotherhong.clearitem.Messages;
import me.brotherhong.clearitem.configs.Config;
import org.bukkit.entity.Player;

public abstract class SubCommand {

    protected final ClearItem plugin;
    protected final Config config;
    protected final Messages messages;

    public SubCommand(ClearItem plugin) {
        this.plugin = plugin;
        this.config = plugin.getMyConfig();
        this.messages = plugin.getMessages();
    }

    public abstract String getName();
    public abstract String getDescription();
    public abstract String getUsage();

    public abstract boolean hasPermission(Player player);

    public abstract void execute(Player player, String[] args);
}
