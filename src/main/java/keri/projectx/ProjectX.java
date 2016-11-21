package keri.projectx;

import keri.projectx.common.init.PXConfig;
import keri.projectx.common.init.PXContent;
import keri.projectx.common.init.PXCrafting;
import keri.projectx.common.util.IProxy;
import keri.projectx.common.util.ModPrefs;
import keri.projectx.common.world.PXWorldGen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ModPrefs.MODID, name = ModPrefs.NAME, version = ModPrefs.VERSION, dependencies = ModPrefs.DEPS, acceptedMinecraftVersions = ModPrefs.ACC_MC)
public class ProjectX {

    @Mod.Instance(value = ModPrefs.MODID)
    public static ProjectX INSTANCE = new ProjectX();
    @SidedProxy(clientSide = ModPrefs.CSIDE, serverSide = ModPrefs.SSIDE)
    public static IProxy PROXY;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        new PXConfig(event);
        PXContent.preInit();
        PROXY.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        PROXY.init(event);
        PXContent.init();
        PXCrafting.init();
        GameRegistry.registerWorldGenerator(new PXWorldGen(), 1);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        PROXY.postInit(event);
    }

}
