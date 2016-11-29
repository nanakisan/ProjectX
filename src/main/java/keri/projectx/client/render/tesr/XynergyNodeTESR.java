package keri.projectx.client.render.tesr;

import codechicken.lib.vec.Vector3;
import keri.projectx.client.render.RenderPowerCore;
import keri.projectx.common.tile.TileXynergyNode;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class XynergyNodeTESR extends TileEntitySpecialRenderer<TileXynergyNode> {

    @Override
    public void renderTileEntityAt(TileXynergyNode tile, double x, double y, double z, float partialTicks, int destroyStage) {
        final VertexBuffer buffer = Tessellator.getInstance().getBuffer();
        Vector3 translation = new Vector3(x, y, z).add(0.5D, 0.5D, 0.5D);
        GlStateManager.pushMatrix();

        if(tile.getHasCore()){
            RenderPowerCore.render(tile.getXynergyClass(), tile.getXynergyType(), translation, true);
        }

        GlStateManager.popMatrix();
    }

}
