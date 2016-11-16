package teammdfive.projectx.common.init;

import com.google.common.collect.Lists;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;

public class PXConfig {

    public static final String GENERAL = "general";
    public static final String WORLDGEN = "worldgen";

    public static boolean enableParticles;

    public static int cornSeedChance;
    public static int xycroniumOreChance;
    public static int xycroniumOreMinHeight;
    public static int xycroniumOreMaxHeight;
    public static int xycroniumOreMinVeinSize;
    public static int xycroniumOreMaxVeinSize;

    public PXConfig(FMLPreInitializationEvent event){
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        StopWatch timer = new StopWatch();
        timer.start();
        config.load();

        if(this.getCategories() != null && !this.getCategories().isEmpty()){
            for(Pair<String, String> category : this.getCategories()){
                config.addCustomCategoryComment(category.getLeft(), category.getRight());
            }
        }

        this.getFlags(config);
        config.save();
        timer.stop();
        LogManager.getLogger(event.getModMetadata().name).info("Done reading config after " + timer.getTime() + "ms !");
    }

    private ArrayList<Pair<String, String>> getCategories(){
        ArrayList<Pair<String, String>> categories = Lists.newArrayList();
        categories.add(Pair.of(GENERAL, "General settings related to ProjectX"));
        categories.add(Pair.of(WORLDGEN, "Settings related to the worldgen from ProjectX"));
        return categories;
    }

    private void getFlags(Configuration config){
        enableParticles = config.get(GENERAL, "enableParticles", true).getBoolean();
        cornSeedChance = config.get(WORLDGEN, "cornSeedChance", 4).getInt();
        xycroniumOreChance = config.get(WORLDGEN, "xycroniumOreChance", 19).getInt();
        xycroniumOreMinHeight = config.get(WORLDGEN, "xycroniumOreMinHeight", 12).getInt();
        xycroniumOreMaxHeight = config.get(WORLDGEN, "xycroniumOreMaxHeight", 80).getInt();
        xycroniumOreMinVeinSize = config.get(WORLDGEN, "xycroniumOreMinVeinSize", 4).getInt();
        xycroniumOreMaxVeinSize = config.get(WORLDGEN, "xycroniumOreMaxVeinSize", 6).getInt();
    }

}
