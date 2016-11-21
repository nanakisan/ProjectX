package keri.projectx.client.render.block;

import codechicken.lib.colour.ColourRGBA;
import codechicken.lib.vec.Cuboid6;
import com.google.common.collect.Lists;
import keri.projectx.client.render.IRenderingHandler;
import keri.projectx.client.render.IconRegistry;
import keri.projectx.client.render.ModelBuilder;
import keri.projectx.client.render.TextureGetter;
import keri.projectx.common.property.CommonProperties;
import keri.projectx.common.property.EnumTransformerMode;
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
public class RenderXynergyTransformer implements IRenderingHandler {

    //// TODO: 21.11.2016 Implement proper rendering for Xynergy Transformer

    @Override
    public Collection<ResourceLocation> getTextures() {
        return Lists.newArrayList(
                new ResourceLocation(ModPrefs.MODID, "blocks/xynergy_transformer")
        );
    }

    @Override
    public void render(ModelBuilder builder, TextureGetter textureGetter, BlockRenderLayer layer, IBlockState state, EnumFacing face, long random) {
        if(layer != null){
            switch(layer){
                case SOLID:
                    EnumTransformerMode mode = (EnumTransformerMode)state.getValue(CommonProperties.TRANSFORMER_MODE);
                    ColourRGBA color = ModPrefs.xyColors[mode.getID()];
                    builder.setTexture(IconRegistry.getIcon("animation")).setFullbright(true).setColor(color).addCuboid(new Cuboid6(0D, 0D, 0D, 16D, 8D, 16D));
                    break;
                case CUTOUT_MIPPED:
                    break;
            }
        }
        else{

        }
    }

    @Override
    public void renderInventory(ModelBuilder builder, TextureGetter textureGetter, ItemStack stack, long random) {

    }

    @Override
    public TextureAtlasSprite getParticleTexture(TextureGetter textureGetter) {
        return textureGetter.getTexture("xynergy_transformer");
    }

}
