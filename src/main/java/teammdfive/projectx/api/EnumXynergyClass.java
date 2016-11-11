package teammdfive.projectx.api;

public enum EnumXynergyClass {

    LOW(1000, 100, 100),
    MIDDLE(5000, 200, 200),
    OMNIPOTENT(10000, 600, 600);

    private int capacity;
    private int maxIn;
    private int maxOut;

    EnumXynergyClass(int capacity, int maxIn, int maxOut){
        this.capacity = capacity;
        this.maxIn = maxIn;
        this.maxOut = maxOut;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public int getMaxIn(){
        return this.maxIn;
    }

    public int getMaxOut(){
        return this.maxOut;
    }


}
