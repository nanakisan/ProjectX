package keri.projectx.client;

import keri.projectx.common.init.PXContent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ProjectXTab extends CreativeTabs {

    public static final ProjectXTab tabProjectX = new ProjectXTab();

    public ProjectXTab() {
        super("projectx.name");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return Item.getItemFromBlock(PXContent.xycroniumBricks);
    }

}
