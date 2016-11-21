package keri.projectx.common.item;

import keri.projectx.common.init.PXContent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class ItemCornKernel extends ItemBase implements IPlantable {

    public ItemCornKernel(){
        super("corn_kernel");
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
        if(side != EnumFacing.UP){
            return EnumActionResult.PASS;
        }
        else if(player.canPlayerEdit(pos, side, stack) && player.canPlayerEdit(pos.add(0, 1, 0), side, stack)){
            IBlockState state = world.getBlockState(pos);

            if(state.getBlock().canSustainPlant(state, world, pos, EnumFacing.UP, this) && world.isAirBlock(pos.add(0, 1, 0))){
                world.setBlockState(pos.add(0, 1, 0), PXContent.cornCrop.getDefaultState());
                stack.stackSize--;
                return EnumActionResult.SUCCESS;
            }
            else{
                return EnumActionResult.PASS;
            }
        }
        else{
            return EnumActionResult.PASS;
        }
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return ((IPlantable)PXContent.cornCrop).getPlantType(world, pos);
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return PXContent.cornCrop.getDefaultState();
    }

}
