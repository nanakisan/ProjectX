package keri.projectx.common.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ResourceAction {

    private ResourceLocation location;

    public ResourceAction(String path){
        this.location = new ResourceLocation(path);
    }

    public ResourceAction(String modid, String path){
        this.location = new ResourceLocation(modid, path);
    }

    public ResourceAction(ResourceLocation location){
        this.location = location;
    }

    public void bind(boolean manager){
        if(manager){
            Minecraft.getMinecraft().getTextureManager().bindTexture(this.location);
        }
        else{
            Minecraft.getMinecraft().renderEngine.bindTexture(this.location);
        }
    }

    public ResourceLocation getLocation(){
        return location;
    }

}
