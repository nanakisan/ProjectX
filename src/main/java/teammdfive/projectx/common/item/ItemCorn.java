package teammdfive.projectx.common.item;

import net.minecraft.item.ItemFood;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teammdfive.projectx.client.ProjectXTab;
import teammdfive.projectx.common.util.ModPrefs;

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
