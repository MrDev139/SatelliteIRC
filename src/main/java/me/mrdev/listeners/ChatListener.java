package me.mrdev.listeners;

import me.mrdev.DiscordSettingsFile.DiscordSettingsFile;
import me.mrdev.MainClass;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import javax.security.auth.login.LoginException;

public class ChatListener extends ListenerAdapter implements Listener {

    private MainClass plugin;
    private JDA jda;

    public ChatListener(MainClass plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        setupBot();
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onAsyncChat(AsyncPlayerChatEvent event){
        Player p = event.getPlayer();
        String msg = event.getMessage();
        if(!Utils.isChannelSetup()){
            plugin.getLogger().warning("Text-Channel is not setup , please go to DiscordSettingsFile and add your channel's id");
        }else {
            TextChannel channel = jda.getTextChannelById(DiscordSettingsFile.getDiscordconfig().getString("Text-Channel-ID"));
            channel.sendMessage("[Minecraft] " + p.getName() + " >>> " + msg);
            channel.sendMessage("this worked !!");
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if (event.isFromType(ChannelType.PRIVATE) || event.isFromType(ChannelType.GROUP)) {
           return;
        }
        User user = event.getAuthor();
        if (user.isBot()) {
            return;
        }
        TextChannel channel = jda.getTextChannelById(DiscordSettingsFile.getDiscordconfig().getString("Text-Channel-ID"));
        if(!channel.getId().equals(DiscordSettingsFile.getDiscordconfig().getString("Text-Channel-ID"))){
            return;
        }
        String msg = event.getMessage().getContentRaw();
        Bukkit.broadcastMessage("[Satellite] [Discord] " + user.getName() + ">>> " + msg);
    }

    public void setupBot(){
        if(!Utils.isTokenSetup()){
            plugin.getLogger().warning("Token is not setup , bot will not work until token is setup and next server launch");
        }
        try {
                 jda = new JDABuilder().setToken(DiscordSettingsFile.getDiscordconfig().getString("Token"))
                         .setActivity(Activity.of(Activity.ActivityType.STREAMING , "Mrdev developing me"))
                        .addEventListeners(this)
                        .build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }

}
