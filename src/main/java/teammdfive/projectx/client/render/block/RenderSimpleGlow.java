package teammdfive.projectx.client.render.block;

import codechicken.lib.vec.Cuboid6;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teammdfive.projectx.client.render.IRenderingHandler;
import teammdfive.projectx.client.render.ModelBuilder;
import teammdfive.projectx.client.render.TextureGetter;
import teammdfive.projectx.common.block.BlockBase;
import teammdfive.projectx.common.property.CommonProperties;
import teammdfive.projectx.common.property.EnumColorType;
import teammdfive.projectx.common.property.EnumMCColorType;
import teammdfive.projectx.common.util.IMetaBlock;
import teammdfive.projectx.common.util.ModPrefs;

import java.util.Collection;

@SideOnly(Side.CLIENT)
public class RenderSimpleGlow implements IRenderingHandler {

    private final ResourceLocation animation;
    private final ResourceLocation location;

    public RenderSimpleGlow(BlockBase block){
        if(block == null){
            throw new IllegalArgumentException("Block can't be null !");
        }

        this.animation = new ResourceLocation(ModPrefs.MODID, "blocks/animation");
        this.location = new ResourceLocation(ModPrefs.MODID, "blocks/" + block.getInternalName());
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        return Lists.newArrayList(this.animation, this.location);
    }

    @Override
    public void render(ModelBuilder builder, TextureGetter textureGetter, BlockRenderLayer layer, IBlockState state, EnumFacing face, long random) {
        if(state.getBlock() instanceof IMetaBlock){
            IMetaBlock iface = (IMetaBlock)state.getBlock();

            if(iface.getSubNames().length == EnumColorType.values().length){
                EnumColorType type = (EnumColorType)state.getValue(CommonProperties.COLOR);

                switch(layer){
                    case SOLID:
                        builder.setTexture(textureGetter.getTexture(this.animation)).setFullbright(true).setColor(ModPrefs.xyColors[type.getID()]).addCuboid(new Cuboid6(0D, 0D, 0D, 16D, 16D, 16D));
                        break;
                    case CUTOUT_MIPPED:
                        builder.setTexture(textureGetter.getTexture(this.location)).addCuboid(new Cuboid6(0D, 0D, 0D, 16D, 16D, 16D));
                        break;
                }
            }
            else if(iface.getSubNames().length == EnumMCColorType.values().length){
                EnumMCColorType type = (EnumMCColorType)state.getValue(CommonProperties.MC_COLOR);

                switch(layer){
                    case SOLID:
                        builder.setTexture(textureGetter.getTexture(this.animation)).setFullbright(true).setColor(ModPrefs.mcColors[type.getID()]).addCuboid(new Cuboid6(0D, 0D, 0D, 16D, 16D, 16D));
                        break;
                    case CUTOUT_MIPPED:
                        builder.setTexture(textureGetter.getTexture(this.location)).addCuboid(new Cuboid6(0D, 0D, 0D, 16D, 16D, 16D));
                        break;
                }
            }
        }
    }

    @Override
    public void renderInventory(ModelBuilder builder, TextureGetter textureGetter, ItemStack stack, long random) {
        if(Block.getBlockFromItem(stack.getItem()) instanceof IMetaBlock){
            IMetaBlock iface = (IMetaBlock)Block.getBlockFromItem(stack.getItem());

            if(iface.getSubNames().length == EnumColorType.values().length){
                builder.setTexture(textureGetter.getTexture(this.animation)).setColor(ModPrefs.xyColors[stack.getMetadata()]).addCuboid(new Cuboid6(0D, 0D, 0D, 16D, 16D, 16D));
                builder.setTexture(textureGetter.getTexture(this.location)).addCuboid(new Cuboid6(0D, 0D, 0D, 16D, 16D, 16D));
            }
            else if(iface.getSubNames().length == EnumMCColorType.values().length){
                builder.setTexture(textureGetter.getTexture(this.animation)).setColor(ModPrefs.mcColors[stack.getMetadata()]).addCuboid(new Cuboid6(0D, 0D, 0D, 16D, 16D, 16D));
                builder.setTexture(textureGetter.getTexture(this.location)).addCuboid(new Cuboid6(0D, 0D, 0D, 16D, 16D, 16D));
            }
        }
    }

    @Override
    public TextureAtlasSprite getParticleTexture(TextureGetter textureGetter) {
        return textureGetter.getTexture(this.location);
    }

}
