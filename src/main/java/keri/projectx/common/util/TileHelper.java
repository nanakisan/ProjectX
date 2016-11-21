package keri.projectx.common.util;

import com.google.common.collect.Lists;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class TileHelper {

    public static void writeCoordList(ArrayList<BlockPos> list, NBTTagCompound tag){
        int[] entriesX = new int[list.size()];
        int[] entriesY = new int[list.size()];
        int[] entriesZ = new int[list.size()];

        for(int i = 0; i < list.size(); i++){
            entriesX[i] = list.get(i).getX();
            entriesY[i] = list.get(i).getY();
            entriesZ[i] = list.get(i).getZ();
        }

        tag.setIntArray("devicesX", entriesX);
        tag.setIntArray("devicesY", entriesY);
        tag.setIntArray("devicesZ", entriesZ);
    }

    public static ArrayList<BlockPos> readCoordList(NBTTagCompound tag){
        ArrayList<BlockPos> list = Lists.newArrayList();

        int[] entriesX = tag.getIntArray("devicesX");
        int[] entriesY = tag.getIntArray("devicesY");
        int[] entriesZ = tag.getIntArray("devicesZ");

        for(int i = 0; i < entriesX.length; i++){
            list.add(new BlockPos(entriesX[i], entriesY[i], entriesZ[i]));
        }

        return list;
    }

}
