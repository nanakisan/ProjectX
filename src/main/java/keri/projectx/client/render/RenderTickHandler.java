package keri.projectx.client.render;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTickHandler {

    private static int renderTime = 0;
    private static float renderFrame = 0F;
    private static int r = 255;
    private static int g = 0;
    private static int b = 0;

    @SubscribeEvent
    public void clientTickEvent(TickEvent.ClientTickEvent e){
        if(e.phase == TickEvent.Phase.END && !Minecraft.getMinecraft().isGamePaused()){
            renderTime += 1;
        }
    }

    @SubscribeEvent
    public void renderTickEvent(TickEvent.RenderTickEvent e) {
        if(e.phase == TickEvent.Phase.END && !Minecraft.getMinecraft().isGamePaused()){
            renderFrame = e.renderTickTime;
        }

        if(!Minecraft.getMinecraft().isGamePaused()){
            if (r > 0 && b == 0) {
                r -= 1;
                g += 1;
            }
            else if (g > 0) {
                g -= 1;
                b += 1;
            }
            else if (b > 0) {
                b -= 1;
                r += 1;
            }
        }
    }

    public static int getRenderTime(){
        return renderTime;
    }

    public static float getRenderFrame(){
        return renderFrame;
    }

    public static int getR(){
        return r;
    }

    public static int getG(){
        return g;
    }

    public static int getB(){
        return b;
    }

}
