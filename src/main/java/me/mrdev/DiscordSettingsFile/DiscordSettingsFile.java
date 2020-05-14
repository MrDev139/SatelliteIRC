package me.mrdev.DiscordSettingsFile;

import me.mrdev.MainClass;
import org.bukkit.Warning;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class DiscordSettingsFile {

    private static MainClass plugin;

    private static File discordFile;
    private static FileConfiguration discordconfig;

    public DiscordSettingsFile(MainClass Plugin){
        plugin = Plugin;
        setup();
        getDiscordconfig().options().copyDefaults(true);
        saveDiscordFile();
    }

    public static void setup(){
        discordFile = new File(plugin.getDataFolder(), "DiscordSettings.yml");
        if(!discordFile.exists()){
            plugin.getLogger().info("DiscordSettings.yml does not exist , creating new one for you !!!");
            try{
                discordFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            plugin.getLogger().info("DiscordSettings.yml exists and ready !!");
        }
        discordconfig = YamlConfiguration.loadConfiguration(discordFile);
    }

    public static FileConfiguration getDiscordconfig() {
        return discordconfig;
    }

    public static void saveDiscordFile(){
        try{
            discordconfig.save(discordFile);
        }catch (IOException e){
            plugin.getLogger().warning("Can not save DiscordSettings Yaml file !!");
        }
    }


}
