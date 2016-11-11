package teammdfive.projectx.client.render.block;

import com.google.common.collect.Lists;
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
import teammdfive.projectx.common.util.ModPrefs;

import java.util.Collection;

@SideOnly(Side.CLIENT)
public class RenderXynergyNode implements IRenderingHandler {

    @Override
    public Collection<ResourceLocation> getTextures() {
        return Lists.newArrayList(
                new ResourceLocation(ModPrefs.MODID, "models/xynergy_node_post"),
                new ResourceLocation(ModPrefs.MODID, "models/xynergy_node_base")
        );
    }

    @Override
    public void render(ModelBuilder builder, TextureGetter textureGetter, BlockRenderLayer layer, IBlockState state, EnumFacing face, long random) {

    }

    @Override
    public void renderInventory(ModelBuilder builder, TextureGetter textureGetter, ItemStack stack, long random) {

    }

    @Override
    public TextureAtlasSprite getParticleTexture(TextureGetter textureGetter) {
        return textureGetter.getTexture(new ResourceLocation(ModPrefs.MODID, "models/xynergy_node_base"));
    }

}
