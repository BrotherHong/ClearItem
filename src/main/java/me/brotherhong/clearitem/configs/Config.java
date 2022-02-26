package me.brotherhong.clearitem.configs;

import me.brotherhong.clearitem.ClearItem;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Config extends ConfigManager {

    private int interval;
    private List<Integer> broadcastTime;
    private boolean clearNameTag;
    private List<String> whiteListedWorld;
    private List<EntityType> entitiesToClear;

    public Config(ClearItem plugin) {
        super(plugin, "config.yml");
        load();
    }

    @Override
    protected void load() {
        String general = "general.";
        interval = getConfig().getInt(general + "clear-interval");
        broadcastTime = getConfig().getIntegerList(general + "broadcast");
        clearNameTag = getConfig().getBoolean(general + "clear-nametag");
        whiteListedWorld = getConfig().getStringList("white-list");
        try {
            entitiesToClear = getConfig()
                    .getStringList("entities-to-clear")
                    .stream()
                    .map(EntityType::valueOf)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            plugin.getServer().getLogger().severe("[ClearItem] Wrong entity type input.");
            plugin.getServer().getLogger().severe("[ClearItem] Please check your config file.");
        }
    }

    public int getInterval() {
        if (interval <= 0) {
            interval = 100;
        }
        return interval;
    }

    public List<Integer> getBroadcastTime() {
        if (broadcastTime == null) {
            broadcastTime = new ArrayList<>();
        }
        return broadcastTime;
    }

    public boolean canClearNameTag() {
        return clearNameTag;
    }

    public List<String> getWhiteListedWorld() {
        if (whiteListedWorld == null) {
            whiteListedWorld = new ArrayList<>();
        }
        return whiteListedWorld;
    }

    public List<EntityType> getEntitiesToClear() {
        return entitiesToClear;
    }
}
