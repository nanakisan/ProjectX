package keri.projectx.common.block;

import keri.projectx.common.property.CommonProperties;
import keri.projectx.common.property.EnumMCColorType;
import keri.projectx.common.util.ClientHelper;
import keri.projectx.common.util.IMetaBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockXycroniumPlatform extends BlockBase implements IMetaBlock {

    public BlockXycroniumPlatform() {
        super("xycronium_platform", Material.ROCK);
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
    public boolean canRenderInLayer(BlockRenderLayer layer) {
        return ClientHelper.canRenderInLayer(layer);
    }

}
