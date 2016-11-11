package teammdfive.projectx.client.render;

import com.google.common.base.Function;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teammdfive.projectx.common.util.ModPrefs;

@SideOnly(Side.CLIENT)
public class TextureGetter {

    private Function<ResourceLocation, TextureAtlasSprite> textureGetter;

    public TextureGetter(Function<ResourceLocation, TextureAtlasSprite> textureGetter){
        this.textureGetter = textureGetter;
    }

    public TextureAtlasSprite getTexture(String path){
        ResourceLocation location = new ResourceLocation(ModPrefs.MODID, "blocks/" + path);
        return this.textureGetter.apply(location);
    }

    public TextureAtlasSprite getTexture(String domain, String path){
        ResourceLocation location = new ResourceLocation(domain, path);
        return this.textureGetter.apply(location);
    }

    public TextureAtlasSprite getTexture(ResourceLocation location){
        return this.textureGetter.apply(location);
    }

}
