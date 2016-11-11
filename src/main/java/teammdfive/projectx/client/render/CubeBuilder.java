package teammdfive.projectx.client.render;

import codechicken.lib.colour.ColourRGBA;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.vecmath.Vector4d;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

/**
 * Slightly modified version of AE2's CubeBuilder class.
 * All rights go to the AE2 Team and all people that are
 * ascociated with the project.
 */
@SideOnly(Side.CLIENT)
public class CubeBuilder {

    private VertexFormat format;
    private final List<BakedQuad> output;
    private final EnumMap<EnumFacing, TextureAtlasSprite> textures = Maps.newEnumMap(EnumFacing.class);
    private EnumSet<EnumFacing> drawFaces = EnumSet.allOf(EnumFacing.class);
    private final EnumMap<EnumFacing, Vector4d> customUv = Maps.newEnumMap(EnumFacing.class);
    private byte[] uvRotations = new byte[EnumFacing.values().length];
    private int color = new ColourRGBA(255, 255, 255, 255).argb();
    private boolean renderFullBright = false;

    public CubeBuilder(VertexFormat format, List<BakedQuad> output){
        this.output = output;
        this.format = format;
    }

    public CubeBuilder(VertexFormat format){
        this(format, new ArrayList<BakedQuad>(6));
    }

    /**
     * Resets all attributes of the current call.
     * Affects lightmap and color.
     */
    public void reset(){
        this.renderFullBright = false;
        this.color = new ColourRGBA(255, 255, 255, 255).argb();
    }

    /**
     * Adds a cube to the builder using the min and max values
     * representing x, y and z for the min and x, y and z for the max.
     * (Always call this last !)
     * @param x1
     * @param y1
     * @param z1
     * @param x2
     * @param y2
     * @param z2
     */
    public void addCube(float x1, float y1, float z1, float x2, float y2, float z2){
        x1 /= 16.0f;
        y1 /= 16.0f;
        z1 /= 16.0f;
        x2 /= 16.0f;
        y2 /= 16.0f;
        z2 /= 16.0f;
        VertexFormat savedFormat = null;

        if(renderFullBright){
            savedFormat = format;
            format = VertexFormats.getFormatWithLightMap(format);
        }

        for(EnumFacing face : drawFaces){
            putFace(face, x1, y1, z1, x2, y2, z2);
        }

        if(savedFormat != null){
            format = savedFormat;
        }
    }

    /**
     * Adds a quad to the builder. Similiar to addCube instead
     * this requires an EnumFacing for the desired face that should
     * be rendered as an argument.
     * @param face
     * @param x1
     * @param y1
     * @param z1
     * @param x2
     * @param y2
     * @param z2
     */
    public void addQuad(EnumFacing face, float x1, float y1, float z1, float x2, float y2, float z2){
        VertexFormat savedFormat = null;

        if(renderFullBright){
            savedFormat = format;
            format = new VertexFormat(savedFormat);

            if(!format.getElements().contains(DefaultVertexFormats.TEX_2S)){
                format.addElement(DefaultVertexFormats.TEX_2S);
            }
        }

        putFace(face, x1, y1, z1, x2, y2, z2);

        if(savedFormat != null){
            format = savedFormat;
        }
    }

    private static final class UVVector{
        float u1;
        float u2;
        float v1;
        float v2;
    }

