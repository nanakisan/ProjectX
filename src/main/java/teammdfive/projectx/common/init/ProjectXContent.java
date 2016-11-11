package teammdfive.projectx.common.init;

import teammdfive.projectx.common.block.*;
import teammdfive.projectx.common.item.*;

public class ProjectXContent {

    public static BlockBase xycroniumOre;
    public static BlockBase xycroniumBlock;
    public static BlockBase xycroniumBricks;
    public static BlockBase xycroniumPlate;
    public static BlockBase xycroniumPlatform;
    public static BlockBase xycroniumStructure;
    public static BlockBase xycroniumShield;

    public static ItemBase xycroniumCrystal;
    public static ItemBase xycroniumIngot;
    public static ItemBase xycroniumNugget;
    public static ItemBase xycroniumDust;
    public static ItemBase powerCore;

    public static void preInit(){
        xycroniumOre = new BlockXycroniumOre();
        xycroniumBlock = new BlockXycroniumBlock();
        xycroniumBricks = new BlockXycroniumBricks();
        xycroniumPlate = new BlockXycroniumPlate();
        xycroniumPlatform = new BlockXycroniumPlatform();
        xycroniumStructure = new BlockXycroniumStructure();
        xycroniumShield = new BlockXycroniumShield();

        xycroniumCrystal = new ItemXycroniumCrystal();
        xycroniumIngot = new ItemXycroniumIngot();
        xycroniumNugget = new ItemXycroniumNugget();
        xycroniumDust = new ItemXycroniumDust();
        powerCore = new ItemPowerCore();
    }

    public static void init(){

    }

}
