package teammdfive.projectx.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teammdfive.projectx.common.init.PXContent;
import teammdfive.projectx.common.tile.TileXynergyNode;
import teammdfive.projectx.common.util.ClientHelper;

import javax.annotation.Nullable;

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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileXynergyNode tile = (TileXynergyNode)world.getTileEntity(pos);
        ItemStack currentHold = player.getHeldItemMainhand();

        if(currentHold != null){
            if(currentHold.getItem() == PXContent.powerCore){
                if(tile != null){
                    if(!tile.getHasCore()){
                        tile.setHasCore(true);
                        tile.setCoreType(currentHold.getMetadata());
                        currentHold.stackSize--;
                        return true;
                    }
                }
            }
            if(currentHold.getItem() == PXContent.xynergyTool){
                if(tile != null){
                    if(tile.getHasCore()){
                        ItemStack drop = new ItemStack(PXContent.powerCore, 1, tile.getCoreType());
                        EntityItem worldDrop = new EntityItem(world, player.posX, player.posY, player.posZ, drop);

                        if(!world.isRemote){
                            world.spawnEntityInWorld(worldDrop);
                        }

                        tile.setCoreType(0);
                        tile.setHasCore(false);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("deprecation")
    public boolean canRenderInLayer(BlockRenderLayer layer) {
        return ClientHelper.canRenderInLayer(layer);
    }

}
