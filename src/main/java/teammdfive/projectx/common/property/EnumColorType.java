package teammdfive.projectx.common.property;

import net.minecraft.util.IStringSerializable;

public enum EnumColorType implements IStringSerializable {

    BLUE("blue", 0),
    GREEN("green", 1),
    RED("red", 2),
    DARK("dark", 3),
    LIGHT("light", 4);

    private String name;
    private int ID;

    EnumColorType(String name, int ID){
        this.name = name;
        this.ID = ID;
    }

    public int getID(){
        return this.ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
