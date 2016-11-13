package teammdfive.projectx.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import teammdfive.projectx.api.energy.EnumXynergyClass;
import teammdfive.projectx.api.energy.EnumXynergyType;
import teammdfive.projectx.common.util.ItemHelper;

public class TileXynergyNode extends TileBase {

    private int coreType = 0;
    private boolean hasCore = false;

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.coreType = tag.getInteger("core_type");
        this.hasCore = tag.getBoolean("has_core");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        tag.setInteger("core_type", this.coreType);
        tag.setBoolean("has_core", this.hasCore);
        return super.writeToNBT(tag);
    }

    public void setCoreType(int coreType){ this.coreType = coreType; }

    public void setHasCore(boolean hasCore){ this.hasCore = hasCore; }

    public int getCoreType(){ return this.coreType; }

    public boolean getHasCore(){ return this.hasCore; }

    public EnumXynergyClass getXynergyClass(){
        return ItemHelper.getClassByMeta(this.coreType);
    }

    public EnumXynergyType getXynergyType(){
        return ItemHelper.getTypeByMeta(this.coreType);
    }

}
