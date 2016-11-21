package keri.projectx.common.block;

import keri.projectx.client.ProjectXTab;
import keri.projectx.common.item.ItemBlockBase;
import keri.projectx.common.util.IMetaBlock;
import keri.projectx.common.util.ModPrefs;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public abstract class BlockBase extends Block implements ITileEntityProvider {

    private String blockName;
    private ItemBlock itemBlock = null;

    public BlockBase(String blockName, Material material, MapColor mapColor) {
        super(material, mapColor);
        this.blockName = blockName;
        this.setUnlocalizedName(ModPrefs.MODID + "." + blockName);
        this.setCreativeTab(ProjectXTab.tabProjectX);
        this.setRegistryName(ModPrefs.MODID, blockName);
        this.adjustSound();
        GameRegistry.register(this);

        if(this.itemBlock != null){
            GameRegistry.register(this.itemBlock.setRegistryName(this.getRegistryName()));
        }
        else{
            GameRegistry.register(new ItemBlockBase(this).setRegistryName(this.getRegistryName()));
        }
    }

    public BlockBase(String blockName, Material material) {
        super(material);
        this.blockName = blockName;
        this.setUnlocalizedName(ModPrefs.MODID + "." + blockName);
        this.setCreativeTab(ProjectXTab.tabProjectX);
        this.setRegistryName(ModPrefs.MODID, blockName);
        this.adjustSound();
        GameRegistry.register(this);

        if(this.itemBlock != null){
            GameRegistry.register(this.itemBlock.setRegistryName(this.getRegistryName()));
        }
        else{
            GameRegistry.register(new ItemBlockBase(this).setRegistryName(this.getRegistryName()));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        if(this instanceof IMetaBlock){
            IMetaBlock iface = (IMetaBlock)this;

            for(int i = 0; i < iface.getSubNames().length; i++){
                list.add(new ItemStack(item, 1, i));
            }
        }
        else{
            list.add(new ItemStack(item, 1, 0));
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return null;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        if(this instanceof IMetaBlock){
            return new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state));
        }
        else{
            return new ItemStack(state.getBlock(), 1, 0);
        }
    }

    private void adjustSound(){
        if(this.blockMaterial==Material.ANVIL)
            this.blockSoundType = SoundType.ANVIL;
        else if(this.blockMaterial==Material.CARPET||this.blockMaterial==Material.CLOTH)
            this.blockSoundType = SoundType.CLOTH;
        else if(this.blockMaterial==Material.GLASS||this.blockMaterial==Material.ICE)
            this.blockSoundType = SoundType.GLASS;
        else if(this.blockMaterial==Material.GRASS||this.blockMaterial==Material.TNT||this.blockMaterial==Material.PLANTS||this.blockMaterial==Material.VINE)
            this.blockSoundType = SoundType.PLANT;
        else if(this.blockMaterial==Material.GROUND)
            this.blockSoundType = SoundType.GROUND;
        else if(this.blockMaterial==Material.IRON)
            this.blockSoundType = SoundType.METAL;
        else if(this.blockMaterial==Material.SAND)
            this.blockSoundType = SoundType.SAND;
        else if(this.blockMaterial==Material.SNOW)
            this.blockSoundType = SoundType.SNOW;
        else if(this.blockMaterial==Material.ROCK)
            this.blockSoundType = SoundType.STONE;
        else if(this.blockMaterial==Material.WOOD||this.blockMaterial==Material.CACTUS)
            this.blockSoundType = SoundType.WOOD;
    }

    public void setItemBlock(ItemBlock itemBlock){ this.itemBlock = itemBlock; }

    public ItemBlock getItemBlock(){ return this.itemBlock; }

    public String getInternalName(){ return this.blockName; }

}
