package me.andrew.trovemc;

import lombok.Getter;
import me.andrew.trovemc.events.ChunkEvents;
import me.andrew.trovemc.events.InteractionEvents;
import me.andrew.trovemc.events.PlotBuildingEvents;
import me.andrew.trovemc.managers.PlotManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
        getServer().getPluginManager().registerEvents(new ChunkEvents(this), this);
        getServer().getPluginManager().registerEvents(new InteractionEvents(), this);
        getServer().getPluginManager().registerEvents(new PlotBuildingEvents(), this);
        saveDefaultConfig();
        instance = this;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("testplotsave")) {
            Player p = (Player) sender;
            PlotManager.getInstance().savePlot(p, p.getLocation().getChunk());
        }
        return false;
    }
}
