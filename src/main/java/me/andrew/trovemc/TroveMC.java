package me.andrew.trovemc;

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

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new ChunkEvents(this), this);
    }
}
