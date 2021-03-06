package me.andrew.trovemc.populators;

import me.andrew.trovemc.TroveMC;
import me.andrew.trovemc.managers.PlotManager;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
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

    TroveMC troveMC;

    public CornerStonePopulator(TroveMC troveMC) {
        this.troveMC = troveMC;
    }

    public static boolean isPlot(Chunk c) {
        return c.getBlock(10, 10, 10).getType() == Material.BEDROCK;
    }

    public void populate(World world, Random random, final Chunk c) {

        if (!canBeCornerstone(c,
                Material.WATER,
                Material.LAVA
        )) {
            return;
        }

        if (chance(0.50, random)) {

            int cX = c.getX() * 16;
            int cZ = c.getZ() * 16;

            int lowestY = c.getWorld().getMaxHeight();
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    Location loc = nextBlockUP(c, cX + x, cZ + z);
                    if (loc.getBlockY() < lowestY) {
                        lowestY = loc.getBlockY();
                    }
                }
            }

            for(int x = 0; x < 16; x++) {
                for(int z = 0; z <16; z++) {
                    final Location loc2 = nextBlockUP(c, cX + x, cZ + z);

                    final Location loc = locationAtY(loc2.clone(), lowestY);

                    final int finalZ = z;
                    final int finalX = x;
                    final int finalLowestY = lowestY;
                    troveMC.getServer().getScheduler().runTaskLater(troveMC, new Runnable() {
                        public void run() {

                            if (loc2.getBlockY() + 1 > finalLowestY) {
                                int start = finalLowestY;
                                int curY = loc2.getBlockY() + 1;
                                while (curY > finalLowestY) {
                                    Location tmpLoc = locationAtY(loc.clone(), curY);
                                    tmpLoc.getBlock().setType(Material.AIR);
                                    curY--;
                                }
                            }

                            troveMC.getServer().getScheduler().runTaskLater(troveMC, new Runnable() {
                                public void run() {
                                    Block b = loc.getBlock();
                                    b.setType(Material.GRASS_PATH);
                                    b.getRelative(0, -1, 0).setType(Material.SMOOTH_STONE);
                                    if (finalZ == 15 || finalZ == 0 || finalX == 15 || finalX == 0) {
                                        loc.clone().add(0, 1, 0).getBlock().setType(Material.STONE_SLAB);
                                        int curY = loc.clone().getBlockY();
                                        while (curY > 0) {
                                            locationAtY(loc.clone(), curY).getBlock().setType(Material.BEDROCK);
                                            curY--;
                                        }
                                    }

                                    if (finalX == 7 && finalZ == 1) {
                                        setCornerStoneSign(loc.clone().add(0, 1, 0).getBlock());
                                    }
                                }
                            }, 1);
                        }
                    }, 1);

                }
                troveMC.getServer().getScheduler().runTaskLater(troveMC, new Runnable() {
                    public void run() {
                        c.getBlock(10, 10, 10).setType(Material.BEDROCK);
                    }
                }, 1);
            }
        }
    }

    private Location nextBlockUP(Chunk c, int x, int z){
        Location loc = c.getWorld().getHighestBlockAt(x, z).getLocation().clone();
        return loc;//.add(0,1,0);
    }

    private Location locationAtY(Location loc, int y) {
        loc.setY(y);
        return loc;
    }

    public boolean chance(double c, Random r) {
        int res = r.nextInt(10000);
        double r2 = res / 100.00;
        return r2 < c;
    }

    public boolean canBeCornerstone(Chunk c, Material... mats) {
        boolean canBe = true;
        int Y_MIN = 60;
        int Y_MAX = 150;

        int cX = c.getX() * 16;
        int cZ = c.getZ() * 16;

        Location loc = nextBlockUP(c, cX, cZ);

        return canBe;
    }

    public void setCornerStoneSign(Block b){

        String[] text = PlotManager.getInstance().getSignText();
        b.setType(Material.SIGN);

        Sign s = (Sign) b.getState();
        s.setLine(0, text[0]);
        s.setLine(1, text[1]);
        s.setLine(2, text[2]);
        s.setLine(3, text[3]);

        s.update();
    }

}
