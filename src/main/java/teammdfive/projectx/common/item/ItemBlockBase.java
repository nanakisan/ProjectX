package teammdfive.projectx.common.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teammdfive.projectx.common.util.IMetaBlock;
import teammdfive.projectx.common.util.IShiftDescription;
import teammdfive.projectx.common.util.InputHelper;
import teammdfive.projectx.common.util.LanguageHelper;

import java.util.List;

public class ItemBlockBase extends ItemBlock {

    public ItemBlockBase(Block block) {
        super(block);

        if(block instanceof IMetaBlock){
            this.setHasSubtypes(true);
        }
    }

    @Override
    public int getMetadata(int damage) {
        if(this.block instanceof IMetaBlock){
            return damage;
        }
        else{
            return 0;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        if(this.block instanceof IMetaBlock){
            this.block.getSubBlocks(item, tab, subItems);
        }
        else{
            subItems.add(new ItemStack(item, 1, 0));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getUnlocalizedName(ItemStack stack) {
        if(this.block instanceof IMetaBlock){
            IMetaBlock iface = (IMetaBlock)this.block;
            return this.getUnlocalizedName() + "." + iface.getSubNames()[stack.getMetadata()];
        }
        else{
            return this.getUnlocalizedName();
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        if(this.block instanceof IShiftDescription){
            IShiftDescription iface = (IShiftDescription)this.block;

            if(InputHelper.isShiftPressed()){
                iface.addDescription(stack, player, tooltip);
            }
            else{
                tooltip.add(LanguageHelper.PRESS_KEY + " " + LanguageHelper.KEY_SHIFT + " " + LanguageHelper.KEY_SHIFT);
            }
        }
    }

}
