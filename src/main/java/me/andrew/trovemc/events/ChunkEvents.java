package me.andrew.trovemc.events;

import me.andrew.trovemc.TroveMC;
import me.andrew.trovemc.objects.Config;
import me.andrew.trovemc.populators.CornerStonePopulator;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.world.WorldInitEvent;

/**
 * ------------------------------
 * Copyright (c) AndrewAubury 2018
 * https://www.andrewa.pw
 * Project: TroveMC
 * ------------------------------
 */
public class ChunkEvents implements Listener {

    private TroveMC troveMC;
    FileConfiguration chatConfig;


public ChunkEvents(TroveMC troveMC){
    this.troveMC = troveMC;
    chatConfig = new Config(troveMC.getDataFolder().getPath(), "lang.yml").getConfig();
}

    @EventHandler
    public void onWorldInit(WorldInitEvent e) {
        e.getWorld().getPopulators().add(new CornerStonePopulator(troveMC));
    }


    @EventHandler
    public void onBlockExplode(BlockExplodeEvent e) {
        if (e.blockList() == null) {
            return;
        }
        for (Block b : e.blockList()) {
            if (CornerStonePopulator.isPlot(b.getChunk())) {
                e.blockList().remove(b);
            }
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        if (e.blockList() == null) {
            return;
        }
        for (Block b : e.blockList()) {
            if (CornerStonePopulator.isPlot(b.getChunk())) {
                e.blockList().remove(b);
            }
        }
    }
}
