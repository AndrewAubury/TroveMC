package me.andrew.trovemc.managers;

import lombok.Getter;
import me.andrew.trovemc.TroveMC;
import me.andrew.trovemc.objects.Config;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * ------------------------------
 * Copyright (c) AndrewAubury 2018
 * https://www.andrewa.pw
 * Project: TroveMC
 * ------------------------------
 */
public class ConfigManager {
    @Getter
    private static ConfigManager instance = new ConfigManager();

    JavaPlugin jp;
    ArrayList<Config> configs;

    private ConfigManager() {
        jp = TroveMC.getProvidingPlugin(TroveMC.class);
        configs = new ArrayList<Config>();
    }


    public void addConfig(Config conf) {
        configs.add(conf);
    }

    public void reloadAll() {
        for (Config cnf : configs) {
            if (!cnf.dontReload) {
                cnf.reload();
            }
        }
    }
}
