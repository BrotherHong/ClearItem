package me.brotherhong.clearitem.configs;

import me.brotherhong.clearitem.ClearItem;

import java.util.ArrayList;
import java.util.List;

public class Config extends ConfigManager {

    private int interval;
    private List<Integer> broadcastTime;

    public Config(ClearItem plugin) {
        super(plugin, "config.yml");
        load();
    }

    @Override
    protected void load() {
        String path = "general.";
        interval = getConfig().getInt(path + "clear-interval");
        broadcastTime = getConfig().getIntegerList(path + "broadcast");
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
}
