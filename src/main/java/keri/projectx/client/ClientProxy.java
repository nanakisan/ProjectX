package keri.projectx.client;

import keri.projectx.client.model.CustomModelLoader;
import keri.projectx.client.render.RenderTickHandler;
import keri.projectx.client.render.block.RenderSimpleGlow;
import keri.projectx.client.render.block.RenderXycroniumOre;
import keri.projectx.client.render.item.RenderItemPowerCore;
import keri.projectx.common.init.ProjectXContent;
import keri.projectx.common.util.ClientHelper;
import keri.projectx.common.util.IProxy;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event){
        ModelLoaderRegistry.registerLoader(CustomModelLoader.INSTANCE);
        MinecraftForge.EVENT_BUS.register(new RenderTickHandler());

        ClientHelper.registerRenderer(ProjectXContent.xycroniumOre, new RenderXycroniumOre());
        ClientHelper.registerRenderer(ProjectXContent.xycroniumBlock, new RenderSimpleGlow(ProjectXContent.xycroniumBlock));
        ClientHelper.registerRenderer(ProjectXContent.xycroniumBricks, new RenderSimpleGlow(ProjectXContent.xycroniumBricks));
        ClientHelper.registerRenderer(ProjectXContent.xycroniumPlate, new RenderSimpleGlow(ProjectXContent.xycroniumPlate));
        ClientHelper.registerRenderer(ProjectXContent.xycroniumPlatform, new RenderSimpleGlow(ProjectXContent.xycroniumPlatform));
        ClientHelper.registerRenderer(ProjectXContent.xycroniumStructure, new RenderSimpleGlow(ProjectXContent.xycroniumStructure));
        ClientHelper.registerRenderer(ProjectXContent.xycroniumShield, new RenderSimpleGlow(ProjectXContent.xycroniumShield));

        ClientHelper.autoRegister(ProjectXContent.xycroniumCrystal);
        ClientHelper.autoRegister(ProjectXContent.xycroniumIngot);
        ClientHelper.autoRegister(ProjectXContent.xycroniumNugget);
        ClientHelper.autoRegister(ProjectXContent.xycroniumDust);

        ClientHelper.registerBuiltin(ProjectXContent.powerCore, new RenderItemPowerCore());
    }

    @Override
    public void init(FMLInitializationEvent event){

    }

    @Override
    public void postInit(FMLPostInitializationEvent event){

    }

}
