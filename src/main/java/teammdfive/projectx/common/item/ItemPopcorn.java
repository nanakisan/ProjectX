package teammdfive.projectx.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teammdfive.projectx.client.ProjectXTab;
import teammdfive.projectx.common.util.ModPrefs;

public class ItemPopcorn extends ItemFood {

    private final String itemName = "popcorn";

    public ItemPopcorn() {
        super(4, 2.8F, false);
        this.setCreativeTab(ProjectXTab.tabProjectX);
        this.setUnlocalizedName(ModPrefs.MODID + "." + this.itemName);
        this.setRegistryName(ModPrefs.MODID, this.itemName);
        GameRegistry.register(this);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        for(PotionEffect effect : PotionTypes.SWIFTNESS.getEffects()){
            player.addPotionEffect(effect);
        }
    }

}
