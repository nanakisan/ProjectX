package keri.projectx.client.render.block;

import codechicken.lib.colour.ColourRGBA;
import codechicken.lib.vec.Cuboid6;
import com.google.common.collect.Lists;
import keri.projectx.client.ClientProxy;
import keri.projectx.client.render.IRenderingHandler;
import keri.projectx.client.render.ModelBuilder;
import keri.projectx.client.render.TextureGetter;
import keri.projectx.common.util.ModPrefs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collection;

@SideOnly(Side.CLIENT)
public class RenderXynergyNode implements IRenderingHandler {

    @Override
    public Collection<ResourceLocation> getTextures() {
        return Lists.newArrayList(
                new ResourceLocation(ModPrefs.MODID, "blocks/xynergy_node")
        );
    }

    @Override
    public void render(ModelBuilder builder, TextureGetter textureGetter, BlockRenderLayer layer, IBlockState state, EnumFacing face, long random) {
        if(layer == BlockRenderLayer.SOLID){
            builder.setTexture(ClientProxy.getAnimationIcon()).setFullbright(true).setColor(new ColourRGBA(100, 255, 255, 255)).addCuboid(new Cuboid6(2D, 0D, 2D, 4D, 2D, 14D));
            builder.setTexture(ClientProxy.getAnimationIcon()).setFullbright(true).setColor(new ColourRGBA(100, 255, 255, 255)).addCuboid(new Cuboid6(2D, 0D, 2D, 14D, 2D, 4D));
            builder.setTexture(ClientProxy.getAnimationIcon()).setFullbright(true).setColor(new ColourRGBA(100, 255, 255, 255)).addCuboid(new Cuboid6(12D, 0D, 2D, 14D, 2D, 14D));
            builder.setTexture(ClientProxy.getAnimationIcon()).setFullbright(true).setColor(new ColourRGBA(100, 255, 255, 255)).addCuboid(new Cuboid6(2D, 0D, 12D, 14D, 2D, 14D));
        }
        else if(layer == BlockRenderLayer.CUTOUT_MIPPED){
            builder.setTexture(textureGetter.getTexture("xynergy_node")).addCuboid(new Cuboid6(2D, 0D, 2D, 4D, 2D, 14D));
            builder.setTexture(textureGetter.getTexture("xynergy_node")).addCuboid(new Cuboid6(2D, 0D, 2D, 14D, 2D, 4D));
            builder.setTexture(textureGetter.getTexture("xynergy_node")).addCuboid(new Cuboid6(12D, 0D, 2D, 14D, 2D, 14D));
            builder.setTexture(textureGetter.getTexture("xynergy_node")).addCuboid(new Cuboid6(2D, 0D, 12D, 14D, 2D, 14D));
        }
    }

    @Override
    public void renderInventory(ModelBuilder builder, TextureGetter textureGetter, ItemStack stack, long random) {
        builder.setTexture(ClientProxy.getAnimationIcon()).setColor(new ColourRGBA(100, 255, 255, 255)).addCuboid(new Cuboid6(2D, 0D, 2D, 4D, 2D, 14D));
        builder.setTexture(ClientProxy.getAnimationIcon()).setColor(new ColourRGBA(100, 255, 255, 255)).addCuboid(new Cuboid6(2D, 0D, 2D, 14D, 2D, 4D));
        builder.setTexture(ClientProxy.getAnimationIcon()).setColor(new ColourRGBA(100, 255, 255, 255)).addCuboid(new Cuboid6(12D, 0D, 2D, 14D, 2D, 14D));
        builder.setTexture(ClientProxy.getAnimationIcon()).setColor(new ColourRGBA(100, 255, 255, 255)).addCuboid(new Cuboid6(2D, 0D, 12D, 14D, 2D, 14D));

        builder.setTexture(textureGetter.getTexture("xynergy_node")).addCuboid(new Cuboid6(2D, 0D, 2D, 4D, 2D, 14D));
        builder.setTexture(textureGetter.getTexture("xynergy_node")).addCuboid(new Cuboid6(2D, 0D, 2D, 14D, 2D, 4D));
        builder.setTexture(textureGetter.getTexture("xynergy_node")).addCuboid(new Cuboid6(12D, 0D, 2D, 14D, 2D, 14D));
        builder.setTexture(textureGetter.getTexture("xynergy_node")).addCuboid(new Cuboid6(2D, 0D, 12D, 14D, 2D, 14D));
    }

    @Override
    public TextureAtlasSprite getParticleTexture(TextureGetter textureGetter) {
        return textureGetter.getTexture("xynergy_node");
    }

}
