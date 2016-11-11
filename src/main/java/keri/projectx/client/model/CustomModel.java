package keri.projectx.client.model;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import keri.projectx.client.render.IRenderingHandler;
import keri.projectx.client.render.ModelBuilder;
import keri.projectx.client.render.TextureGetter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class CustomModel extends AbstractModel {

    private IRenderingHandler renderer;

    public CustomModel(IRenderingHandler renderer){
        if(renderer == null){
            throw new IllegalArgumentException("Renderer can't be null !");
        }

        this.renderer = renderer;
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        return this.renderer.getTextures();
    }

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter){
        return new BakedModel(this.renderer, format, bakedTextureGetter);
    }

    private static class BakedModel extends AbstractBakedModel {

        private IRenderingHandler renderer;
        private VertexFormat format;
        private Function<ResourceLocation, TextureAtlasSprite> textureGetter;

        public BakedModel(IRenderingHandler renderer, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> textureGetter){
            this.renderer = renderer;
            this.format = format;
            this.textureGetter = textureGetter;
        }

        @Override
        public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
            TextureGetter textureGetter = new TextureGetter(this.textureGetter);
            Map<IBlockState, List<BakedQuad>> quadCache = Maps.newHashMap();
            BlockRenderLayer layer = MinecraftForgeClient.getRenderLayer();
            ModelBuilder builder = new ModelBuilder(this.format);
            this.renderer.render(builder, textureGetter, layer, state, side, rand);
            quadCache.put(state, builder.getOutput());
            return quadCache.get(state);
        }

        @Override
        public TextureAtlasSprite getParticleTexture() {
            return this.renderer.getParticleTexture(new TextureGetter(this.textureGetter));
        }

    }

}