    private void putFace(EnumFacing face, float x1, float y1, float z1, float x2, float y2, float z2){
        TextureAtlasSprite texture = textures.get( face );
        UnpackedBakedQuad.Builder builder = new UnpackedBakedQuad.Builder(format);
        builder.setTexture(texture);
        builder.setQuadOrientation(face);
        UVVector uv = new UVVector();
        Vector4d customUv = this.customUv.get(face);

        if(customUv != null){
            uv.u1 = texture.getInterpolatedU(customUv.x);
            uv.v1 = texture.getInterpolatedV(customUv.y);
            uv.u2 = texture.getInterpolatedU(customUv.z);
            uv.v2 = texture.getInterpolatedV(customUv.w);
        }
        else{
            uv = getDefaultUv( face, texture, x1, y1, z1, x2, y2, z2 );
        }

        switch(face){
            case DOWN:
                putVertexTR( builder, face, x2, y1, z1, uv );
                putVertexBR( builder, face, x2, y1, z2, uv );
                putVertexBL( builder, face, x1, y1, z2, uv );
                putVertexTL( builder, face, x1, y1, z1, uv );
                break;
            case UP:
                putVertexTL( builder, face, x1, y2, z1, uv );
                putVertexBL( builder, face, x1, y2, z2, uv );
                putVertexBR( builder, face, x2, y2, z2, uv );
                putVertexTR( builder, face, x2, y2, z1, uv );
                break;
            case NORTH:
                putVertexBR( builder, face, x2, y2, z1, uv );
                putVertexTR( builder, face, x2, y1, z1, uv );
                putVertexTL( builder, face, x1, y1, z1, uv );
                putVertexBL( builder, face, x1, y2, z1, uv );
                break;
            case SOUTH:
                putVertexBL( builder, face, x1, y2, z2, uv );
                putVertexTL( builder, face, x1, y1, z2, uv );
                putVertexTR( builder, face, x2, y1, z2, uv );
                putVertexBR( builder, face, x2, y2, z2, uv );
                break;
            case WEST:
                putVertexTL( builder, face, x1, y1, z1, uv );
                putVertexTR( builder, face, x1, y1, z2, uv );
                putVertexBR( builder, face, x1, y2, z2, uv );
                putVertexBL( builder, face, x1, y2, z1, uv );
                break;
            case EAST:
                putVertexBR( builder, face, x2, y2, z1, uv );
                putVertexBL( builder, face, x2, y2, z2, uv );
                putVertexTL( builder, face, x2, y1, z2, uv );
                putVertexTR( builder, face, x2, y1, z1, uv );
                break;
        }

        int[] vertexData = builder.build().getVertexData();
        output.add( new BakedQuad( vertexData, -1, face, texture, true, format ) );
    }

    private UVVector getDefaultUv(EnumFacing face, TextureAtlasSprite texture, float x1, float y1, float z1, float x2, float y2, float z2){
        UVVector uv = new UVVector();

        switch(face){
            case DOWN:
                uv.u1 = texture.getInterpolatedU( x1 * 16 );
                uv.v1 = texture.getInterpolatedV( z1 * 16 );
                uv.u2 = texture.getInterpolatedU( x2 * 16 );
                uv.v2 = texture.getInterpolatedV( z2 * 16 );
                break;
            case UP:
                uv.u1 = texture.getInterpolatedU( x1 * 16 );
                uv.v1 = texture.getInterpolatedV( z1 * 16 );
                uv.u2 = texture.getInterpolatedU( x2 * 16 );
                uv.v2 = texture.getInterpolatedV( z2 * 16 );
                break;
            case NORTH:
                uv.u1 = texture.getInterpolatedU( x1 * 16 );
                uv.v1 = texture.getInterpolatedV( 16 - y1 * 16 );
                uv.u2 = texture.getInterpolatedU( x2 * 16 );
                uv.v2 = texture.getInterpolatedV( 16 - y2 * 16 );
                break;
            case SOUTH:
                uv.u1 = texture.getInterpolatedU( x1 * 16 );
                uv.v1 = texture.getInterpolatedV( 16 - y1 * 16 );
                uv.u2 = texture.getInterpolatedU( x2 * 16 );
                uv.v2 = texture.getInterpolatedV( 16 - y2 * 16 );
                break;
            case WEST:
                uv.u1 = texture.getInterpolatedU( z1 * 16 );
                uv.v1 = texture.getInterpolatedV( 16 - y1 * 16 );
                uv.u2 = texture.getInterpolatedU( z2 * 16 );
                uv.v2 = texture.getInterpolatedV( 16 - y2 * 16 );
                break;
            case EAST:
                uv.u1 = texture.getInterpolatedU( z2 * 16 );
                uv.v1 = texture.getInterpolatedV( 16 - y1 * 16 );
                uv.u2 = texture.getInterpolatedU( z1 * 16 );
                uv.v2 = texture.getInterpolatedV( 16 - y2 * 16 );
                break;
        }

        return uv;
    }

