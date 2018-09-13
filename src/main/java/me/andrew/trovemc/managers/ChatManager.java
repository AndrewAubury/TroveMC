package me.andrew.trovemc.managers;

import me.andrew.trovemc.TroveMC;
import me.andrew.trovemc.objects.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

/**
 * ------------------------------
 * Copyright (c) AndrewAubury 2018
 * https://www.andrewa.pw
 * Project: TroveMC
 * ------------------------------
 */
public class ChatManager {
    private static ChatManager ourInstance = new ChatManager();
    private JavaPlugin main;

    public ChatManager() {
        main = JavaPlugin.getProvidingPlugin(TroveMC.class);
    }

    public static ChatManager getInstance() {
        return ourInstance;
    }

    public void sendMessage(Player p, String msg_, boolean prefix) {
        final FileConfiguration config = new Config(main.getDataFolder().getPath(), "lang.yml").getConfig();
        String msg = prefix ? config.getString("prefix", "&7[&cBOINC&7]") + msg_ : msg_;
        //msg  = PlaceholderAPI.setPlaceholders(p, msg);
        p.sendMessage(cc(msg));
    }

    public void sendMessage(CommandSender sender, String msg_, boolean prefix) {
        final FileConfiguration config = new Config(main.getDataFolder().getPath(), "lang.yml").getConfig();
        String msg = prefix ? config.getString("prefix", "&7[&cBOINC&7]") + msg_ : msg_;
        sender.sendMessage(cc(msg));
    }

    public void sendMessageFromConfig(Player p, String key, boolean prefix) {
        final FileConfiguration config = new Config(main.getDataFolder().getPath(), "lang.yml").getConfig();
        String msg = prefix ? config.getString("prefix", "&7[&cBOINC&7]") + config.getString(key) : config.getString(key);
//        msg  = PlaceholderAPI.setPlaceholders(p, msg);
        p.sendMessage(cc(msg));
    }

    public void sendMessageFromConfig(Player p, String key, boolean prefix, HashMap<String, String> replacements) {
        final FileConfiguration config = new Config(main.getDataFolder().getPath(), "lang.yml").getConfig();
        String msg = prefix ? config.getString("prefix", "&7[&cBOINC&7]") + config.getString(key) : config.getString(key);
        for (String rkey : replacements.keySet()) {
            if (msg.contains("%" + rkey + "%")) {
                msg = msg.replaceAll("%" + rkey + "%", replacements.get(rkey));
            }
        }
//        msg  = PlaceholderAPI.setPlaceholders(p, msg);
        p.sendMessage(cc(msg));
    }

    private String cc(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
