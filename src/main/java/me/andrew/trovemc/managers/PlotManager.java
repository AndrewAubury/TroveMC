package me.andrew.trovemc.managers;


import me.andrew.trovemc.TroveMC;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * ------------------------------
 * Copyright (c) AndrewAubury 2018
 * https://www.andrewa.pw
 * Project: TroveMC
 * ------------------------------
 */
public class PlotManager {
    private static PlotManager ourInstance = new PlotManager();
    private HashMap<UUID, Chunk> activePlots = new HashMap<UUID, Chunk>();
    private HashMap<Chunk, Integer> plotHeights = new HashMap<Chunk, Integer>();

    private PlotManager() {

    }

    public static PlotManager getInstance() {
        return ourInstance;
    }

    public boolean isActive(Chunk c) {
        return activePlots.containsValue(c);
    }

    public Player whoActivated(Chunk c) {
        Player p = null;
        for (Map.Entry<UUID, Chunk> entry : activePlots.entrySet()) {
            if (entry.getValue() == c) {
                p = JavaPlugin.getProvidingPlugin(TroveMC.class).getServer().getPlayer(entry.getKey());
            }
        }
        return p;
    }

    //TODO (ok this isnt a todo but i wanted you to see it) this function here will be called from a EventHandler ONLY if its a
    //     Interaction with a activation sign.

    public void activateSignClicked(PlayerInteractEvent e) {
        Chunk c = e.getClickedBlock().getLocation().getChunk();
        plotHeights.remove(c);
        plotHeights.put(c, e.getClickedBlock().getLocation().clone().subtract(0, 1, 0).getBlockY());
        activatePlot(e.getPlayer(), c);
        e.getClickedBlock().setType(Material.AIR);
    }

    public void activatePlot(Player p, Chunk c) {
        deactivatePlot(p); //Make sure they dont have one pre-existed


        Location loc = c.getBlock(0, 0, 0).getLocation().clone();
        loc.setY(plotHeights.get(c));
        activePlots.put(p.getUniqueId(), c);

        //TODO The code so that plots can be made (copied from schem if they have one)
        //At this point loc is set to the 1st X, Z of the chunk with Y at the plot level;

        ChatManager.getInstance().sendMessageFromConfig(p, "activated", true);

    }

    public void deactivatePlot(Player p) {
        //TODO the code to disable the plot
        if (!activePlots.containsKey(p.getUniqueId())) {
            return; //player doesnt have a plot active
        }
        Chunk c = activePlots.get(p.getUniqueId());

        //TODO Do deactivation here

        plotHeights.remove(c);
        activePlots.remove(p.getUniqueId());
    }

    public boolean isPlot(Chunk c) {
        return c.getBlock(10, 10, 10).getType() == Material.BEDROCK || activePlots.containsValue(c);
    }

    public void savePlot(Player p, Chunk c) {
        String path = JavaPlugin.getProvidingPlugin(TroveMC.class).getDataFolder().getPath() + "/plots/";
        File file = new File(path + "plot-" + p.getUniqueId() + ".schematic");
        Location pos1 = c.getBlock(0, 0, 0).getLocation();
        Location pos2 = c.getBlock(15, 15, 15).getLocation();

        pos1.setY(plotHeights.get(c) - 50);
        pos2.setY(plotHeights.get(c) - 150);

//        Vector bot = new Vector(pos1.getBlockX(), pos1.getBlockY(), pos1.getBlockZ());
//        Vector top = new Vector(pos2.getBlockX(), pos2.getBlockY(), pos2.getBlockZ());
//        CuboidRegion  region = new CuboidRegion(BukkitUtil.getLocalWorld(c.getWorld()), BukkitUtil.toVector(pos1), BukkitUtil.toVector(pos2));
//
//        Schematic schem = new Schematic(region);
//
//        try {
//            schem.save(file, ClipboardFormat.SCHEMATIC);
//            ChatManager.getInstance().sendMessage(p, "&aSaved!",true);
//        } catch (IOException e) {
//            ChatManager.getInstance().sendMessage(p, "&Error! Check Console.",true);
//
//            e.printStackTrace();
//        }
    }

    public int getPlotHeight(Chunk c) {
        return plotHeights.get(c);
    }

    public String[] getSignText() {
        return new String[]{
                cc("&7[&8Cornerstone&7]"),
                cc("&8Right click"),
                cc("&8this sign to"),
                cc("&7Activate"),
        };
    }

    private String cc(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
