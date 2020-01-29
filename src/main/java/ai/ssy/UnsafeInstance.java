package ai.ssy;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @description : 通过反射获取 sun.misc.Unsafe 实例
 */
public class UnsafeInstance {

    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
