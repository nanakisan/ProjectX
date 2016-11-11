package teammdfive.projectx.common.property;

import net.minecraft.util.EnumFacing;

public class NodeRenderState {

    private boolean hasBase;
    private boolean hasExtension;
    private EnumFacing[] connections;

    public void setHasBase(boolean hasBase){ this.hasBase = hasBase; }

    public void setHasExtension(boolean hasExtension){ this.hasExtension = hasExtension; }

    public void setConnection(EnumFacing side, boolean connected){
        if(connected){
            this.connections[side.getIndex()] = EnumFacing.VALUES[side.getIndex()];
        }
        else{
            this.connections[side.getIndex()] = null;
        }
    }

    public boolean getHasBase(){ return this.hasBase; }

    public boolean hasExtension(){ return this.hasExtension; }

    public EnumFacing[] getConnections(){ return this.connections; }

}
