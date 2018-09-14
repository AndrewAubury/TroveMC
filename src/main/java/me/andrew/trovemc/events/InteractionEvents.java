package me.andrew.trovemc.events;

import me.andrew.trovemc.managers.PlotManager;
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
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block b = e.getClickedBlock();
//            ChatManager.getInstance().sendMessage(e.getPlayer(),"&aRight Clicked Block", true);
            if (b.getState() instanceof Sign) {
                Sign s = (Sign) b.getState();
//                ChatManager.getInstance().sendMessage(e.getPlayer(),"&aRight Clicked Sign", true);

                if (s.getLine(0).equals(PlotManager.getInstance().getSignText()[0])) {
//                    ChatManager.getInstance().sendMessage(e.getPlayer(),"&aRight Clicked Plot Sign", true);
                    PlotManager.getInstance().activateSignClicked(e);
                } else {
//                    ChatManager.getInstance().sendMessage(e.getPlayer(),"&cRight Clicked other sign", true);
//                    ChatManager.getInstance().sendMessage(e.getPlayer(),"&cExpected:&r "+PlotManager.getInstance().getSignText()[0], true);
//                    ChatManager.getInstance().sendMessage(e.getPlayer(),"&cFound:&r "+s.getLine(0), true);
                }
            }
        } else {
            return;
        }
    }

}
