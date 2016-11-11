package teammdfive.projectx.common.property;

import net.minecraft.block.properties.PropertyEnum;

public class CommonProperties {

    public static final PropertyEnum<EnumColorType> COLOR = PropertyEnum.create("color", EnumColorType.class);
    public static final PropertyEnum<EnumMCColorType> MC_COLOR = PropertyEnum.create("color", EnumMCColorType.class);
    public static final UnlistedProperty<NodeRenderState> NODE_RENDER_STATE = new UnlistedProperty<NodeRenderState>("render_state", NodeRenderState.class);

}
