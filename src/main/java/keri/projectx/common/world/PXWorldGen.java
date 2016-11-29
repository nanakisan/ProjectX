package keri.projectx.common.world;

import keri.projectx.common.init.PXConfig;
import keri.projectx.common.init.PXContent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class PXWorldGen implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.getDimension()){
            case -1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
                break;
            case 0:
                generateOverworld(world, random, chunkX * 16, chunkZ * 16);
                break;
            case 1:
                generateEnd(world, random, chunkX * 16, chunkZ * 16);
                break;
        }
    }

    @SuppressWarnings("deprecation")
    private void generateOverworld(World world, Random random, int chunkX, int chunkZ){
        for(int i = 0; i < 5; i++){
            this.addOre(PXContent.xycroniumOre.getStateFromMeta(i), random, world, chunkX, chunkZ,
                    PXConfig.xycroniumOreMinHeight,
                    PXConfig.xycroniumOreMaxHeight,
                    PXConfig.xycroniumOreMinVeinSize,
                    PXConfig.xycroniumOreMaxVeinSize,
                    PXConfig.xycroniumOreChance
            );
        }
    }

    private void generateNether(World world, Random random, int chunkX, int chunkZ){

    }

    private void generateEnd(World world, Random random, int chunkX, int chunkZ){

    }

    private void addOre(IBlockState block, Random random, World world, int posX, int posZ, int minY, int maxY, int minVeinSize, int maxVeinSize, int spawnChance){
        for(int i = 0; i < spawnChance; i++){
            int defaultChunkSize = 16;
            int x = posX + random.nextInt(defaultChunkSize);
            int y = minY + random.nextInt(maxY - minY);
            int z = posZ + random.nextInt(defaultChunkSize);
            new WorldGenMinable(block, (minVeinSize + random.nextInt(maxVeinSize - minVeinSize))).generate(world, random, new BlockPos(x, y, z));
        }
    }

}
