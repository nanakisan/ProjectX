package teammdfive.projectx.common.item;

public class ItemPowerCore extends ItemBase {

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

}
