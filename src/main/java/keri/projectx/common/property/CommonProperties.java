package keri.projectx.common.property;

import net.minecraft.block.properties.PropertyEnum;

public class CommonProperties {

    public static final PropertyEnum<EnumColorType> COLOR = PropertyEnum.create("color", EnumColorType.class);
    public static final PropertyEnum<EnumMCColorType> MC_COLOR = PropertyEnum.create("color", EnumMCColorType.class);
    public static final PropertyEnum<EnumGrowthStage> GROWTH_STAGE = PropertyEnum.create("stage", EnumGrowthStage.class);
    public static final PropertyEnum<EnumTransformerMode> TRANSFORMER_MODE = PropertyEnum.create("mode", EnumTransformerMode.class);

}
