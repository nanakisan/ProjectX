package teammdfive.projectx.common.util;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.resources.I18n;

public class LanguageHelper {

    public static final String PRESS_KEY = format("misc", "tooltip.press_key");
    public static final String SHOW_INFO = format("misc", "tooltip.show_info");
    public static final String KEY_SHIFT = ChatFormatting.YELLOW + format("misc", "key.shift") + ChatFormatting.GRAY;
    public static final String KEY_CTRL = ChatFormatting.GREEN + format("misc", "key.ctrl") + ChatFormatting.GRAY;
    public static final String KEY_ALT = ChatFormatting.RED + format("misc", "key.alt") + ChatFormatting.GRAY;
    public static final String XYNERGY_CLASS = format("misc", "tooltip.xynergy_class");
    public static final String XYNERGY_TYPE = format("misc", "tooltip.xynergy_type");
    public static final String XC_LOW = ChatFormatting.YELLOW + format("misc", "tooltip.xc_low") + ChatFormatting.GRAY;
    public static final String XC_MIDDLE = ChatFormatting.GREEN + format("misc", "tooltip.xc_middle") + ChatFormatting.GRAY;
    public static final String XC_OMNIPOTENT = ChatFormatting.RED + format("misc", "tooltip.xc_omnipotent") + ChatFormatting.GRAY;
    public static final String XT_NORMAL = ChatFormatting.WHITE + format("misc", "tooltip.xt_normal") + ChatFormatting.GRAY;
    public static final String XT_RADIAL = ChatFormatting.LIGHT_PURPLE + format("misc", "tooltip.xt_radial") + ChatFormatting.GRAY;
    public static final String XT_LONGRANGE = ChatFormatting.BLUE + format("misc", "tooltip.xt_longrange") + ChatFormatting.GRAY;

    private static String format(String prefix, String toFormat){
        return I18n.format(prefix + "." + ModPrefs.MODID + "." + toFormat + ".name");
    }

}
