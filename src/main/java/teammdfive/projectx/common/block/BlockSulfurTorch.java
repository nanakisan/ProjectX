package teammdfive.projectx.common.block;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teammdfive.projectx.client.particle.SparkleParticle;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockSulfurTorch extends BlockBase {

    private static final AxisAlignedBB STANDING_AABB = new AxisAlignedBB(0.4000000059604645D, 0.0D, 0.4000000059604645D, 0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
    private static final AxisAlignedBB TORCH_NORTH_AABB = new AxisAlignedBB(0.3499999940395355D, 0.20000000298023224D, 0.699999988079071D, 0.6499999761581421D, 0.800000011920929D, 1.0D);
    private static final AxisAlignedBB TORCH_SOUTH_AABB = new AxisAlignedBB(0.3499999940395355D, 0.20000000298023224D, 0.0D, 0.6499999761581421D, 0.800000011920929D, 0.30000001192092896D);
    private static final AxisAlignedBB TORCH_WEST_AABB = new AxisAlignedBB(0.699999988079071D, 0.20000000298023224D, 0.3499999940395355D, 1.0D, 0.800000011920929D, 0.6499999761581421D);
    private static final AxisAlignedBB TORCH_EAST_AABB = new AxisAlignedBB(0.0D, 0.20000000298023224D, 0.3499999940395355D, 0.30000001192092896D, 0.800000011920929D, 0.6499999761581421D);

    private static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>() {
        @Override
        public boolean apply(@Nullable EnumFacing input) {
            return input != EnumFacing.DOWN;
        }
    });

    public BlockSulfurTorch() {
        super("sulfur_torch", Material.WOOD);
        this.setHardness(0F);
        this.setTickRandomly(true);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
    }

    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        switch((EnumFacing)state.getValue(FACING)){
            case EAST:
                return TORCH_EAST_AABB;
            case WEST:
                return TORCH_WEST_AABB;
            case SOUTH:
                return TORCH_SOUTH_AABB;
            case NORTH:
                return TORCH_NORTH_AABB;
            default:
                return STANDING_AABB;
        }
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos){
        return NULL_AABB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isOpaqueCube(IBlockState state){
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFullCube(IBlockState state){
        return false;
    }

    private boolean canPlaceOn(World world, BlockPos pos){
        IBlockState state = world.getBlockState(pos);
        if (state.isSideSolid(world, pos, EnumFacing.UP)){
            return true;
        }
        else{
            return state.getBlock().canPlaceTorchOnTop(state, world, pos);
        }
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos){
        for (EnumFacing enumfacing : FACING.getAllowedValues()){
            if (this.canPlaceAt(world, pos, enumfacing)){
                return true;
            }
        }

        return false;
    }

    private boolean canPlaceAt(World world, BlockPos pos, EnumFacing facing){
        BlockPos blockpos = pos.offset(facing.getOpposite());
        boolean flag = facing.getAxis().isHorizontal();
        return flag && world.isSideSolid(blockpos, facing, true) || facing.equals(EnumFacing.UP) && this.canPlaceOn(world, blockpos);
    }

    @Override
    public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
        if (this.canPlaceAt(world, pos, facing)){
            return this.getDefaultState().withProperty(FACING, facing);
        }
        else{
            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL){
                if (world.isSideSolid(pos.offset(enumfacing.getOpposite()), enumfacing, true)){
                    return this.getDefaultState().withProperty(FACING, enumfacing);
                }
            }

            return this.getDefaultState();
        }
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state){
        this.checkForDrop(world, pos, state);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block){
        this.onNeighborChangeInternal(world, pos, state);
    }

    private boolean onNeighborChangeInternal(World world, BlockPos pos, IBlockState state){
        if (!this.checkForDrop(world, pos, state)){
            return true;
        }
        else{
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
            EnumFacing.Axis enumfacing$axis = enumfacing.getAxis();
            EnumFacing enumfacing1 = enumfacing.getOpposite();
            boolean flag = false;

            if (enumfacing$axis.isHorizontal() && !world.isSideSolid(pos.offset(enumfacing1), enumfacing, true)){
                flag = true;
            }
            else if (enumfacing$axis.isVertical() && !this.canPlaceOn(world, pos.offset(enumfacing1))){
                flag = true;
            }

            if (flag){
                this.dropBlockAsItem(world, pos, state, 0);
                world.setBlockToAir(pos);
                return true;
            }
            else{
                return false;
            }
        }
    }

    private boolean checkForDrop(World world, BlockPos pos, IBlockState state){
        if (state.getBlock() == this && this.canPlaceAt(world, pos, (EnumFacing)state.getValue(FACING))){
            return true;
        }
        else{
            if (world.getBlockState(pos).getBlock() == this){
                this.dropBlockAsItem(world, pos, state, 0);
                world.setBlockToAir(pos);
            }

            return false;
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        IBlockState iblockstate = this.getDefaultState();

        switch (meta){
            case 1:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST);
                break;
            case 2:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST);
                break;
            case 3:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH);
                break;
            case 5:
            default:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.UP);
        }

        return iblockstate;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer(){
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public int getMetaFromState(IBlockState state){
        int i = 0;

        switch ((EnumFacing)state.getValue(FACING)){
            case EAST:
                i = i | 1;
                break;
            case WEST:
                i = i | 2;
                break;
            case SOUTH:
                i = i | 3;
                break;
            case NORTH:
                i = i | 4;
                break;
            case DOWN:
            case UP:
            default:
                i = i | 5;
        }

        return i;
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot){
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirror){
        return state.withRotation(mirror.toRotation((EnumFacing)state.getValue(FACING)));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
        double modY = 0.2199999988079071D;
        double modX = 0.27000001072883606D;
        int meta = this.getMetaFromState(state);
        float x1 = (float)pos.getX() + 0.5F;
        float y1 = (float)pos.getY() + 0.6F;
        float z1 = (float)pos.getZ() + 0.49F;

        if(meta >= 1 && meta <= 4){
            y1 = (float) ((double) y1 + modY);
        }

        switch (meta) {
            case 1:
                x1 = (float)((double)x1 - modX);
                break;
            case 2:
                x1 = (float)((double)x1 + modX);
                break;
            case 3:
                z1 = (float)((double)z1 - modX);
                break;
            case 4:
                z1 = (float)((double)z1 + modX);
        }

        float offset = rand.nextFloat() - rand.nextFloat();
        SparkleParticle particle = new SparkleParticle(world, (double) x1, (double) y1, (double) z1, (double) (x1 + (rand.nextFloat() - rand.nextFloat()) / 4.0F), (double) (y1 + (rand.nextFloat() - rand.nextFloat()) / 4.0F), (double) (z1 + (rand.nextFloat() - rand.nextFloat()) / 4.0F), 0.75F, 7, 22);
        particle.setGravity(-0.013F);
        particle.setParticle(3);
        particle.tinkle = true;
        particle.shrink = true;
        Minecraft.getMinecraft().effectRenderer.addEffect(particle);
    }

}
