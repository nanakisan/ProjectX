package keri.projectx.client.render;

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
public interface IRenderingHandler {

    Collection<ResourceLocation> getTextures();

    void render(ModelBuilder builder, TextureGetter textureGetter, BlockRenderLayer layer, IBlockState state, EnumFacing face, long random);

    void renderInventory(ModelBuilder builder, TextureGetter textureGetter, ItemStack stack, long random);

    TextureAtlasSprite getParticleTexture(TextureGetter textureGetter);

}
