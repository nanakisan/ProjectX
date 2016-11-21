package keri.projectx.client.render;

import codechicken.lib.colour.ColourRGBA;
import codechicken.lib.vec.Cuboid6;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Helper class for constructing block models using an
 * IBakedModel alongside with the CustomModelLoader.
 * Basicly a dummy class that hands over functions to
 * the internal CubeBuilder of this class and outputs
 * the result in form of a ImmutableList that contains
 * BakedQuads.
 */
@SideOnly(Side.CLIENT)
public class ModelBuilder {

    private VertexFormat format;
    private List<BakedQuad> quads;
    private CubeBuilder cubeBuilder;

    public ModelBuilder(VertexFormat format){
        this.format = format;
        this.quads = Lists.newArrayList();
        this.cubeBuilder = new CubeBuilder(format, this.quads);
    }

    /**
     * Adds a cuboid to the internal CubeBuilder.
     * Also always resets the attributes of the current call
     * after finished adding the cube.
     * (Always call last !)
     * @param bounds
     * @return
     */
    public void addCuboid(Cuboid6 bounds){
        float minX = (float)bounds.min.x;
        float minY = (float)bounds.min.y;
        float minZ = (float)bounds.min.z;
        float maxX = (float)bounds.max.x;
        float maxY = (float)bounds.max.y;
        float maxZ = (float)bounds.max.z;
        this.cubeBuilder.addCube(minX, minY, minZ, maxX, maxY, maxZ);
        this.cubeBuilder.reset();
    }

    /**
     * Sets the current texture of the cuboid thats currently
     * being added to the internal CubeBuilder.
     * @param textureName
     * @return
     */
    public ModelBuilder setTexture(TextureAtlasSprite texture){
        this.cubeBuilder.setTexture(texture);
        return this;
    }

    /**
     * Sets the lightmap of the cuboid thats currently being
     * added to the internal CubeBuilder to a fullbright one.
     * (Not compatible with OptiFine !!!)
     * @param fullbright
     * @return
     */
    public ModelBuilder setFullbright(boolean fullbright){
        this.cubeBuilder.setRenderFullBright(fullbright);
        return this;
    }

    /**
     * Sets the color of the cuboid thats currently being added
     * to the internal CubeBuilder.
     * @param color
     * @return
     */
    public ModelBuilder setColor(ColourRGBA color){
        this.cubeBuilder.setColor(color);
        return this;
    }

    /**
     * Output of the internal CubeBuilder in form of an
     * ImmutableList.
     * @return
     */
    public ImmutableList<BakedQuad> getOutput(){
        return ImmutableList.copyOf(this.quads);
    }

}
