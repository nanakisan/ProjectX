package keri.projectx.common.item;

import keri.projectx.client.ProjectXTab;
import keri.projectx.common.util.ModPrefs;
import net.minecraft.item.ItemFood;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemCobOCorn extends ItemFood {

    private final String itemName = "cob_o_corn";

    public ItemCobOCorn() {
        super(4, 3.2F, false);
        this.setCreativeTab(ProjectXTab.tabProjectX);
        this.setUnlocalizedName(ModPrefs.MODID + "." + this.itemName);
        this.setRegistryName(ModPrefs.MODID, this.itemName);
        GameRegistry.register(this);
    }

}
