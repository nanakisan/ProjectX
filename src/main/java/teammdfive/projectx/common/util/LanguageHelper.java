package teammdfive.projectx.common.util;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.resources.I18n;

public class LanguageHelper {

    public static final String PRESS_KEY = format("misc", "press_key");
    public static final String SHOW_INFO = format("misc", "show_info");
    public static final String KEY_SHIFT = ChatFormatting.YELLOW + format("misc", "shift") + ChatFormatting.GRAY;
    public static final String KEY_CTRL = ChatFormatting.GREEN + format("misc", "ctrl") + ChatFormatting.GRAY;
    public static final String KEY_ALT = ChatFormatting.RED + format("misc", "alt") + ChatFormatting.GRAY;
    public static final String XYNERGY_CLASS = format("misc", "xynergy_class");
    public static final String XYNERGY_TYPE = format("misc", "xynergy_type");
    public static final String XC_LOW = ChatFormatting.YELLOW + format("misc", "xc_low") + ChatFormatting.GRAY;
    public static final String XC_MIDDLE = ChatFormatting.GREEN + format("misc", "xc_middle") + ChatFormatting.GRAY;
    public static final String XC_OMNIPOTENT = ChatFormatting.RED + format("misc", "xc_omnipotent") + ChatFormatting.GRAY;
    public static final String XT_NORMAL = ChatFormatting.WHITE + format("misc", "xt_normal") + ChatFormatting.GRAY;
    public static final String XT_RADIAL = ChatFormatting.LIGHT_PURPLE + format("misc", "xt_radial") + ChatFormatting.GRAY;
    public static final String XT_LONGRANGE = ChatFormatting.BLUE + format("misc", "xt_longrange") + ChatFormatting.GRAY;
    public static final String CHAT_NO_CORE = format("chat", "no_power_core");
    public static final String CHAT_ENERGY_STORED = format("chat", "energy_stored");
    public static final String CHAT_POSITION = format("chat", "position");

    private static String format(String prefix, String toFormat){
        return I18n.format(prefix + "." + ModPrefs.MODID + "." + toFormat + ".name");
    }

}
