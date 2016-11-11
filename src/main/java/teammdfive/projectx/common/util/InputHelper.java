package teammdfive.projectx.common.util;

import org.lwjgl.input.Keyboard;

public class InputHelper {

    public static boolean isShiftPressed(){
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }

    public static boolean isControllPressed(){
        return Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
    }

}
