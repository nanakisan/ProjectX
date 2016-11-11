package teammdfive.projectx.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teammdfive.projectx.common.property.CommonProperties;
import teammdfive.projectx.common.property.EnumMCColorType;
import teammdfive.projectx.common.util.ClientHelper;
import teammdfive.projectx.common.util.IMetaBlock;

public class BlockXycroniumStructure extends BlockBase implements IMetaBlock {

    public BlockXycroniumStructure() {
        super("xycronium_structure", Material.ROCK);
        this.setHardness(1.4F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(CommonProperties.MC_COLOR, EnumMCColorType.BLACK));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{CommonProperties.MC_COLOR});
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(CommonProperties.MC_COLOR, EnumMCColorType.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        EnumMCColorType color = (EnumMCColorType)state.getValue(CommonProperties.MC_COLOR);
        return color.getID();
    }

    @Override
    public String[] getSubNames() {
        String[] subNames = new String[EnumMCColorType.values().length];

        for(int i = 0; i < subNames.length; i++){
            subNames[i] = EnumMCColorType.values()[i].getName();
        }

        return subNames;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("deprecation")
    public boolean canRenderInLayer(BlockRenderLayer layer) {
        return ClientHelper.canRenderInLayer(layer);
    }

}
