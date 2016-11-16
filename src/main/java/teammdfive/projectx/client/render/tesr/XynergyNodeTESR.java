package teammdfive.projectx.client.render.tesr;

import codechicken.lib.vec.Vector3;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teammdfive.projectx.client.render.RenderPowerCore;
import teammdfive.projectx.common.tile.TileXynergyNode;
import teammdfive.projectx.common.util.ModPrefs;
import teammdfive.projectx.common.util.ResourceAction;

@SideOnly(Side.CLIENT)
public class XynergyNodeTESR extends TileEntitySpecialRenderer<TileXynergyNode> {

    private static final ResourceAction beamTexture = new ResourceAction(ModPrefs.MODID, "textures/particle/xynergy_beam.png");

    @Override
    public void renderTileEntityAt(TileXynergyNode tile, double x, double y, double z, float partialTicks, int destroyStage) {
        final VertexBuffer buffer = Tessellator.getInstance().getBuffer();
        GlStateManager.pushMatrix();

        if(tile.getHasCore()){
            Vector3 translation = new Vector3(x, y, z).add(0.5D, 0.5D, 0.5D);
            RenderPowerCore.render(tile.getXynergyClass(), tile.getXynergyType(), translation, true);
        }

        GlStateManager.popMatrix();
    }

}
