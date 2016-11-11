package teammdfive.projectx.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
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

}
