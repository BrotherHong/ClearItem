package me.brotherhong.clearitem.module.modules;

import me.brotherhong.clearitem.ClearItem;
import me.brotherhong.clearitem.module.Module;

import java.util.List;

public class AutoClear extends Module implements Runnable {

    private int clearTask = 0;
    private int remaining = 0;
    private List<Integer> broadcastTime;

    public AutoClear(ClearItem plugin) {
        super(plugin);
    }

    @Override
    public void onEnable() {
        remaining = config.getInterval();
        broadcastTime = config.getBroadcastTime();

        clearTask = plugin.getServer().getScheduler()
                .scheduleSyncRepeatingTask(plugin, this, 0, 20);
    }

    @Override
    public void onDisable() {
        plugin.getServer().getScheduler().cancelTask(clearTask);
    }

    @Override
    public void run() {
        if (remaining < 0 || ClearItem.reset) {
            remaining = config.getInterval();
            ClearItem.reset = false;
        }
        if (broadcastTime.contains(remaining)) {
            messages.broadcastRemaining(remaining);
        }
        if (remaining == 0) {
            plugin.clearDroppedItem();
        }
        remaining--;
    }


    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
