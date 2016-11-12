package teammdfive.projectx.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teammdfive.projectx.common.util.IShiftDescription;
import teammdfive.projectx.common.util.LanguageHelper;

import java.util.List;

public class ItemPowerCore extends ItemBase implements IShiftDescription {

    private static final String[] subNames = new String[]{
            "normal_low",
            "normal_middle",
            "normal_omnipotent",
            "radial_low",
            "radial_middle",
            "radial_omnipotent",
            "longrange_low",
            "longrange_middle",
            "longrange_omnipotent"
    };

    public ItemPowerCore() {
        super("power_core", subNames);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addDescription(ItemStack stack, EntityPlayer player, List<String> tooltip) {
        switch(stack.getItemDamage()){
            case 0:
                tooltip.add(LanguageHelper.XYNERGY_CLASS + " " + LanguageHelper.XC_LOW);
                tooltip.add(LanguageHelper.XYNERGY_TYPE + " " + LanguageHelper.XT_NORMAL);
                break;
            case 1:
                tooltip.add(LanguageHelper.XYNERGY_CLASS + " " + LanguageHelper.XC_MIDDLE);
                tooltip.add(LanguageHelper.XYNERGY_TYPE + " " + LanguageHelper.XT_NORMAL);
                break;
            case 2:
                tooltip.add(LanguageHelper.XYNERGY_CLASS + " " + LanguageHelper.XC_OMNIPOTENT);
                tooltip.add(LanguageHelper.XYNERGY_TYPE + " " + LanguageHelper.XT_NORMAL);
                break;
            case 3:
                tooltip.add(LanguageHelper.XYNERGY_CLASS + " " + LanguageHelper.XC_LOW);
                tooltip.add(LanguageHelper.XYNERGY_TYPE + " " + LanguageHelper.XT_RADIAL);
                break;
            case 4:
                tooltip.add(LanguageHelper.XYNERGY_CLASS + " " + LanguageHelper.XC_MIDDLE);
                tooltip.add(LanguageHelper.XYNERGY_TYPE + " " + LanguageHelper.XT_RADIAL);
                break;
            case 5:
                tooltip.add(LanguageHelper.XYNERGY_CLASS + " " + LanguageHelper.XC_OMNIPOTENT);
                tooltip.add(LanguageHelper.XYNERGY_TYPE + " " + LanguageHelper.XT_RADIAL);
                break;
            case 6:
                tooltip.add(LanguageHelper.XYNERGY_CLASS + " " + LanguageHelper.XC_LOW);
                tooltip.add(LanguageHelper.XYNERGY_TYPE + " " + LanguageHelper.XT_LONGRANGE);
                break;
            case 7:
                tooltip.add(LanguageHelper.XYNERGY_CLASS + " " + LanguageHelper.XC_MIDDLE);
                tooltip.add(LanguageHelper.XYNERGY_TYPE + " " + LanguageHelper.XT_LONGRANGE);
                break;
            case 8:
                tooltip.add(LanguageHelper.XYNERGY_CLASS + " " + LanguageHelper.XC_OMNIPOTENT);
                tooltip.add(LanguageHelper.XYNERGY_TYPE + " " + LanguageHelper.XT_LONGRANGE);
                break;
        }
    }

}
