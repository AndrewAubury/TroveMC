package me.andrew.trovemc.events;

import me.andrew.trovemc.managers.PlotManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * ------------------------------
 * Copyright (c) AndrewAubury 2018
 * https://www.andrewa.pw
 * Project: TroveMC
 * ------------------------------
 */
public class InteractionEvents implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block b = e.getClickedBlock();
            if (b.getType().equals(Material.SIGN)) {
                Sign s = (Sign) b.getState();
                if (s.getLines() == PlotManager.getInstance().getSignText()) {
                    PlotManager.getInstance().activateSignClicked(e);
                }
            }
        } else {
            return;
        }
    }

}
