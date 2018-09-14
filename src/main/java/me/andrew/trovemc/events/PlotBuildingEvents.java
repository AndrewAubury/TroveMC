package me.andrew.trovemc.events;

import me.andrew.trovemc.managers.ChatManager;
import me.andrew.trovemc.managers.PlotManager;
import me.andrew.trovemc.populators.CornerStonePopulator;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * ------------------------------
 * Copyright (c) AndrewAubury 2018
 * https://www.andrewa.pw
 * Project: TroveMC
 * ------------------------------
 */
public class PlotBuildingEvents implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (CornerStonePopulator.isPlot(e.getBlock().getChunk())) {
            if (PlotManager.getInstance().isActive(e.getBlock().getChunk())) {
                if (PlotManager.getInstance().whoActivated(e.getBlock().getChunk()) == e.getPlayer()) {
                    Block b = e.getBlock();

                    int startY = PlotManager.getInstance().getPlotHeight(b.getChunk());
                    int blockY = b.getLocation().getBlockY();
                    Location plotLocation = b.getLocation().clone();
                    plotLocation.setY(startY);
                    if (startY > blockY) {
                        if (plotLocation.distance(e.getBlock().getLocation()) > 50) {
                            e.setCancelled(true);
                            ChatManager.getInstance().sendMessageFromConfig(e.getPlayer(), "max_down", true);
                            return;
                        }
                        //Check to see if it has been built down more then 50
                    } else {
                        if (plotLocation.distance(e.getBlock().getLocation()) > 150) {
                            e.setCancelled(true);
                            ChatManager.getInstance().sendMessageFromConfig(e.getPlayer(), "max_up", true);
                            return;
                        }
                    }
                    return;
                }
            }
            e.setCancelled(true);
            ChatManager.getInstance().sendMessageFromConfig(e.getPlayer(), "cant_break", true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (CornerStonePopulator.isPlot(e.getBlock().getChunk())) {
            if (PlotManager.getInstance().isActive(e.getBlock().getChunk())) {
                if (PlotManager.getInstance().whoActivated(e.getBlock().getChunk()) == e.getPlayer()) {
                    Block b = e.getBlock();
                    int startY = PlotManager.getInstance().getPlotHeight(b.getChunk());
                    int blockY = b.getLocation().getBlockY();
                    Location plotLocation = b.getLocation().clone();
                    plotLocation.setY(startY);
                    if (startY > blockY) {
                        if (plotLocation.distance(e.getBlock().getLocation()) > 50) {
                            e.setCancelled(true);
                            ChatManager.getInstance().sendMessageFromConfig(e.getPlayer(), "max_down", true);
                            return;
                        }
                        //Check to see if it has been built down more then 50
                    } else {
                        if (plotLocation.distance(e.getBlock().getLocation()) > 150) {
                            e.setCancelled(true);
                            ChatManager.getInstance().sendMessageFromConfig(e.getPlayer(), "max_up", true);
                            return;
                        }
                    }
                    return;
                }
            }
            e.setCancelled(true);
            ChatManager.getInstance().sendMessageFromConfig(e.getPlayer(), "cant_place", true);
        }
    }


}
