package keri.projectx.client.model;

import com.google.common.collect.Maps;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Map;

/**
 * Simple custom model loader for all blocks in ProjectX.
 * The uses a ResourceLocation as the key value and a IModel
 * as the actual model. The loader can then request the
 * desired model from the map directly using the modelLocation argument
 * in loadModel.
 */
@SideOnly(Side.CLIENT)
public class CustomModelLoader implements ICustomModelLoader {

    public static final CustomModelLoader INSTANCE = new CustomModelLoader();
    private final Map<ResourceLocation, IModel> models = Maps.newHashMap();

    @Override
    public boolean accepts(ResourceLocation modelLocation) {
        return this.models.containsKey(modelLocation);
    }

    @Override
    public IModel loadModel(ResourceLocation modelLocation) throws Exception {
        return this.models.get(modelLocation);
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager){}

    /**
     * Registers a given IModel to the given ModelResourceLocation.
     * Neither model or location can be null, if one of them is the loader
     * will throw an IllegalArgumentException.
     * @param location
     * @param model
     */
    public void registerModel(ModelResourceLocation location, IModel model){
        if(location != null && model != null){
            this.models.put(location, model);
        }
        else{
            throw new IllegalArgumentException("Error loading models - ResourceLocation or IModel can't be null !");
        }
    }

}
