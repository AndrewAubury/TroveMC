package me.andrew.trovemc;

import lombok.Getter;
import me.andrew.trovemc.events.ChunkEvents;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * ------------------------------
 * Copyright (c) AndrewAubury 2018
 * https://www.andrewa.pw
 * Project: TroveMC
 * ------------------------------
 */
public class TroveMC extends JavaPlugin{

    @Getter
    public TroveMC instance;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new ChunkEvents(this), this);
        saveDefaultConfig();
        instance = this;
    }
}
