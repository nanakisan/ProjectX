package teammdfive.projectx.api.energy;

import net.minecraft.nbt.NBTTagCompound;

public class XynergyStorage {

    private int capacity;
    private int maxIn;
    private int maxOut;
    private int stored;

    public XynergyStorage(){
        this.capacity = 1024;
        this.maxIn = 32;
        this.maxOut = 32;
        this.stored = 0;
    }

    public XynergyStorage(int capacity){
        this.capacity = capacity;
        this.maxIn = 32;
        this.maxOut = 32;
        this.stored = 0;
    }

    public XynergyStorage(int capacity, int maxIO){
        this.capacity = capacity;
        this.maxIn = maxIO;
        this.maxOut = maxIO;
        this.stored = 0;
    }

    public void setCapacity(int capacity){ this.capacity = capacity; }

    public void setMaxIn(int maxIn){ this.maxIn = maxIn; }

    public void setMaxOut(int maxOut){ this.maxOut = maxOut; }

    public void setMaxIO(int maxIO){ this.maxIn = maxIO; this.maxOut = maxIO; }

    public void setStored(int stored){ this.stored = stored; }

    public void modifyStored(int amount){ this.stored += amount; }

    public int getCapacity(){ return this.capacity; }

    public int getMaxIn(){ return this.maxIn; }

    public int getMaxOut(){ return this.maxOut; }

    public int getStored(){ return this.stored; }

    public void readFromNBT(NBTTagCompound tag){
        this.capacity = tag.getInteger("xynergy_capacity");
        this.maxIn = tag.getInteger("xynergy_max_in");
        this.maxOut = tag.getInteger("xynergy_max_out");
        this.stored = tag.getInteger("xynergy_stored");
    }

    public void writeToNBT(NBTTagCompound tag){
        tag.setInteger("xynergy_capacity", this.capacity);
        tag.setInteger("xynergy_max_in", this.maxIn);
        tag.setInteger("xynergy_max_out", this.maxOut);
        tag.setInteger("xynergy_stored", this.stored);
    }

}
