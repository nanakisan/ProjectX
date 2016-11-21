package keri.projectx.client.render.item;

import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.util.TransformUtils;
import codechicken.lib.vec.Vector3;
import com.google.common.collect.Lists;
import keri.projectx.api.energy.EnumXynergyClass;
import keri.projectx.api.energy.EnumXynergyType;
import keri.projectx.client.render.RenderPowerCore;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.List;

@SideOnly(Side.CLIENT)
public class RenderItemPowerCore implements IItemRenderer, IPerspectiveAwareModel {

    //// TODO: 21.11.2016 Fix inventory rendering glitch

    @Override
    public void renderItem(ItemStack stack) {
        RenderHelper.enableStandardItemLighting();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5D, 0.5D, 0.5D);
        GlStateManager.enableBlend();

        switch(stack.getMetadata()){
            case 0:
                RenderPowerCore.render(EnumXynergyClass.LOW, EnumXynergyType.NORMAL, new Vector3(0D, 0D, 0D), true);
                break;
            case 1:
                RenderPowerCore.render(EnumXynergyClass.MIDDLE, EnumXynergyType.NORMAL, new Vector3(0D, 0D, 0D), true);
                break;
            case 2:
                RenderPowerCore.render(EnumXynergyClass.OMNIPOTENT, EnumXynergyType.NORMAL, new Vector3(0D, 0D, 0D), true);
                break;
            case 3:
                RenderPowerCore.render(EnumXynergyClass.LOW, EnumXynergyType.RADIAL, new Vector3(0D, 0D, 0D), true);
                break;
            case 4:
                RenderPowerCore.render(EnumXynergyClass.MIDDLE, EnumXynergyType.RADIAL, new Vector3(0D, 0D, 0D), true);
                break;
            case 5:
                RenderPowerCore.render(EnumXynergyClass.OMNIPOTENT, EnumXynergyType.RADIAL, new Vector3(0D, 0D, 0D), true);
                break;
            case 6:
                RenderPowerCore.render(EnumXynergyClass.LOW, EnumXynergyType.LONGDISTANCE, new Vector3(0D, 0D, 0D), true);
                break;
            case 7:
                RenderPowerCore.render(EnumXynergyClass.MIDDLE, EnumXynergyType.LONGDISTANCE, new Vector3(0D, 0D, 0D), true);
                break;
            case 8:
                RenderPowerCore.render(EnumXynergyClass.OMNIPOTENT, EnumXynergyType.LONGDISTANCE, new Vector3(0D, 0D, 0D), true);
                break;
        }

        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
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
        return MapWrapper.handlePerspective(this, TransformUtils.DEFAULT_ITEM.getTransforms(), cameraTransformType);
    }

}
