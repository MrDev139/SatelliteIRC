package me.mrdev;

import me.mrdev.DiscordSettingsFile.DiscordSettingsFile;
import me.mrdev.listeners.ChatListener;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {

    @Override
    public void onEnable() {
        new DiscordSettingsFile(this);
        new ChatListener(this);
    }

    @Override
    public void onDisable() {
    }
}
