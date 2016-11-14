package teammdfive.projectx.common.block;

import com.google.common.collect.Lists;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teammdfive.projectx.api.tool.IDebuggerAction;
import teammdfive.projectx.api.tool.IToolAction;
import teammdfive.projectx.common.init.PXContent;
import teammdfive.projectx.common.tile.TileXynergyNode;
import teammdfive.projectx.common.util.ClientHelper;
import teammdfive.projectx.common.util.DebugHelper;
import teammdfive.projectx.common.util.LanguageHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class BlockXynergyNode extends BlockBase implements IToolAction, IDebuggerAction {

    public BlockXynergyNode() {
        super("xynergy_node", Material.IRON);
        this.setHardness(1.3F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileXynergyNode();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileXynergyNode tile = (TileXynergyNode)world.getTileEntity(pos);
        ItemStack currentHold = player.getHeldItemMainhand();

        if(currentHold != null){
            if(currentHold.getItem() == PXContent.powerCore){
                if(tile != null){
                    if(!tile.getHasCore()){
                        tile.setHasCore(true);
                        tile.setCoreType(currentHold.getMetadata());
                        currentHold.stackSize--;
                        return true;
                    }
                }
            }
            if(currentHold.getItem() == PXContent.xynergyTool){
                if(tile != null){
                    if(tile.getHasCore()){
                        ItemStack drop = new ItemStack(PXContent.powerCore, 1, tile.getCoreType());
                        EntityItem worldDrop = new EntityItem(world, player.posX, player.posY, player.posZ, drop);

                        if(!world.isRemote){
                            world.spawnEntityInWorld(worldDrop);
                        }

                        tile.setCoreType(0);
                        tile.setHasCore(false);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public EnumActionResult onToolUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        return EnumActionResult.FAIL;
    }

    @Override
    public EnumActionResult onDebuggerUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        TileXynergyNode tile = (TileXynergyNode)world.getTileEntity(pos);

        if(!world.isRemote){
            if(tile != null){
                if(tile.getHasCore()){
                    DebugHelper.printCoords(pos, player);
                    ArrayList<String> message = Lists.newArrayList();

                    switch(tile.getCoreType()){
                        case 0:
                            message.add(LanguageHelper.XYNERGY_CLASS + " " + LanguageHelper.XC_LOW);
                            message.add(LanguageHelper.XYNERGY_TYPE + " " + LanguageHelper.XT_NORMAL);
                            break;
                        case 1:
                            message.add(LanguageHelper.XYNERGY_CLASS + " " + LanguageHelper.XC_MIDDLE);
                            message.add(LanguageHelper.XYNERGY_TYPE + " " + LanguageHelper.XT_NORMAL);
                            break;
                        case 2:
                            message.add(LanguageHelper.XYNERGY_CLASS + " " + LanguageHelper.XC_OMNIPOTENT);
                            message.add(LanguageHelper.XYNERGY_TYPE + " " + LanguageHelper.XT_NORMAL);
                            break;
                        case 3:
                            message.add(LanguageHelper.XYNERGY_CLASS + " " + LanguageHelper.XC_LOW);
                            message.add(LanguageHelper.XYNERGY_TYPE + " " + LanguageHelper.XT_RADIAL);
                            break;
                        case 4:
                            message.add(LanguageHelper.XYNERGY_CLASS + " " + LanguageHelper.XC_MIDDLE);
                            message.add(LanguageHelper.XYNERGY_TYPE + " " + LanguageHelper.XT_RADIAL);
                            break;
                        case 5:
                            message.add(LanguageHelper.XYNERGY_CLASS + " " + LanguageHelper.XC_OMNIPOTENT);
                            message.add(LanguageHelper.XYNERGY_TYPE + " " + LanguageHelper.XT_RADIAL);
                            break;
                        case 6:
                            message.add(LanguageHelper.XYNERGY_CLASS + " " + LanguageHelper.XC_LOW);
                            message.add(LanguageHelper.XYNERGY_TYPE + " " + LanguageHelper.XT_LONGRANGE);
                            break;
                        case 7:
                            message.add(LanguageHelper.XYNERGY_CLASS + " " + LanguageHelper.XC_MIDDLE);
                            message.add(LanguageHelper.XYNERGY_TYPE + " " + LanguageHelper.XT_LONGRANGE);
                            break;
                        case 8:
                            message.add(LanguageHelper.XYNERGY_CLASS + " " + LanguageHelper.XC_OMNIPOTENT);
                            message.add(LanguageHelper.XYNERGY_TYPE + " " + LanguageHelper.XT_LONGRANGE);
                            break;
                    }

                    for(String entry : message){
                        player.addChatComponentMessage(new TextComponentString(entry));
                    }

                    player.addChatComponentMessage(new TextComponentString(LanguageHelper.CHAT_ENERGY_STORED + " " + tile.getInternalStorage().getStored()));
                }
                else{
                    DebugHelper.printCoords(pos, player);
                    player.addChatComponentMessage(new TextComponentString(LanguageHelper.CHAT_NO_CORE));
                    player.addChatComponentMessage(new TextComponentString(LanguageHelper.CHAT_ENERGY_STORED + " 0"));
                }

                return EnumActionResult.SUCCESS;
            }
        }
        else{
            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.FAIL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("deprecation")
    public boolean canRenderInLayer(BlockRenderLayer layer) {
        return ClientHelper.canRenderInLayer(layer);
    }

}
