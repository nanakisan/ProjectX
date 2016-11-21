package keri.projectx.client.render;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;

@SideOnly(Side.CLIENT)
public class IconRegistry {

    private static HashMap<String, TextureAtlasSprite> icons = new HashMap<String, TextureAtlasSprite>();

    private IconRegistry(){}

    public static void addIcon(String iconName, ResourceLocation iconLocation, TextureMap ir) {
        addIcon(iconName, ir.registerSprite(iconLocation));
    }

    public static void addIcon(String iconName, String iconLocation, TextureMap ir) {
        addIcon(iconName, ir.registerSprite(new ResourceLocation(iconLocation)));
    }

    public static void addIcon(String iconName, TextureAtlasSprite icon) {
        icons.put(iconName, icon);
    }

    public static TextureAtlasSprite getIcon(String iconName) {
        return icons.get(iconName);
    }

    public static TextureAtlasSprite getIcon(String iconName, int iconOffset) {
        return icons.get(iconName + iconOffset);
    }

}
