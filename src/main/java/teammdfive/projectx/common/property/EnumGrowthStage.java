package teammdfive.projectx.common.property;

import net.minecraft.util.IStringSerializable;

public enum EnumGrowthStage implements IStringSerializable {

    BOTTOM0("bottom0", 0),
    BOTTOM1("bottom1", 1),
    BOTTOM2("bottom2", 2),
    BOTTOM3("bottom3", 3),
    BOTTOM4("bottom4", 4),
    TOP0("top0", 5);

    private String name;
    private int ID;

    EnumGrowthStage(String name, int ID){
        this.name = name;
        this.ID = ID;
    }

    public int getID(){
        return this.ID;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
