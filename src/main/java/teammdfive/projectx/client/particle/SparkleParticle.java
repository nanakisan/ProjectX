package teammdfive.projectx.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import teammdfive.projectx.common.util.ClientHelper;
import teammdfive.projectx.common.util.ModPrefs;
import teammdfive.projectx.common.util.ResourceAction;

@SideOnly(Side.CLIENT)
public class SparkleParticle extends Particle {

    private final ResourceAction texture = new ResourceAction(ModPrefs.MODID, "textures/particle/particles.png");
    public int multiplier;
    public boolean shrink;
    public int particle;
    public boolean tinkle;
    public int blendmode;

    public SparkleParticle(World world, double d, double d1, double d2, float f, float f1, float f2, float f3, int m) {
        super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
        this.multiplier = 4;
        this.shrink = false;
        this.particle = 0;
        this.tinkle = true;
        this.blendmode = 1;

        if (f1 == 0.0F) {
            f1 = 1.0F;
        }
        super.particleRed = f1;
        super.particleGreen = f2;
        super.particleBlue = f3;
        super.particleGravity = 0.0F;
        super.motionX = super.motionY = super.motionZ = 0.0D;
        super.particleScale *= f;
        super.particleMaxAge = 4 * m;
        this.multiplier = m;
    }

    public SparkleParticle(World world, double x, double y, double z, float scale, int type, int multiplier) {
        this(world, x, y, z, scale, 0.0F, 0.0F, 0.0F, multiplier);

        switch (type) {
            case 0:
                super.particleRed = 0.75F + world.rand.nextFloat() * 0.25F;
                super.particleGreen = 0.25F + world.rand.nextFloat() * 0.25F;
                super.particleBlue = 0.75F + world.rand.nextFloat() * 0.25F;
                break;
            case 1:
                super.particleRed = 0.5F + world.rand.nextFloat() * 0.3F;
                super.particleGreen = 0.5F + world.rand.nextFloat() * 0.3F;
                super.particleBlue = 0.2F;
                break;
            case 2:
                super.particleRed = 0.2F;
                super.particleGreen = 0.2F;
                super.particleBlue = 0.7F + world.rand.nextFloat() * 0.3F;
                break;
            case 3:
                super.particleRed = 0.2F;
                super.particleGreen = 0.7F + world.rand.nextFloat() * 0.3F;
                super.particleBlue = 0.2F;
                break;
            case 4:
                super.particleRed = 0.7F + world.rand.nextFloat() * 0.3F;
                super.particleGreen = 0.2F;
                super.particleBlue = 0.2F;
                break;
            case 5:
                this.blendmode = 771;
                super.particleRed = world.rand.nextFloat() * 0.1F;
                super.particleGreen = world.rand.nextFloat() * 0.1F;
                super.particleBlue = world.rand.nextFloat() * 0.1F;
                break;
            case 6:
                super.particleRed = 0.8F + world.rand.nextFloat() * 0.2F;
                super.particleGreen = 0.8F + world.rand.nextFloat() * 0.2F;
                super.particleBlue = 0.8F + world.rand.nextFloat() * 0.2F;
                break;
            case 7:
                super.particleRed = 0.2F;
                super.particleGreen = 0.5F + world.rand.nextFloat() * 0.3F;
                super.particleBlue = 0.6F + world.rand.nextFloat() * 0.3F;
        }
    }

    public SparkleParticle(World world, double d, double d1, double d2, double x, double y, double z, float f, int type, int m) {
        this(world, d, d1, d2, f, type, m);
        double dx = x - super.posX;
        double dy = y - super.posY;
        double dz = z - super.posZ;
        super.motionX = dx / (double) super.particleMaxAge;
        super.motionY = dy / (double) super.particleMaxAge;
        super.motionZ = dz / (double) super.particleMaxAge;
    }

