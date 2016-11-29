package keri.projectx.client.model;

import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.util.TransformUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import keri.projectx.client.render.IRenderingHandler;
import keri.projectx.client.render.ModelBuilder;
import keri.projectx.client.render.TextureGetter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.List;
import java.util.Map;
import java.util.Random;

@SideOnly(Side.CLIENT)
public class CustomItemRenderer implements IItemRenderer, IPerspectiveAwareModel {

    private IRenderingHandler renderer;

    public CustomItemRenderer(IRenderingHandler renderer){
        if(renderer == null){
            throw new IllegalArgumentException("Renderer can't be null !");
        }

        this.renderer = renderer;
    }

    @Override
    public void renderItem(ItemStack stack) {
        Map<Integer, IBakedModel> modelCache = Maps.newHashMap();
        ModelBuilder builder = new ModelBuilder(DefaultVertexFormats.ITEM);
        TextureGetter textureGetter = new TextureGetter(ModelLoader.defaultTextureGetter());
        RenderHelper.enableStandardItemLighting();
        this.renderer.renderInventory(builder, textureGetter, stack, new Random().nextLong());
        modelCache.put(stack.getMetadata(), new BakedModel(builder.getOutput()));
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5D, 0.5D, 0.5D);
        renderItem.renderItem(stack, modelCache.get(stack.getMetadata()));
        GlStateManager.popMatrix();
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
        return Lists.newArrayList();
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return true;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return null;
    }

    @Override
    @SuppressWarnings("deprecation")
    public ItemCameraTransforms getItemCameraTransforms() {
        return ItemCameraTransforms.DEFAULT;
    }

    @Override
    public ItemOverrideList getOverrides() {
        return ItemOverrideList.NONE;
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        return MapWrapper.handlePerspective(this, TransformUtils.DEFAULT_BLOCK.getTransforms(), cameraTransformType);
    }

    private static class BakedModel extends AbstractBakedModel {

        private List<BakedQuad> quads;

        public BakedModel(List<BakedQuad> quads){
            this.quads = quads;
        }

        @Override
        public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
            return this.quads;
        }

        @Override
        public TextureAtlasSprite getParticleTexture() {
            return null;
        }

    }

}
