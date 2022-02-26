package me.brotherhong.clearitem;

import me.brotherhong.clearitem.configs.Config;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages {

    private ClearItem plugin;
    private Config config;

    private String prefix;
    private final String path = "messages.";

    public Messages(ClearItem plugin) {
        this.plugin = plugin;
        this.config = plugin.getMyConfig();
        this.prefix = trans(config.getConfig().getString("prefix")) + " ";
    }

    public static String trans(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public void send(Player player, String msg) {
        player.sendMessage(prefix + trans(msg));
    }

    public void sendNoPermission(Player player) {
        player.sendMessage(prefix + trans(config.getConfig().getString("")));
    }

    public void broadcastRemaining(int remaining) {
        plugin.getServer().broadcastMessage(prefix + trans(
                config.getConfig().getString(path + "countdown")
                        .replaceAll("%remaining%", Integer.toString(remaining)))
        );
    }

    public void broadcastClear(int amount) {
        plugin.getServer().broadcastMessage(prefix + trans(
                config.getConfig().getString(path + "clear")
                        .replaceAll("%amount%", Integer.toString(amount)))
        );
    }
}
