package teammdfive.projectx.common.util;

import codechicken.lib.model.ModelRegistryHelper;
import codechicken.lib.render.item.IItemRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teammdfive.projectx.client.model.CustomItemRenderer;
import teammdfive.projectx.client.model.CustomModel;
import teammdfive.projectx.client.model.CustomModelLoader;
import teammdfive.projectx.client.render.IRenderingHandler;
import teammdfive.projectx.common.block.BlockBase;
import teammdfive.projectx.common.item.ItemBase;
import teammdfive.projectx.common.property.EnumColorType;
import teammdfive.projectx.common.property.EnumMCColorType;

@SideOnly(Side.CLIENT)
public class ClientHelper {

    private static final ResourceAction minecraft_particles = new ResourceAction("minecraft", "textures/particle/particles.png");

    public static void registerRenderer(Block block, IRenderingHandler renderer){
        if(block != null && renderer != null){
            if(block instanceof IMetaBlock){
                IMetaBlock iface = (IMetaBlock)block;

                if(iface.getSubNames().length == EnumColorType.values().length){
                    for(int i = 0; i < iface.getSubNames().length; i++){
                        EnumColorType type = EnumColorType.values()[i];
                        ModelResourceLocation location = new ModelResourceLocation(block.getRegistryName(), "color=" + type.getName());
                        CustomModelLoader.INSTANCE.registerModel(location, new CustomModel(renderer));

                        if(Item.getItemFromBlock(block) != null){
                            Item item = Item.getItemFromBlock(block);
                            ModelResourceLocation itemLocation = new ModelResourceLocation(item.getRegistryName(), "inventory");
                            ModelLoader.setCustomModelResourceLocation(item, i, itemLocation);
                            ModelRegistryHelper.registerItemRenderer(item, new CustomItemRenderer(renderer));
                        }
                    }
                }
                else if(iface.getSubNames().length == EnumMCColorType.values().length){
                    for(int i = 0; i < iface.getSubNames().length; i++){
                        EnumMCColorType type = EnumMCColorType.values()[i];
                        ModelResourceLocation location = new ModelResourceLocation(block.getRegistryName(), "color=" + type.getName());
                        CustomModelLoader.INSTANCE.registerModel(location, new CustomModel(renderer));

                        if(Item.getItemFromBlock(block) != null){
                            Item item = Item.getItemFromBlock(block);
                            ModelResourceLocation itemLocation = new ModelResourceLocation(item.getRegistryName(), "inventory");
                            ModelLoader.setCustomModelResourceLocation(item, i, itemLocation);
                            ModelRegistryHelper.registerItemRenderer(item, new CustomItemRenderer(renderer));
                        }
                    }
                }
            }
            else{
                ModelResourceLocation location = new ModelResourceLocation(block.getRegistryName(), "normal");
                CustomModelLoader.INSTANCE.registerModel(location, new CustomModel(renderer));

                if(Item.getItemFromBlock(block) != null){
                    Item item = Item.getItemFromBlock(block);
                    ModelRegistryHelper.registerItemRenderer(item, new CustomItemRenderer(renderer));
                }
            }
        }
        else{
            throw new IllegalArgumentException("Block or IModel can't be null !");
        }
    }

    public static void autoRegister(Item item){
        if(item != null){
            if(item instanceof ItemBase){
                ItemBase itemBase = (ItemBase)item;

                if(itemBase.getSubNames() != null){
                    for(int i = 0; i < itemBase.getSubNames().length; i++){
                        ResourceLocation name = new ResourceLocation(item.getRegistryName().getResourceDomain(), itemBase.getInternalName() + "_" + itemBase.getSubNames()[i]);
                        ModelResourceLocation location = new ModelResourceLocation(name, "inventory");
                        ModelLoader.setCustomModelResourceLocation(itemBase, i, location);
                    }
                }
                else{
                    ModelResourceLocation location = new ModelResourceLocation(item.getRegistryName(), "inventory");
                    ModelLoader.setCustomModelResourceLocation(itemBase, 0, location);
                }
            }
            else{
                ModelResourceLocation location = new ModelResourceLocation(item.getRegistryName(), "inventory");
                ModelLoader.setCustomModelResourceLocation(item, 0, location);
            }
        }
        else{
            throw new IllegalArgumentException("Item can't be null !");
        }
    }

