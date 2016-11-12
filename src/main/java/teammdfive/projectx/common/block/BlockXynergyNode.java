package teammdfive.projectx.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import teammdfive.projectx.common.property.CommonProperties;
import teammdfive.projectx.common.property.NodeRenderState;
import teammdfive.projectx.common.tile.TileXynergyNode;

public class BlockXynergyNode extends BlockBase {

    public BlockXynergyNode() {
        super("xynergy_node", Material.IRON);
        this.setHardness(1.3F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileXynergyNode();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new ExtendedBlockState(this, new IProperty[0], new IUnlistedProperty[]{CommonProperties.NODE_RENDER_STATE});
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos){
        NodeRenderState renderState = new NodeRenderState();
        return ((IExtendedBlockState)state).withProperty(CommonProperties.NODE_RENDER_STATE, renderState);
    }

}
