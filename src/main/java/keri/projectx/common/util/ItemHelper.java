package keri.projectx.common.util;

import keri.projectx.api.energy.EnumXynergyClass;
import keri.projectx.api.energy.EnumXynergyType;

public class ItemHelper {

    public static EnumXynergyClass getClassByMeta(int meta){
        switch(meta){
            case 0: return EnumXynergyClass.LOW;
            case 1: return EnumXynergyClass.MIDDLE;
            case 2: return EnumXynergyClass.OMNIPOTENT;
            case 3: return EnumXynergyClass.LOW;
            case 4: return EnumXynergyClass.MIDDLE;
            case 5: return EnumXynergyClass.OMNIPOTENT;
            case 6: return EnumXynergyClass.LOW;
            case 7: return EnumXynergyClass.MIDDLE;
            case 8: return EnumXynergyClass.OMNIPOTENT;
        }

        return null;
    }

    public static EnumXynergyType getTypeByMeta(int meta){
        switch(meta){
            case 0: return EnumXynergyType.NORMAL;
            case 1: return EnumXynergyType.NORMAL;
            case 2: return EnumXynergyType.NORMAL;
            case 3: return EnumXynergyType.RADIAL;
            case 4: return EnumXynergyType.RADIAL;
            case 5: return EnumXynergyType.RADIAL;
            case 6: return EnumXynergyType.LONGDISTANCE;
            case 7: return EnumXynergyType.LONGDISTANCE;
            case 8: return EnumXynergyType.LONGDISTANCE;
        }

        return null;
    }

}