    public static void registerBuiltin(Item item, IItemRenderer renderer){
        if(item != null){
            ModelRegistryHelper.registerItemRenderer(item, renderer);
        }
        else{
            throw new IllegalArgumentException("Item can't be null !");
        }
    }

    public static void registerModel(BlockBase block, IModel model){
        if(block != null && model != null){
            if(block instanceof IMetaBlock){
                IMetaBlock iface = (IMetaBlock)block;

                if(iface.getSubNames().length == EnumColorType.values().length){
                    for(int i = 0; i < iface.getSubNames().length; i++){
                        EnumColorType type = EnumColorType.values()[i];
                        ModelResourceLocation location = new ModelResourceLocation(block.getRegistryName(), "color=" + type.getName());
                        CustomModelLoader.INSTANCE.registerModel(location, model);

                        if(Item.getItemFromBlock(block) != null){
                            Item item = Item.getItemFromBlock(block);
                            ModelLoader.setCustomModelResourceLocation(item, i, location);
                        }
                    }
                }
                else if(iface.getSubNames().length == EnumColorType.values().length){
                    for(int i = 0; i < iface.getSubNames().length; i++){
                        EnumColorType type = EnumColorType.values()[i];
                        ModelResourceLocation location = new ModelResourceLocation(block.getRegistryName(), "color=" + type.getName());
                        CustomModelLoader.INSTANCE.registerModel(location, model);

                        if(Item.getItemFromBlock(block) != null){
                            Item item = Item.getItemFromBlock(block);
                            ModelLoader.setCustomModelResourceLocation(item, i, location);
                        }
                    }
                }
            }
            else{
                ModelResourceLocation location = new ModelResourceLocation(block.getRegistryName(), "normal");
                CustomModelLoader.INSTANCE.registerModel(location, model);

                if(Item.getItemFromBlock(block) != null){
                    Item item = Item.getItemFromBlock(block);
                    ModelLoader.setCustomModelResourceLocation(item, 0, location);
                }
            }
        }
        else{
            throw new IllegalArgumentException("Block or IModel can't be null !");
        }
    }

    public static void registerModel(ModelResourceLocation location, IModel model){
        if(location != null && model != null){
            CustomModelLoader.INSTANCE.registerModel(location, model);
        }
        else{
            throw new IllegalArgumentException("ModelResourceLocation or IModel can't be null !");
        }
    }

    public static void applyStateMapper(BlockBase block){
        if(block != null){
            if(block instanceof IMetaBlock){
                IMetaBlock iface = (IMetaBlock)block;

                if(iface.getSubNames().length == EnumColorType.values().length){
                    for(int i = 0; i < iface.getSubNames().length; i++){
                        EnumColorType type = EnumColorType.values()[i];
                        ModelResourceLocation location = new ModelResourceLocation(block.getRegistryName(), "color=" + type.getName());
                        ModelLoader.setCustomStateMapper(block, new CustomStateMapper(location));
                    }
                }
                else if(iface.getSubNames().length == EnumMCColorType.values().length){
                    for(int i = 0; i < iface.getSubNames().length; i++){
                        EnumMCColorType type = EnumMCColorType.values()[i];
                        ModelResourceLocation location = new ModelResourceLocation(block.getRegistryName(), "color=" + type.getName());
                        ModelLoader.setCustomStateMapper(block, new CustomStateMapper(location));
                    }
                }
            }
            else{
                ModelResourceLocation location = new ModelResourceLocation(block.getRegistryName(), "normal");
                ModelLoader.setCustomStateMapper(block, new CustomStateMapper(location));
            }
        }
        else{
            throw new IllegalArgumentException("Block can't be null !");
        }
    }

    public static boolean canRenderInLayer(BlockRenderLayer layer){
        return layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.CUTOUT_MIPPED;
    }

    public static void resetParticleTexture(){
        minecraft_particles.bind(true);
    }

    @SideOnly(Side.CLIENT)
    private static class CustomStateMapper extends StateMapperBase {

        private ModelResourceLocation location;

        public CustomStateMapper(ModelResourceLocation location){
            this.location = location;
        }

        @Override
        protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
            return this.location;
        }

    }

}
