package teammdfive.projectx.common.util;

import codechicken.lib.vec.Vector3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DebugHelper {

    public static void printCoords(BlockPos pos, EntityPlayer player){
        if(pos != null && player != null){
            player.addChatComponentMessage(new TextComponentString(LanguageHelper.CHAT_POSITION + " " + pos.toString()));
        }
    }

    public static void printCoords(Vector3 pos, EntityPlayer player){
        if(pos != null && player != null){
            player.addChatComponentMessage(new TextComponentString(LanguageHelper.CHAT_POSITION + " " + pos.toString()));
        }
    }

}
