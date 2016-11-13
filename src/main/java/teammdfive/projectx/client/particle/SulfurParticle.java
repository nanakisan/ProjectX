package teammdfive.projectx.client.particle;

import net.minecraft.client.particle.ParticleRedstone;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SulfurParticle extends ParticleRedstone {

    public SulfurParticle(World world, double x, double y, double z, float r, float g, float b) {
        super(world, x, y, z, r, g, b);
    }

    public SulfurParticle(World world, double x, double y, double z, float r, float g, float b, float a) {
        super(world, x, y, z, r, g, b, a);
    }

    @Override
    public int getBrightnessForRender(float partialTicks) {
        return 15 << 20 | 15 << 4;
    }

}
