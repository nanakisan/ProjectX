package keri.projectx.common.item;

import keri.projectx.client.ProjectXTab;
import keri.projectx.common.util.ModPrefs;
import net.minecraft.item.ItemFood;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemCorn extends ItemFood {

    private final String itemName = "corn";

    public ItemCorn() {
        super(2, 2F, false);
        this.setCreativeTab(ProjectXTab.tabProjectX);
        this.setUnlocalizedName(ModPrefs.MODID + "." + this.itemName);
        this.setRegistryName(ModPrefs.MODID, this.itemName);
        GameRegistry.register(this);
    }

}
