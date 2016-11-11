package teammdfive.projectx.common.block;

import com.google.common.collect.Lists;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teammdfive.projectx.common.init.ProjectXContent;
import teammdfive.projectx.common.property.CommonProperties;
import teammdfive.projectx.common.property.EnumColorType;
import teammdfive.projectx.common.util.ClientHelper;
import teammdfive.projectx.common.util.IMetaBlock;

import java.util.List;

public class BlockXycroniumOre extends BlockBase implements IMetaBlock {

    public BlockXycroniumOre() {
        super("xycronium_ore", Material.ROCK);
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
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> drops = Lists.newArrayList();
        drops.add(new ItemStack(ProjectXContent.xycroniumCrystal, 4 + fortune, state.getBlock().getMetaFromState(state)));
        return drops;
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
    @SuppressWarnings("deprecation")
    public boolean canRenderInLayer(BlockRenderLayer layer) {
        return ClientHelper.canRenderInLayer(layer);
    }

}