    @Override
    public void renderParticle(VertexBuffer buffer, Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        Tessellator.getInstance().draw();
        GlStateManager.pushMatrix();
        GlStateManager.depthMask(false);
        GL11.glEnable(3042);
        GlStateManager.blendFunc(770, this.blendmode);
        this.texture.bind(true);
        GlStateManager.color(1F, 1F, 1F, 0.75F);
        int part = this.particle + super.particleAge / this.multiplier;
        float var8 = (float) (part % 8) / 8.0F;
        float var9 = var8 + 0.124875F;
        float var10 = (float) (part / 8) / 8.0F;
        float var11 = var10 + 0.124875F;
        float var12 = 0.1F * super.particleScale * ((float) (super.particleMaxAge - super.particleAge + 1) / (float) super.particleMaxAge);
        float var13 = (float) (super.prevPosX + (super.posX - super.prevPosX) * (double) f - Particle.interpPosX);
        float var14 = (float) (super.prevPosY + (super.posY - super.prevPosY) * (double) f - Particle.interpPosY);
        float var15 = (float) (super.prevPosZ + (super.posZ - super.prevPosZ) * (double) f - Particle.interpPosZ);
        float var16 = 1.0F;
        int lightMapU = 16 & 65535;
        int lightMapV = 65535;
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        buffer.pos((double)(var13 - f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 - f3 * var12 - f5 * var12)).tex((double)var9, (double)var11).lightmap(lightMapU, lightMapV).color(super.particleRed * var16, super.particleGreen * var16, super.particleBlue * var16, 1.0F).endVertex();
        buffer.pos((double)(var13 - f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 - f3 * var12 + f5 * var12)).tex((double)var9, (double)var10).lightmap(lightMapU, lightMapV).color(super.particleRed * var16, super.particleGreen * var16, super.particleBlue * var16, 1.0F).endVertex();
        buffer.pos((double)(var13 + f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 + f3 * var12 + f5 * var12)).tex((double)var8, (double)var10).lightmap(lightMapU, lightMapV).color(super.particleRed * var16, super.particleGreen * var16, super.particleBlue * var16, 1.0F).endVertex();
        buffer.pos((double)(var13 + f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 + f3 * var12 - f5 * var12)).tex((double)var8, (double)var11).lightmap(lightMapU, lightMapV).color(super.particleRed * var16, super.particleGreen * var16, super.particleBlue * var16, 1.0F).endVertex();
        Tessellator.getInstance().draw();
        GL11.glDisable(3042);
        GlStateManager.depthMask(true);
        GlStateManager.popMatrix();
        ClientHelper.resetParticleTexture();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
    }

    @Override
    public void onUpdate() {
        try {
            EntityPlayerSP e = Minecraft.getMinecraft().thePlayer;
            byte visibleDistance = 50;

            if (!Minecraft.getMinecraft().gameSettings.fancyGraphics) {
                visibleDistance = 25;
            }

            if (e.getDistance(super.posX, super.posY, super.posZ) > (double) visibleDistance) {
                this.setExpired();
            }

            super.prevPosX = super.posX;
            super.prevPosY = super.posY;
            super.prevPosZ = super.posZ;

            //if (super.particleAge == 0 && this.tinkle && super.worldObj.rand.nextInt(10) == 0) {
            //    super.worldObj.playSoundAtEntity(this, "random.orb", 0.02F, 0.7F * ((super.worldObj.rand.nextFloat() - super.worldObj.rand.nextFloat()) * 0.6F + 2.0F));
            //}

            if (super.particleAge++ >= super.particleMaxAge) {
                this.setExpired();
            }

            super.motionY -= 0.04D * (double) super.particleGravity;
            this.moveEntity(super.motionX, super.motionY, super.motionZ);
            super.motionX *= 0.9800000190734863D;
            super.motionY *= 0.9800000190734863D;
            super.motionZ *= 0.9800000190734863D;

            if (super.isCollided) {
                super.motionX *= 0.699999988079071D;
                super.motionZ *= 0.699999988079071D;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void setGravity(float value) {
        super.particleGravity = value;
    }

    public void setParticle(int part) {
        this.particle = part;
    }

}
