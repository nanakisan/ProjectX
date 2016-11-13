package teammdfive.projectx.client;

import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import teammdfive.projectx.client.model.CustomModelLoader;
import teammdfive.projectx.client.render.RenderTickHandler;
import teammdfive.projectx.client.render.block.RenderSimpleGlow;
import teammdfive.projectx.client.render.block.RenderXycroniumOre;
import teammdfive.projectx.client.render.item.RenderItemPowerCore;
import teammdfive.projectx.common.init.PXContent;
import teammdfive.projectx.common.util.ClientHelper;
import teammdfive.projectx.common.util.IProxy;

public class ClientProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event){
        ModelLoaderRegistry.registerLoader(CustomModelLoader.INSTANCE);
        MinecraftForge.EVENT_BUS.register(new RenderTickHandler());

        ClientHelper.registerRenderer(PXContent.xycroniumOre, new RenderXycroniumOre());
        ClientHelper.registerRenderer(PXContent.xycroniumBlock, new RenderSimpleGlow(PXContent.xycroniumBlock));
        ClientHelper.registerRenderer(PXContent.xycroniumBricks, new RenderSimpleGlow(PXContent.xycroniumBricks));
        ClientHelper.registerRenderer(PXContent.xycroniumPlate, new RenderSimpleGlow(PXContent.xycroniumPlate));
        ClientHelper.registerRenderer(PXContent.xycroniumPlatform, new RenderSimpleGlow(PXContent.xycroniumPlatform));
        ClientHelper.registerRenderer(PXContent.xycroniumStructure, new RenderSimpleGlow(PXContent.xycroniumStructure));
        ClientHelper.registerRenderer(PXContent.xycroniumShield, new RenderSimpleGlow(PXContent.xycroniumShield));

        ClientHelper.autoRegister(PXContent.xycroniumCrystal);
        ClientHelper.autoRegister(PXContent.xycroniumIngot);
        ClientHelper.autoRegister(PXContent.xycroniumNugget);
        ClientHelper.autoRegister(PXContent.xycroniumDust);
        ClientHelper.autoRegister(Item.getItemFromBlock(PXContent.sulfurTorch));
        ClientHelper.autoRegister(Item.getItemFromBlock(PXContent.aluminumTorch));

        ClientHelper.registerBuiltin(PXContent.powerCore, new RenderItemPowerCore());
    }

    @Override
    public void init(FMLInitializationEvent event){

    }

    @Override
    public void postInit(FMLPostInitializationEvent event){

    }

}
