package keri.projectx.common.init;

import keri.projectx.common.block.*;
import keri.projectx.common.item.*;
import keri.projectx.common.tile.TileXynergyNode;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PXContent {

    public static Block xycroniumOre;
    public static Block xycroniumBlock;
    public static Block xycroniumBricks;
    public static Block xycroniumPlate;
    public static Block xycroniumPlatform;
    public static Block xycroniumStructure;
    public static Block xycroniumShield;
    public static Block sulfurTorch;
    public static Block aluminumTorch;
    public static Block cornCrop;
    public static Block xynergyNode;

    public static Item xycroniumCrystal;
    public static Item xycroniumIngot;
    public static Item xycroniumNugget;
    public static Item xycroniumDust;
    public static Item quartzCrystal;
    public static Item cornKernel;
    public static Item corn;
    public static Item cobOCorn;
    public static Item popcorn;
    public static Item sulfurGoo;
    public static Item xynergyTool;
    public static Item powerCore;
    public static Item debugger;

    public static void preInit(){
        xycroniumOre = new BlockXycroniumOre();
        xycroniumBlock = new BlockXycroniumBlock();
        xycroniumBricks = new BlockXycroniumBricks();
        xycroniumPlate = new BlockXycroniumPlate();
        xycroniumPlatform = new BlockXycroniumPlatform();
        xycroniumStructure = new BlockXycroniumStructure();
        xycroniumShield = new BlockXycroniumShield();
        sulfurTorch = new BlockSulfurTorch();
        aluminumTorch = new BlockAluminumTorch();
        cornCrop = new BlockCornCrop();
        xynergyNode = new BlockXynergyNode();

        xycroniumCrystal = new ItemXycroniumCrystal();
        xycroniumIngot = new ItemXycroniumIngot();
        xycroniumNugget = new ItemXycroniumNugget();
        xycroniumDust = new ItemXycroniumDust();
        quartzCrystal = new ItemQuartzCrystal();
        cornKernel = new ItemCornKernel();
        corn = new ItemCorn();
        cobOCorn = new ItemCobOCorn();
        popcorn = new ItemPopcorn();
        sulfurGoo = new ItemSulfurGoo();
        xynergyTool = new ItemXynergyTool();
        powerCore = new ItemPowerCore();
        debugger = new ItemDebugger();
    }

    public static void init(){
        MinecraftForge.addGrassSeed(new ItemStack(cornKernel, 1, 0), PXConfig.cornSeedChance);
        GameRegistry.registerTileEntity(TileXynergyNode.class, "projectx.tile_xynergy_node");
    }

}
