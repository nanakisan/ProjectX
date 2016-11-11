package teammdfive.projectx.common.util;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.resources.I18n;

public class LanguageHelper {

    public static final String PRESS_KEY = I18n.format("misc.tooltip.press_key.name");
    public static final String SHOW_INFO = I18n.format("misc.tooltip.show_info.name");
    public static final String KEY_SHIFT = ChatFormatting.YELLOW + I18n.format("misc.key.shift.name");
    public static final String KEY_CTRL = ChatFormatting.GREEN + I18n.format("misc.key.ctrl.name");
    public static final String KEY_ALT = ChatFormatting.RED + I18n.format("misc.key.alt.name");

    public static final String[] COLORS = new String[]{
            I18n.format("misc.color.blue"),
            I18n.format("misc.color.green"),
            I18n.format("misc.color.red"),
            I18n.format("misc.color.dark"),
            I18n.format("misc.color.light")
    };

    public static final String[] MC_COLORS = new String[]{
            I18n.format("misc.color.black"),
            I18n.format("misc.color.red"),
            I18n.format("misc.color.green"),
            I18n.format("misc.color.brown"),
            I18n.format("misc.color.blue"),
            I18n.format("misc.color.purple"),
            I18n.format("misc.color.cyan"),
            I18n.format("misc.color.light_gray"),
            I18n.format("misc.color.gray"),
            I18n.format("misc.color.pink"),
            I18n.format("misc.color.lime"),
            I18n.format("misc.color.yellow"),
            I18n.format("misc.color.light_blue"),
            I18n.format("misc.color.magenta"),
            I18n.format("misc.color.orange"),
            I18n.format("misc.color.white")
    };

}
