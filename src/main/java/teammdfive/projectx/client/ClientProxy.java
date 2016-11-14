package teammdfive.projectx.client;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teammdfive.projectx.client.model.CustomModelLoader;
import teammdfive.projectx.client.render.IconRegistry;
import teammdfive.projectx.client.render.RenderTickHandler;
import teammdfive.projectx.client.render.block.RenderSimpleGlow;
import teammdfive.projectx.client.render.block.RenderXycroniumOre;
import teammdfive.projectx.client.render.block.RenderXynergyNode;
import teammdfive.projectx.client.render.item.RenderItemPowerCore;
import teammdfive.projectx.client.render.item.RenderItemQuartzCrystal;
import teammdfive.projectx.client.render.tesr.XynergyNodeTESR;
import teammdfive.projectx.common.init.PXContent;
import teammdfive.projectx.common.tile.TileXynergyNode;
import teammdfive.projectx.common.util.ClientHelper;
import teammdfive.projectx.common.util.IProxy;
import teammdfive.projectx.common.util.ModPrefs;

public class ClientProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event){
        ModelLoaderRegistry.registerLoader(CustomModelLoader.INSTANCE);
        MinecraftForge.EVENT_BUS.register(new RenderTickHandler());
        MinecraftForge.EVENT_BUS.register(this);
        ClientHelper.registerRenderer(PXContent.xycroniumOre, new RenderXycroniumOre());
        ClientHelper.registerRenderer(PXContent.xycroniumBlock, new RenderSimpleGlow(PXContent.xycroniumBlock));
        ClientHelper.registerRenderer(PXContent.xycroniumBricks, new RenderSimpleGlow(PXContent.xycroniumBricks));
        ClientHelper.registerRenderer(PXContent.xycroniumPlate, new RenderSimpleGlow(PXContent.xycroniumPlate));
        ClientHelper.registerRenderer(PXContent.xycroniumPlatform, new RenderSimpleGlow(PXContent.xycroniumPlatform));
        ClientHelper.registerRenderer(PXContent.xycroniumStructure, new RenderSimpleGlow(PXContent.xycroniumStructure));
        ClientHelper.registerRenderer(PXContent.xycroniumShield, new RenderSimpleGlow(PXContent.xycroniumShield));
        ClientHelper.registerRenderer(PXContent.xynergyNode, new RenderXynergyNode());
        ClientRegistry.bindTileEntitySpecialRenderer(TileXynergyNode.class, new XynergyNodeTESR());
        ClientHelper.autoRegister(PXContent.xycroniumCrystal);
        ClientHelper.autoRegister(PXContent.xycroniumIngot);
        ClientHelper.autoRegister(PXContent.xycroniumNugget);
        ClientHelper.autoRegister(PXContent.xycroniumDust);
        ClientHelper.autoRegister(Item.getItemFromBlock(PXContent.sulfurTorch));
        ClientHelper.autoRegister(Item.getItemFromBlock(PXContent.aluminumTorch));
        ClientHelper.autoRegister(PXContent.xynergyTool);
        ClientHelper.autoRegister(PXContent.debugger);
        ClientHelper.autoRegister(PXContent.cornKernel);
        ClientHelper.autoRegister(PXContent.corn);
        ClientHelper.autoRegister(PXContent.cobOCorn);
        ClientHelper.autoRegister(PXContent.popcorn);
        ClientHelper.autoRegister(PXContent.sulfurGoo);
        ClientHelper.autoRegister(Item.getItemFromBlock(PXContent.cornCrop));
        ClientHelper.registerBuiltin(PXContent.powerCore, new RenderItemPowerCore());
        ClientHelper.registerBuiltin(PXContent.quartzCrystal, new RenderItemQuartzCrystal());
    }

    @Override
    public void init(FMLInitializationEvent event){

    }

    @Override
    public void postInit(FMLPostInitializationEvent event){

    }

    @SubscribeEvent
    public void registerIcons(TextureStitchEvent.Pre event){
        IconRegistry.addIcon("quartz_crystal", new ResourceLocation(ModPrefs.MODID, "items/quartz_crystal"), event.getMap());
    }

}