    private void putVertexTL(UnpackedBakedQuad.Builder builder, EnumFacing face, float x, float y, float z, UVVector uv) {
        float u, v;

        switch( uvRotations[face.ordinal()]){
            default:
            case 0:
                u = uv.u1;
                v = uv.v1;
                break;
            case 1:
                u = uv.u1;
                v = uv.v2;
                break;
            case 2:
                u = uv.u2;
                v = uv.v2;
                break;
            case 3:
                u = uv.u2;
                v = uv.v1;
                break;
        }

        putVertex( builder, face, x, y, z, u, v );
    }

    private void putVertexTR(UnpackedBakedQuad.Builder builder, EnumFacing face, float x, float y, float z, UVVector uv){
        float u;
        float v;

        switch(uvRotations[face.ordinal()]){
            default:
            case 0:
                u = uv.u2;
                v = uv.v1;
                break;
            case 1:
                u = uv.u1;
                v = uv.v1;
                break;
            case 2:
                u = uv.u1;
                v = uv.v2;
                break;
            case 3:
                u = uv.u2;
                v = uv.v2;
                break;
        }

        putVertex( builder, face, x, y, z, u, v );
    }

    private void putVertexBR(UnpackedBakedQuad.Builder builder, EnumFacing face, float x, float y, float z, UVVector uv){
        float u;
        float v;

        switch(uvRotations[face.ordinal()]){
            default:
            case 0:
                u = uv.u2;
                v = uv.v2;
                break;
            case 1:
                u = uv.u2;
                v = uv.v1;
                break;
            case 2:
                u = uv.u1;
                v = uv.v1;
                break;
            case 3:
                u = uv.u1;
                v = uv.v2;
                break;
        }

        putVertex( builder, face, x, y, z, u, v );
    }

    private void putVertexBL(UnpackedBakedQuad.Builder builder, EnumFacing face, float x, float y, float z, UVVector uv){
        float u;
        float v;

        switch(uvRotations[face.ordinal()]) {
            default:
            case 0:
                u = uv.u1;
                v = uv.v2;
                break;
            case 1:
                u = uv.u2;
                v = uv.v2;
                break;
            case 2:
                u = uv.u2;
                v = uv.v1;
                break;
            case 3:
                u = uv.u1;
                v = uv.v1;
                break;
        }

        putVertex( builder, face, x, y, z, u, v );
    }

    private void putVertex(UnpackedBakedQuad.Builder builder, EnumFacing face, float x, float y, float z, float u, float v){
        VertexFormat format = builder.getVertexFormat();

        for(int i = 0; i < format.getElementCount(); i++){
            VertexFormatElement e = format.getElement(i);

            switch(e.getUsage()){
                case POSITION:
                    builder.put(i, x, y, z);
                    break;
                case NORMAL:
                    builder.put(i, face.getFrontOffsetX(), face.getFrontOffsetY(), face.getFrontOffsetZ());
                    break;
                case COLOR:
                    float r = (color >> 16 & 0xFF) / 255f;
                    float g = (color >> 8 & 0xFF) / 255f;
                    float b = (color & 0xFF) / 255f;
                    float a = (color >> 24 & 0xFF) / 255f;
                    builder.put(i, r, g, b, a);
                    break;
                case UV:
                    if(e.getIndex() == 0){
                        builder.put(i, u, v);
                    }
                    else{
                        final float lightMapU = (float)(15 * 0x20) / 0xFFFF;
                        final float lightMapV = (float)(15 * 0x20) / 0xFFFF;
                        builder.put(i, lightMapU, lightMapV);
                    }

                    break;
                default:
                    builder.put(i);
                    break;
            }
        }
    }

