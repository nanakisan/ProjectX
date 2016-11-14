package teammdfive.projectx.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teammdfive.projectx.api.tool.IToolAction;

public class ItemXynergyTool extends ItemBase {

    public ItemXynergyTool() {
        super("xynergy_tool");
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        if(world.getBlockState(pos).getBlock() instanceof IToolAction){
            IToolAction block = (IToolAction)world.getBlockState(pos).getBlock();
            return block.onToolUse(stack, player, world, pos, hand, facing, hitX, hitY, hitZ);
        }

        return EnumActionResult.FAIL;
    }

}
