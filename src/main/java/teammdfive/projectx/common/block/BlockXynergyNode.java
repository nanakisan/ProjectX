package teammdfive.projectx.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockXynergyNode extends BlockBase {

    public BlockXynergyNode(String blockName, Material material) {
        super(blockName, material);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return super.createNewTileEntity(world, meta);
    }

}
