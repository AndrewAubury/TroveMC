package me.andrew.trovemc.populators;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

/**
 * ------------------------------
 * Copyright (c) AndrewAubury 2018
 * https://www.andrewa.pw
 * Project: TroveMC
 * ------------------------------
 */
public class CornerStonePopulator extends BlockPopulator {

    public void populate(World world, Random random, Chunk c) {
        
        if(chance(10,random)){
        for(int x = 0; x < 16; x++) {
            for(int z = 0; z <16; z++) {
                Location loc = nextBlockUP(c, x,z);
                System.out.println(loc);
                Block b = loc.getBlock();
                b.setType(Material.SMOOTH_STONE);
                if(z == 15|| z == 0 || x == 15 || x == 0){
                    loc.add(0,1,0).getBlock().setType(Material.STONE_SLAB);
                }
            }
        }
    }
    }


    private Location nextBlockUP(Chunk c, int x, int z){
        Location loc = c.getBlock(x,1,z).getLocation();
        return loc.add(0,1,0);
    }

    public void setCornerStoneSign(Block b){
        //A sign can hold 14 Chars
        String line1 = ChatColor.translateAlternateColorCodes('&',"&7[&8Cornerstone&7]");
        String line2 = ChatColor.translateAlternateColorCodes('&',"&8Right click");
        String line3 = ChatColor.translateAlternateColorCodes('&',"&8this sign to");
        String line4 = ChatColor.translateAlternateColorCodes('&',"&7Activate");
        b.setType(Material.SIGN);
        Sign s = (Sign) b.getState();
        s.setLine(0,line1);
        s.setLine(1,line2);
        s.setLine(2,line3);
        s.setLine(3,line4);
    }

    public boolean chance(int c, Random r){
        int res = r.nextInt(100-0) + 0;
        return c < res;
    }
}
