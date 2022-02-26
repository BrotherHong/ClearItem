package me.brotherhong.clearitem.module;

import me.brotherhong.clearitem.ClearItem;
import me.brotherhong.clearitem.Messages;
import me.brotherhong.clearitem.configs.Config;

public abstract class Module {

    protected ClearItem plugin;
    protected Config config;
    protected Messages messages;

    public Module(ClearItem plugin) {
        this.plugin = plugin;
        this.config = plugin.getMyConfig();
        this.messages = plugin.getMessages();
    }

    public abstract void onEnable();
    public abstract void onDisable();

}
