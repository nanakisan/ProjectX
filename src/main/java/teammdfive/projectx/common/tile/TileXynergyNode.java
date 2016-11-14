package teammdfive.projectx.common.tile;

import com.google.common.collect.Lists;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import teammdfive.projectx.api.energy.EnumXynergyClass;
import teammdfive.projectx.api.energy.EnumXynergyType;
import teammdfive.projectx.api.energy.IXynergyHandler;
import teammdfive.projectx.api.energy.XynergyStorage;
import teammdfive.projectx.common.util.ItemHelper;
import teammdfive.projectx.common.util.TileHelper;

import java.util.ArrayList;

public class TileXynergyNode extends TileBase implements IXynergyHandler, ITickable {

    private int coreType = 0;
    private boolean hasCore = false;
    private XynergyStorage internalBuffer = new XynergyStorage(128, 32);
    private boolean listChanged = false;
    private ArrayList<BlockPos> connectedDevices = Lists.newArrayList();

    @Override
    public void update(){
        if(this.listChanged){
            for(int i = 0; i < this.connectedDevices.size(); i++){
                BlockPos entry = this.connectedDevices.get(i);

                if(this.worldObj.getTileEntity(entry) == null){
                    this.connectedDevices.remove(this.connectedDevices.indexOf(entry));
                }
            }

            this.listChanged = false;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag){
        super.readFromNBT(tag);
        this.coreType = tag.getInteger("core_type");
        this.hasCore = tag.getBoolean("has_core");
        this.internalBuffer.readFromNBT(tag);
        this.listChanged = tag.getBoolean("list_changed");
        this.connectedDevices = TileHelper.readCoordList(tag);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag){
        tag.setInteger("core_type", this.coreType);
        tag.setBoolean("has_core", this.hasCore);
        this.internalBuffer.writeToNBT(tag);
        tag.setBoolean("list_changed", this.listChanged);
        TileHelper.writeCoordList(this.connectedDevices, tag);
        return super.writeToNBT(tag);
    }

    @Override
    public boolean canAcceptXynergy(){
        return true;
    }

    @Override
    public boolean canProvideXynergy(){
        return true;
    }

    @Override
    public boolean canConnectXynergy(){
        return true;
    }

    @Override
    public XynergyStorage getInternalStorage(){
        return this.internalBuffer;
    }

    public void setCoreType(int coreType){ this.coreType = coreType; }

    public void setHasCore(boolean hasCore){ this.hasCore = hasCore; }

    public void addConnection(BlockPos location){
        if(location != null){
            this.connectedDevices.add(location);
        }
    }

    public void removeConnection(BlockPos location){
        if(location != null){
            if(this.connectedDevices.contains(location)){
                this.connectedDevices.remove(this.connectedDevices.indexOf(location));
            }
        }
    }

    public void updateDeviceList(){ this.listChanged = true; }

    public ArrayList<BlockPos> getConnectedDevices(){ return this.connectedDevices; }

    public int getCoreType(){ return this.coreType; }

    public boolean getHasCore(){ return this.hasCore; }

    public EnumXynergyClass getXynergyClass(){
        return ItemHelper.getClassByMeta(this.coreType);
    }

    public EnumXynergyType getXynergyType(){
        return ItemHelper.getTypeByMeta(this.coreType);
    }

}
