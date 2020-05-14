package me.mrdev.listeners;

import me.mrdev.DiscordSettingsFile.DiscordSettingsFile;

public class Utils{


    public static boolean isTokenSetup() {
        return DiscordSettingsFile.getDiscordconfig().getString("Token") != null;
    }

    public static boolean isChannelSetup() {
        return DiscordSettingsFile.getDiscordconfig().getString("Text-ChannelID") != null;
    }
}