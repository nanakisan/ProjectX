package teammdfive.projectx.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teammdfive.projectx.common.init.PXContent;
import teammdfive.projectx.common.item.ItemBlockBase;
import teammdfive.projectx.common.property.CommonProperties;
import teammdfive.projectx.common.property.EnumGrowthStage;
import teammdfive.projectx.common.util.ModPrefs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockCornCrop extends BlockBush implements IGrowable {

    private final String blockName = "corn_crop";
    private final AxisAlignedBB BOX_0 = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.375D, 1D);
    private final AxisAlignedBB BOX_1 = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.625D, 1D);
    private final AxisAlignedBB BOX_2 = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.875D, 1D);

    public BlockCornCrop(){
        this.setCreativeTab((CreativeTabs)null);
        this.setUnlocalizedName(ModPrefs.MODID + "." + this.blockName);
        this.setRegistryName(ModPrefs.MODID, this.blockName);
        this.setTickRandomly(true);
        this.disableStats();
        this.setDefaultState(this.blockState.getBaseState().withProperty(CommonProperties.GROWTH_STAGE, EnumGrowthStage.BOTTOM0));
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlockBase(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{CommonProperties.GROWTH_STAGE});
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.blockState.getBaseState().withProperty(CommonProperties.GROWTH_STAGE, EnumGrowthStage.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        EnumGrowthStage stage = (EnumGrowthStage)state.getValue(CommonProperties.GROWTH_STAGE);
        return stage.getID();
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state){
        boolean b = super.canBlockStay(world, pos, state);

        if(this.getMetaFromState(state) == 5){
            IBlockState stateBelow = world.getBlockState(pos.add(0, -1, 0));
            b = stateBelow.getBlock().equals(this) && this.getMetaFromState(stateBelow) == getMaxMeta(0);
        }

        return b;
    }

    @Override
    protected boolean canSustainBush(IBlockState state){
        return state.getBlock() == Blocks.FARMLAND;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos){
        return EnumPlantType.Crop;
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World world, BlockPos pos){
        int meta = this.getMetaFromState(world.getBlockState(pos));
        return meta == 0 ? BOX_0 : meta == 1 ? BOX_1 : meta == 2 ? BOX_2 : FULL_BLOCK_AABB;
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune){
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        int meta = this.getMetaFromState(state);

        if(meta > 4){
            Random rand = world instanceof World ? ((World)world).rand : RANDOM;

            for(int i = 0; i < 3 + fortune; i++){
                if(rand.nextInt(8) <= meta){
                    drops.add(new ItemStack(PXContent.corn, 1, 0));
                }
            }

            drops.add(new ItemStack(PXContent.cornKernel, 1, 0));
        }
        else{
            drops.add(new ItemStack(PXContent.cornKernel, 1, 0));
        }

        return drops;
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block){
        super.neighborChanged(state, world, pos, block);

        if(this.getMetaFromState(state) < getMaxMeta(0)){
            world.notifyBlockOfStateChange(pos.add(0, 1, 0), this);
        }
    }

    @Override
    public void updateTick (World world, BlockPos pos, IBlockState state, Random random){
        this.checkAndDropBlock(world, pos, state);
        int light = world.getLight(pos);

        if(light >= 12){
            int meta = this.getMetaFromState(state);

            if(meta > 4){
                return;
            }

            float growth = this.getGrowthSpeed(world, pos, state, light);

            if(random.nextInt((int)(50F / growth) + 1) == 0){
                if(this.getMaxMeta(meta) != meta){
                    meta++;
                    world.setBlockState(pos, this.getStateFromMeta(meta));
                }
                if(meta > 3 && world.isAirBlock(pos.add(0, 1, 0))){
                    world.setBlockState(pos.add(0, 1, 0), this.getStateFromMeta(meta + 1));
                }
            }
        }
    }

    private float getGrowthSpeed(World world, BlockPos pos, IBlockState sate, int light){
        float growth = 0.125F * (light - 11);

        if(world.canBlockSeeSky(pos)){
            growth += 2F;
        }

        IBlockState soil = world.getBlockState(pos.add(0, -1, 0));

        if(soil.getBlock().isFertile(world,pos.add(0, -1, 0))){
            growth *= 1.5F;
        }

        return 1F + growth;
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos){
        IBlockState soil = world.getBlockState(pos.down());
        return super.canPlaceBlockAt(world, pos) && soil.getBlock().canSustainPlant(soil, world, pos.down(), net.minecraft.util.EnumFacing.UP, this);
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient){
        int meta = this.getMetaFromState(state);

        if(meta < getMaxMeta(meta)){
            return true;
        }
        else{
            return meta==4 && !world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(this);
        }
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state){
        int meta = this.getMetaFromState(state);

        if(meta < getMaxMeta(meta)){
            return true;
        }
        else{
            return meta == 4 && !world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(this);
        }
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state){
        int meta = this.getMetaFromState(state);

        if(meta<getMaxMeta(meta)){
            int span = getMaxMeta(meta) - meta;
            int newMeta = meta + rand.nextInt(span) + 1;

            if(newMeta != meta){
                world.setBlockState(pos, this.getStateFromMeta(newMeta));
            }

            meta = newMeta;
        }
        if(meta == 4 && world.isAirBlock(pos.add(0, 1, 0))){
            world.setBlockState(pos.add(0, 1, 0), this.getStateFromMeta(meta + 1));
        }
    }

    public int getMinMeta(int meta){
        return meta <= 4 ? 0 : 5;
    }

    public int getMaxMeta(int meta){
        return meta <= 4 ? 4 : 5;
    }

}
