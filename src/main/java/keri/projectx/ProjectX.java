package keri.projectx;

import keri.projectx.common.init.ProjectXContent;
import keri.projectx.common.util.IProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static keri.projectx.common.util.ModPrefs.*;

@Mod(modid = MODID, name = NAME, version = VERSION, dependencies = DEPS, acceptedMinecraftVersions = ACC_MC)
public class ProjectX {

    @Mod.Instance(value = MODID)
    public static ProjectX INSTANCE = new ProjectX();
    @SidedProxy(clientSide = CSIDE, serverSide = SSIDE)
    public static IProxy PROXY;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ProjectXContent.preInit();
        PROXY.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        PROXY.init(event);
        ProjectXContent.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        PROXY.postInit(event);
    }

}
