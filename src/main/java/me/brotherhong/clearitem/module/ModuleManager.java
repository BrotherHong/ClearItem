package me.brotherhong.clearitem.module;

import me.brotherhong.clearitem.ClearItem;
import me.brotherhong.clearitem.module.modules.AutoClear;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private ClearItem plugin;
    private List<Module> modules = new ArrayList<>();

    public ModuleManager(ClearItem plugin) {
        this.plugin = plugin;

    }

    public void loadModules() {
        addModule(new AutoClear(plugin));

        for (Module module : modules) {
            module.onEnable();
        }

        plugin.getServer().getLogger().info("Loaded " + modules.size() + " plugin modules.");
    }

    public void unloadModules() {
        for (Module module : modules) {
            module.onDisable();
        }
    }

    private void addModule(Module module) {
        modules.add(module);
    }
}
