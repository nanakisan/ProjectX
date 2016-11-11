package keri.projectx.common.block;

import keri.projectx.common.property.CommonProperties;
import keri.projectx.common.property.EnumColorType;
import keri.projectx.common.util.ClientHelper;
import keri.projectx.common.util.IMetaBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockXycroniumBricks extends BlockBase implements IMetaBlock {

    public BlockXycroniumBricks() {
        super("xycronium_bricks", Material.ROCK);
        this.setHardness(1.2F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(CommonProperties.COLOR, EnumColorType.BLUE));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{CommonProperties.COLOR});
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(CommonProperties.COLOR, EnumColorType.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        EnumColorType color = (EnumColorType)state.getValue(CommonProperties.COLOR);
        return color.getID();
    }

    @Override
    public String[] getSubNames() {
        String[] subNames = new String[EnumColorType.values().length];

        for(int i = 0; i < subNames.length; i++){
            subNames[i] = EnumColorType.values()[i].getName();
        }

        return subNames;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean canRenderInLayer(BlockRenderLayer layer) {
        return ClientHelper.canRenderInLayer(layer);
    }

}
