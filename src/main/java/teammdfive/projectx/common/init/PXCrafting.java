package teammdfive.projectx.common.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PXCrafting {

    public static void init(){
        for(int i = 0; i < 5; i++){
            GameRegistry.addSmelting(new ItemStack(PXContent.xycroniumCrystal, 1, i), new ItemStack(PXContent.xycroniumIngot, 1, i), 1F);
            GameRegistry.addRecipe(new ItemStack(PXContent.xycroniumBlock, 1, i), new Object[]{"XXX", "XXX", "XXX", 'X', new ItemStack(PXContent.xycroniumCrystal, 1, i)});
            GameRegistry.addShapelessRecipe(new ItemStack(PXContent.xycroniumCrystal, 9, i), new Object[]{new ItemStack(PXContent.xycroniumBlock, 1, i)});
            GameRegistry.addRecipe(new ItemStack(PXContent.xycroniumBricks, 5, i), new Object[]{"XCX", "CCC", "XCX", 'X', new ItemStack(PXContent.xycroniumIngot, 1, i), 'C', Blocks.STONEBRICK});
            GameRegistry.addShapelessRecipe(new ItemStack(PXContent.xycroniumNugget, 9, i), new Object[]{new ItemStack(PXContent.xycroniumIngot, 1, i)});
            GameRegistry.addRecipe(new ItemStack(PXContent.xycroniumIngot, 1, i), new Object[]{"XXX", "XXX", "XXX", 'X', new ItemStack(PXContent.xycroniumNugget, 1, i)});
            GameRegistry.addSmelting(new ItemStack(PXContent.xycroniumDust, 1, i), new ItemStack(PXContent.xycroniumIngot, 1, i), 1F);
        }

        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 5; j++){
                GameRegistry.addRecipe(new ItemStack(PXContent.xycroniumPlate, 4, i), new Object[]{"XCX", "CVC", "XCX", 'X', new ItemStack(PXContent.xycroniumIngot, 1, j), 'C', Blocks.STONEBRICK, 'V', new ItemStack(Items.DYE, 1, i)});
                GameRegistry.addRecipe(new ItemStack(PXContent.xycroniumPlatform, 4, i), new Object[]{"XCX", "CVC", "XCX", 'X', new ItemStack(PXContent.xycroniumIngot, 1, j), 'C', Blocks.GLASS, 'V', new ItemStack(Items.DYE, 1, i)});
                GameRegistry.addRecipe(new ItemStack(PXContent.xycroniumStructure, 4, i), new Object[]{"XCX", "CVC", "XCX", 'X', new ItemStack(PXContent.xycroniumCrystal, 1, j), 'C', Blocks.STONEBRICK, 'V', new ItemStack(Items.DYE, 1, i)});
            }

            GameRegistry.addRecipe(new ItemStack(PXContent.xycroniumShield, 4, i), new Object[]{"XCX", "CXC", "XCX", 'X', Items.IRON_INGOT, 'C', new ItemStack(PXContent.xycroniumPlate, 1, i)});
        }

        GameRegistry.addShapelessRecipe(new ItemStack(PXContent.cornKernel, 2, 0), new Object[]{new ItemStack(PXContent.corn, 1, 0)});
        GameRegistry.addSmelting(new ItemStack(PXContent.cornKernel, 1, 0), new ItemStack(PXContent.popcorn, 1, 0), 1F);
        GameRegistry.addSmelting(new ItemStack(PXContent.corn, 1, 0), new ItemStack(PXContent.cobOCorn, 1, 0), 1F);
        GameRegistry.addRecipe(new ItemStack(PXContent.quartzCrystal, 1, 0), new Object[]{" X ", " X ", " X ", 'X', Items.QUARTZ});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.QUARTZ, 3, 0), new Object[]{new ItemStack(PXContent.quartzCrystal, 1, 0)});
    }

}
