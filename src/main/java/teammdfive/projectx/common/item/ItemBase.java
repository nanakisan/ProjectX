package teammdfive.projectx.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teammdfive.projectx.client.ProjectXTab;
import teammdfive.projectx.common.util.IShiftDescription;
import teammdfive.projectx.common.util.InputHelper;
import teammdfive.projectx.common.util.LanguageHelper;
import teammdfive.projectx.common.util.ModPrefs;

import java.util.List;

public class ItemBase extends Item {

    private String itemName;
    private String[] subNames = null;

    public ItemBase(String itemName) {
        this.itemName = itemName;
        this.setCreativeTab(ProjectXTab.tabProjectX);
        this.setUnlocalizedName(ModPrefs.MODID + "." + itemName);
        this.setRegistryName(ModPrefs.MODID, itemName);
        GameRegistry.register(this);
    }

    public ItemBase(String itemName, String... subNames) {
        this.itemName = itemName;
        this.subNames = subNames;
        this.setCreativeTab(ProjectXTab.tabProjectX);
        this.setUnlocalizedName(ModPrefs.MODID + "." + itemName);
        this.setRegistryName(ModPrefs.MODID, itemName);
        this.setHasSubtypes(true);
        GameRegistry.register(this);
    }

    @Override
    public int getMetadata(int damage) {
        if(this.subNames != null){
            return damage;
        }
        else{
            return 0;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        if(this.subNames != null){
            for(int i = 0; i < this.subNames.length; i++){
                subItems.add(new ItemStack(item, 1, i));
            }
        }
        else{
            subItems.add(new ItemStack(item, 1, 0));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getUnlocalizedName(ItemStack stack) {
        if(this.subNames != null){
            return this.getUnlocalizedName() + "." + this.subNames[stack.getMetadata()];
        }
        else{
            return this.getUnlocalizedName();
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        if(this instanceof IShiftDescription){
            IShiftDescription iface = (IShiftDescription)this;

            if(InputHelper.isShiftPressed()){
                iface.addDescription(stack, player, tooltip);
            }
            else{
                tooltip.add(LanguageHelper.PRESS_KEY + " " + LanguageHelper.KEY_SHIFT + " " + LanguageHelper.SHOW_INFO);
            }
        }
    }

    public String getInternalName(){ return this.itemName; }

    public String[] getSubNames(){ return this.subNames; }

}
