package keri.projectx.common.item;

import keri.projectx.api.tool.IDebuggerAction;
import keri.projectx.api.tool.IToolAction;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemDebugger extends ItemBase {

    public ItemDebugger() {
        super("debugger");
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(world.getBlockState(pos).getBlock() instanceof IToolAction){
            IDebuggerAction block = (IDebuggerAction)world.getBlockState(pos).getBlock();
            return block.onDebuggerUse(stack, player, world, pos, hand, facing, hitX, hitY, hitZ);
        }

        return EnumActionResult.FAIL;
    }

}
