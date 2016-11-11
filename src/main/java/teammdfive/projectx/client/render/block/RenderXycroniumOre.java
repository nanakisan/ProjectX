package teammdfive.projectx.client.render.block;

import codechicken.lib.vec.Cuboid6;
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
import teammdfive.projectx.common.property.CommonProperties;
import teammdfive.projectx.common.property.EnumColorType;
import teammdfive.projectx.common.util.ModPrefs;

import java.util.Collection;

@SideOnly(Side.CLIENT)
public class RenderXycroniumOre implements IRenderingHandler {

    @Override
    public Collection<ResourceLocation> getTextures() {
        return Lists.newArrayList(
                new ResourceLocation(ModPrefs.MODID, "blocks/ore/xycronium_ore_effect"),
                new ResourceLocation(ModPrefs.MODID, "blocks/ore/xycronium_ore_blue"),
                new ResourceLocation(ModPrefs.MODID, "blocks/ore/xycronium_ore_green"),
                new ResourceLocation(ModPrefs.MODID, "blocks/ore/xycronium_ore_red"),
                new ResourceLocation(ModPrefs.MODID, "blocks/ore/xycronium_ore_dark"),
                new ResourceLocation(ModPrefs.MODID, "blocks/ore/xycronium_ore_light")
        );
    }

    @Override
    public void render(ModelBuilder builder, TextureGetter textureGetter, BlockRenderLayer layer, IBlockState state, EnumFacing face, long random) {
        EnumColorType type = (EnumColorType)state.getValue(CommonProperties.COLOR);
        builder.setTexture(textureGetter.getTexture("ore/xycronium_ore_effect")).setFullbright(true).setColor(ModPrefs.xyColors[type.getID()]).addCuboid(new Cuboid6(0D, 0D, 0D, 16D, 16D, 16D));
        builder.setTexture(textureGetter.getTexture("ore/xycronium_ore_" + type.getName())).addCuboid(new Cuboid6(0D, 0D, 0D, 16D, 16D, 16D));
    }

    @Override
    public void renderInventory(ModelBuilder builder, TextureGetter textureGetter, ItemStack stack, long random){
        builder.setTexture(textureGetter.getTexture("ore/xycronium_ore_effect")).setColor(ModPrefs.xyColors[stack.getMetadata()]).addCuboid(new Cuboid6(0D, 0D, 0D, 16D, 16D, 16D));
        builder.setTexture(textureGetter.getTexture("ore/xycronium_ore_" + EnumColorType.values()[stack.getMetadata()].getName())).addCuboid(new Cuboid6(0D, 0D, 0D, 16D, 16D, 16D));
    }

    @Override
    public TextureAtlasSprite getParticleTexture(TextureGetter textureGetter) {
        return textureGetter.getTexture("minecraft", "blocks/stone");
    }

}
