package teammdfive.projectx.client.render;

import codechicken.lib.colour.ColourRGBA;
import codechicken.lib.vec.Vector3;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import teammdfive.projectx.api.energy.EnumXynergyClass;
import teammdfive.projectx.api.energy.EnumXynergyType;

@SideOnly(Side.CLIENT)
public class RenderPowerCore {

    private static final RenderTruncatedIcosahedron renderIcosa = new RenderTruncatedIcosahedron();

    public static void render(ColourRGBA colorCore, ColourRGBA colorShell, Vector3 translation, boolean spin){
        GL11.glPushMatrix();
        GL11.glTranslated(translation.x, translation.y, translation.z);
        GL11.glBlendFunc(770, 1);

        if(spin){
            GL11.glRotated(RenderTickHandler.getRenderTime(), 0D, 1D, 0D);
        }

        renderIcosa.render(0.7222D, colorCore.copy(), colorCore.copy().multiplyC(0.8D), RenderTruncatedIcosahedron.EnumHedrontexture.FILL);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated(translation.x, translation.y, translation.z);
        GL11.glBlendFunc(770, 1);

        if(spin){
            GL11.glRotated((-RenderTickHandler.getRenderTime() * 20D) * 0.2D, 0D, 1D, 0D);
            GL11.glRotated((-RenderTickHandler.getRenderTime() * 5D) * 0.2D, 0D, 0D, 1D);
            GL11.glRotated((-RenderTickHandler.getRenderTime() * -2D) * 0.2D, 1D, 0D, 0D);
        }

        renderIcosa.render(1D, colorShell.copy(), colorShell.copy().multiplyC(0.8D), RenderTruncatedIcosahedron.EnumHedrontexture.SPACE);
        GL11.glPopMatrix();
    }

    public static void render(EnumXynergyClass powerClass, EnumXynergyType powerType, Vector3 translation, boolean spin){
        ColourRGBA colorCore = null;
        ColourRGBA colorShell = null;

        switch(powerClass){
            case LOW:
                colorCore = new ColourRGBA(210, 255, 20, 255);
                break;
            case MIDDLE:
                colorCore = new ColourRGBA(20, 200, 20, 255);
                break;
            case OMNIPOTENT:
                colorCore = new ColourRGBA(255, 55, 55, 255);
                break;
        }

        switch(powerType){
            case NORMAL:
                colorShell = new ColourRGBA(206, 206, 206, 255);
                break;
            case RADIAL:
                colorShell = new ColourRGBA(200, 0, 255, 255);
                break;
            case LONGDISTANCE:
                colorShell = new ColourRGBA(30, 110, 255, 255);
                break;
        }

        render(colorCore, colorShell, translation, spin);
    }

}
