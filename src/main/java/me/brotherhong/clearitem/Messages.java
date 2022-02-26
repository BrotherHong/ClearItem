package me.brotherhong.clearitem;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Messages {

    private ClearItem plugin;
    private FileConfiguration config;

    private String prefix;
    private final String path = "messages.";

    public Messages(ClearItem plugin) {
        this.plugin = plugin;
        this.config = plugin.getMyConfig().getConfig();
        this.prefix = trans(config.getString("prefix")) + " ";
    }

    public static String trans(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public void send(Player player, String msg) {
        player.sendMessage(prefix + trans(msg));
    }

    public void sendNoPermission(Player player) {
        player.sendMessage(prefix + trans(config.getString("")));
    }

    public void broadcastRemaining(int remaining) {
        plugin.getServer().broadcastMessage(prefix + trans(
                config.getString(path + "countdown")
                        .replaceAll("%remaining%", Integer.toString(remaining)))
        );
    }

    public void broadcastClear(int amount) {
        plugin.getServer().broadcastMessage(prefix + trans(
                config.getString(path + "clear")
                        .replaceAll("%amount%", Integer.toString(amount)))
        );
    }
}
