package keri.projectx.client;

import keri.projectx.client.model.CustomModelLoader;
import keri.projectx.client.render.AnimationFX;
import keri.projectx.client.render.IconRegistry;
import keri.projectx.client.render.RenderTickHandler;
import keri.projectx.client.render.block.RenderSimpleGlow;
import keri.projectx.client.render.block.RenderXycroniumOre;
import keri.projectx.client.render.block.RenderXynergyNode;
import keri.projectx.client.render.item.RenderItemPowerCore;
import keri.projectx.client.render.item.RenderItemQuartzCrystal;
import keri.projectx.client.render.tesr.XynergyNodeTESR;
import keri.projectx.common.init.PXConfig;
import keri.projectx.common.init.PXContent;
import keri.projectx.common.tile.TileXynergyNode;
import keri.projectx.common.util.ClientHelper;
import keri.projectx.common.util.IProxy;
import keri.projectx.common.util.ModPrefs;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
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

public class ClientProxy implements IProxy {

    private static TextureAtlasSprite animationIcon;

    @Override
    public void preInit(FMLPreInitializationEvent event){
        ModelLoaderRegistry.registerLoader(CustomModelLoader.INSTANCE);
        MinecraftForge.EVENT_BUS.register(new RenderTickHandler());
        MinecraftForge.EVENT_BUS.register(this);
        animationIcon = new AnimationFX(PXConfig.hdAnimation ? 64 : 32).texture;
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

    public static TextureAtlasSprite getAnimationIcon(){ return animationIcon; }

}
