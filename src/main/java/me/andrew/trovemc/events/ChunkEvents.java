package me.andrew.trovemc.events;

import me.andrew.trovemc.TroveMC;
import me.andrew.trovemc.populators.CornerStonePopulator;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.material.Directional;

import java.util.Random;

/**
 * ------------------------------
 * Copyright (c) AndrewAubury 2018
 * https://www.andrewa.pw
 * Project: TroveMC
 * ------------------------------
 */
public class ChunkEvents implements Listener {

    private TroveMC troveMC;

public ChunkEvents(TroveMC troveMC){
    this.troveMC = troveMC;
}

    @EventHandler
    public void onChunkLoad(WorldInitEvent e) {
        e.getWorld().getPopulators().add(new CornerStonePopulator());
    }

}
