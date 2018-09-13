package me.andrew.trovemc.objects;

import me.andrew.trovemc.TroveMC;
import me.andrew.trovemc.managers.ConfigManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * ------------------------------
 * Copyright (c) AndrewAubury 2018
 * https://www.andrewa.pw
 * Project: TroveMC
 * ------------------------------
 */
public class Config {
    public boolean dontReload;
    String path;
    String fileName;
    private File file;
    private FileConfiguration fileConfig;

    public Config(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
        JavaPlugin main = TroveMC.getProvidingPlugin(TroveMC.class);

        dontReload = true;
        file = new File(path, fileName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            if (main.getResource(fileName) != null) {
                main.saveResource(fileName, false);
            } else {
                try {
                    file.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        FileConfiguration fileConfig = new YamlConfiguration();
        try {
            fileConfig.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.fileConfig = fileConfig;
        ConfigManager.getInstance().addConfig(this);
    }

    public FileConfiguration getConfig() {
        return fileConfig;
    }

    public void reload() {
        //System.out.println("I reloaded!!!");
        FileConfiguration fileConfig = new YamlConfiguration();
        try {
            fileConfig.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.fileConfig = fileConfig;
    }

    public String getName() {
        return fileName;
    }

    public void setReloader(boolean shouldreload) {
        dontReload = shouldreload;
    }

    public void save() {
        file = new File(path, fileName);

        try {
            fileConfig.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}