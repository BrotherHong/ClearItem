package me.brotherhong.clearitem;

import me.brotherhong.clearitem.commands.CommandManager;
import me.brotherhong.clearitem.configs.Config;
import me.brotherhong.clearitem.module.ModuleManager;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class ClearItem extends JavaPlugin {

    private Config config;
    private Messages messages;
    private ModuleManager moduleManager;

    public static boolean reset = false;

    public ClearItem() {
        config = new Config(this);
        messages = new Messages(this);
        moduleManager = new ModuleManager(this);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("clearitem").setExecutor(new CommandManager(this));
        moduleManager.loadModules();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        moduleManager.unloadModules();
    }

    public void clearDroppedItem() {
        List<World> worlds = getServer().getWorlds();
        List<String> whiteListedWorld = config.getWhiteListedWorld();
        int count = 0;

        for (World world : worlds) {
            if (whiteListedWorld.contains(world.getName()))
                continue;
            List<Entity> entities = world.getEntities();
            for (Entity entity : entities) {
                if (!config.canClearNameTag() && entity.getCustomName() != null)
                    continue;
                if (canClear(entity.getType())) {
                    entity.remove();
                    count++;
                }
            }
        }
        messages.broadcastClear(count);
    }

    private boolean canClear(EntityType type) {
        return config.getEntitiesToClear().contains(type);
    }

    public Config getMyConfig() {
        return config;
    }

    public Messages getMessages() {
        return messages;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }
}
