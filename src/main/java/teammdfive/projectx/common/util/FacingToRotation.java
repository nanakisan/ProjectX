package teammdfive.projectx.common.util;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.model.TRSRTransformation;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;

public enum FacingToRotation {

    DOWN_DOWN	( new Vector3f(	0,		0,		0	) ),
    DOWN_UP		( new Vector3f(	0,		0,		0	) ),
    DOWN_NORTH	( new Vector3f(	-90,	0,		0	) ),
    DOWN_SOUTH	( new Vector3f(	-90,	0,		180	) ),
    DOWN_WEST	( new Vector3f(	-90,	0,		90	) ),
    DOWN_EAST	( new Vector3f(	-90,	0,		-90	) ),
    UP_DOWN		( new Vector3f(	0,		0,		0	) ),
    UP_UP		( new Vector3f(	0,		0,		0	) ),
    UP_NORTH	( new Vector3f(	90,		0,		180	) ),
    UP_SOUTH	( new Vector3f(	90,		0,		0	) ),
    UP_WEST		( new Vector3f(	90,		0,		90	) ),
    UP_EAST		( new Vector3f(	90,		0,		-90	) ),
    NORTH_DOWN	( new Vector3f(	0,		0,		180	) ),
    NORTH_UP	( new Vector3f(	0,		0,		0	) ),
    NORTH_NORTH	( new Vector3f(	0,		0,		0	) ),
    NORTH_SOUTH	( new Vector3f(	0,		0,		0	) ),
    NORTH_WEST	( new Vector3f(	0,		0,		90	) ),
    NORTH_EAST	( new Vector3f(	0,		0,		-90	) ),
    SOUTH_DOWN	( new Vector3f(	0,		180,	180	) ),
    SOUTH_UP	( new Vector3f(	0,		180,	0	) ),
    SOUTH_NORTH	( new Vector3f(	0,		0,		0	) ),
    SOUTH_SOUTH	( new Vector3f(	0,		0,		0	) ),
    SOUTH_WEST	( new Vector3f(	0,		180,	-90	) ),
    SOUTH_EAST	( new Vector3f(	0,		180,	90	) ),
    WEST_DOWN	( new Vector3f(	0,		90,		180	) ),
    WEST_UP		( new Vector3f(	0,		90,		0	) ),
    WEST_NORTH	( new Vector3f(	0,		90,		-90	) ),
    WEST_SOUTH	( new Vector3f(	0,		90,		90	) ),
    WEST_WEST	( new Vector3f(	0,		0,		0	) ),
    WEST_EAST	( new Vector3f(	0,		0,		0	) ),
    EAST_DOWN	( new Vector3f(	0,		-90,	180	) ),
    EAST_UP		( new Vector3f(	0,		-90,	0	) ),
    EAST_NORTH	( new Vector3f(	0,		-90,	90	) ),
    EAST_SOUTH	( new Vector3f(	0,		-90,	-90	) ),
    EAST_WEST	( new Vector3f(	0,		0,		0	) ),
    EAST_EAST	( new Vector3f(	0,		0,		0	) );

    private final Vector3f rot;
    private final Matrix4f mat;

    private FacingToRotation(Vector3f rot){
        this.rot = rot;
        this.mat = TRSRTransformation.toVecmath(new org.lwjgl.util.vector.Matrix4f().rotate((float)Math.toRadians(rot.x), new org.lwjgl.util.vector.Vector3f(1, 0, 0)).rotate((float)Math.toRadians(rot.y), new org.lwjgl.util.vector.Vector3f(0, 1, 0)).rotate((float)Math.toRadians(rot.z), new org.lwjgl.util.vector.Vector3f(0, 0, 1)));
    }

    public Vector3f getRot(){
        return rot;
    }

    public Matrix4f getMat(){
        return new Matrix4f(this.mat);
    }

    public void glRotateCurrentMat(){
        GlStateManager.rotate(rot.x, 1, 0, 0);
        GlStateManager.rotate(rot.y, 0, 1, 0);
        GlStateManager.rotate(rot.z, 0, 0, 1);
    }

    public EnumFacing rotate(EnumFacing facing){
        return TRSRTransformation.rotate(mat, facing);
    }

    public EnumFacing resultingRotate(EnumFacing facing){
        for(EnumFacing face : EnumFacing.values()){
            if(rotate(face) == facing){
                return face;
            }
        }

        return null;
    }

    public static FacingToRotation get(EnumFacing forward, EnumFacing up){
        return values()[forward.ordinal() * 6 + up.ordinal()];
    }

}
