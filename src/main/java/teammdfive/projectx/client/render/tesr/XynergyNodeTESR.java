package teammdfive.projectx.client.render.tesr;

import codechicken.lib.vec.Vector3;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teammdfive.projectx.client.render.RenderPowerCore;
import teammdfive.projectx.common.tile.TileXynergyNode;

@SideOnly(Side.CLIENT)
public class XynergyNodeTESR extends TileEntitySpecialRenderer<TileXynergyNode> {

    @Override
    public void renderTileEntityAt(TileXynergyNode tile, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.pushMatrix();

        if(tile.getHasCore()){
            Vector3 translation = new Vector3(x + 0.5D, y + 0.5D, z + 0.5D);
            RenderPowerCore.render(tile.getXynergyClass(), tile.getXynergyType(), translation, true);
        }

        GlStateManager.popMatrix();
    }

}
