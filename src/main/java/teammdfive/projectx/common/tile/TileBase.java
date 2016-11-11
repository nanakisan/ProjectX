package teammdfive.projectx.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

public abstract class TileBase extends TileEntity {

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        this.writeToNBT(compound);
        return super.writeToNBT(compound);
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound syncData = new NBTTagCompound();
        this.writeToNBT(syncData);
        return new SPacketUpdateTileEntity(this.getPos(), 2, syncData);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound syncData = new NBTTagCompound();
        this.writeToNBT(syncData);
        return syncData;
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readCustomNBT(packet.getNbtCompound());
        this.markDirty();
        this.worldObj.markBlockRangeForRenderUpdate(this.getPos(), this.getPos());
    }

    public abstract void readCustomNBT(NBTTagCompound tag);

    public abstract void writeCustomNBT(NBTTagCompound tag);

}
