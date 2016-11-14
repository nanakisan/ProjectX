package teammdfive.projectx.common.util;

import codechicken.lib.colour.ColourRGBA;

public class ModPrefs {

    public static final String MODID = "projectx";
    public static final String NAME = "ProjectX";
    public static final String VERSION = "0.1.0";
    public static final String DEPS = "";
    public static final String ACC_MC = "1.10.2";
    public static final String CSIDE = "teammdfive.projectx.client.ClientProxy";
    public static final String SSIDE = "teammdfive.projectx.common.CommonProxy";

    public static final String[] xyTypes = new String[]{
            "blue",
            "green",
            "red",
            "dark",
            "light"
    };

    public static final String[] mcTypes = new String[]{
            "black",
            "red",
            "green",
            "brown",
            "blue",
            "purple",
            "cyan",
            "light_gray",
            "gray",
            "pink",
            "lime",
            "yellow",
            "light_blue",
            "magenta",
            "orange",
            "white"
    };

    public static final ColourRGBA[] xyColors = new ColourRGBA[]{
            new ColourRGBA(0, 100, 255, 255),
            new ColourRGBA(16711935),
            new ColourRGBA(-16776961),
            new ColourRGBA(30, 30, 30, 255),
            new ColourRGBA(-1)
    };

    public static final ColourRGBA[] mcColors = new ColourRGBA[]{
            new ColourRGBA(741092607),
            new ColourRGBA(-16776961),
            new ColourRGBA(8388863),
            new ColourRGBA(110, 65, 0, 255),
            new ColourRGBA(65535),
            new ColourRGBA(150, 0, 250, 255),
            new ColourRGBA(16777215),
            new ColourRGBA(-1061109505),
            new ColourRGBA(-2139062017),
            new ColourRGBA(255, 135, 255, 255),
            new ColourRGBA(16711935),
            new ColourRGBA(-65281),
            new ColourRGBA(105, 195, 255, 255),
            new ColourRGBA(-16711681),
            new ColourRGBA(255, 130, 0, 255),
            new ColourRGBA(-1),
    };

}