    /**
     * Sets the texture for all block faces.
     * @param texture
     */
    public void setTexture(TextureAtlasSprite texture){
        for(EnumFacing face : EnumFacing.values()) {
            textures.put(face, texture);
        }
    }

    /**
     * Sets the texture for all block faces seperately.
     * @param up
     * @param down
     * @param north
     * @param south
     * @param east
     * @param west
     */
    public void setTextures(TextureAtlasSprite up, TextureAtlasSprite down, TextureAtlasSprite north, TextureAtlasSprite south, TextureAtlasSprite east, TextureAtlasSprite west){
        textures.put(EnumFacing.UP, up);
        textures.put(EnumFacing.DOWN, down);
        textures.put(EnumFacing.NORTH, north);
        textures.put(EnumFacing.SOUTH, south);
        textures.put(EnumFacing.EAST, east);
        textures.put(EnumFacing.WEST, west);
    }

    /**
     * Sets the texture for given EnumFacing of the block.
     * @param facing
     * @param sprite
     */
    public void setTexture(EnumFacing facing, TextureAtlasSprite sprite){
        textures.put( facing, sprite );
    }

    /**
     * Sets wich faces are gonna be rendered using an EnumSet
     * containing all faces that should be drawn.
     * @param drawFaces
     */
    public void setDrawFaces(EnumSet<EnumFacing> drawFaces){
        this.drawFaces = drawFaces;
    }

    /**
     * Sets the color of the current call using a
     * packed int.
     * (Format: ARGB)
     * @param color
     */
    public void setColor(int color){
        this.color = color;
    }

    /**
     * Sets the color of the current call using a
     * packet int.
     * (Format: RGB)
     * @param color
     */
    public void setColorRGB(int color){
        setColor(color | 0xFF000000);
    }

    /**
     * Sets the color of the current call using a
     * ColourRGBA.
     * @param color
     */
    public void setColor(ColourRGBA color){
        this.color = color.argb();
    }

    /**
     * Sets the color of the current call using three float values
     * for red, green and blue.
     * @param r
     * @param g
     * @param b
     */
    public void setColorRGB(float r, float g, float b){
        setColorRGB((int)(r * 255) << 16 | (int)(g * 255) << 8 | (int)(b * 255));
    }

    /**
     * Sets the lightmap of the current call to a modified fullbright
     * lightmap making it glow without emitting light.
     * (Not OptiFine compatible !)
     * @param renderFullBright
     */
    public void setRenderFullBright(boolean renderFullBright){
        this.renderFullBright = renderFullBright;
    }

    /**
     * Sets custom UV coordinates for the given face.
     * @param facing
     * @param u1
     * @param v1
     * @param u2
     * @param v2
     */
    public void setCustomUv( EnumFacing facing, float u1, float v1, float u2, float v2 ){
        customUv.put(facing, new Vector4d(u1, v1, u2, v2));
    }

    /**
     * Sets the UV rotation for the given face.
     * @param facing
     * @param rotation
     */
    public void setUvRotation(EnumFacing facing, int rotation) {
        if(rotation == 2) {
            rotation = 3;
        }
        else if(rotation == 3){
            rotation = 2;
        }

        Preconditions.checkArgument( rotation >= 0 && rotation <= 3, "rotation" );
        uvRotations[facing.ordinal()] = (byte) rotation;
    }

    /**
     * Output of the builder in form of an ImmutableList
     * because why not.
     * @return
     */
    public ImmutableList<BakedQuad> getOutput(){ return ImmutableList.copyOf(this.output); }

}
