package teammdfive.projectx;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teammdfive.projectx.common.init.PXConfig;
import teammdfive.projectx.common.init.PXContent;
import teammdfive.projectx.common.init.PXCrafting;
import teammdfive.projectx.common.util.IProxy;
import teammdfive.projectx.common.world.PXWorldGen;

import static teammdfive.projectx.common.util.ModPrefs.*;

@Mod(modid = MODID, name = NAME, version = VERSION, dependencies = DEPS, acceptedMinecraftVersions = ACC_MC)
public class ProjectX {

    @Mod.Instance(value = MODID)
    public static ProjectX INSTANCE = new ProjectX();
    @SidedProxy(clientSide = CSIDE, serverSide = SSIDE)
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
