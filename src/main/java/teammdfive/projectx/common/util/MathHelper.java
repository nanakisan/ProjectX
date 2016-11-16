package teammdfive.projectx.common.util;

import codechicken.lib.vec.Vector3;

public class MathHelper {

    public static Vector3 cross(Vector3 a, Vector3 b){
        double x = a.y * b.z - a.z * b.y;
        double y = a.z * b.x - a.x * b.z;
        double z = a.x * b.y - a.y * b.x;
        return new Vector3(x, y, z);
    }

    public static Vector3 subtract(Vector3 a, Vector3 b){
        return new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    public static Vector3 add(Vector3 a, Vector3 b){
        return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public static Vector3 multiply(Vector3 a, double factor){
        return new Vector3(a.x * factor, a.y * factor, a.z * factor);
    }

}
