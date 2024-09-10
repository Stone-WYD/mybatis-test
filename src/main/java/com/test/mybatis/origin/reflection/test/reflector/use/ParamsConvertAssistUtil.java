package com.test.mybatis.origin.reflection.test.reflector.use;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xh
 * @date 2024-08-26
 * @Description:
 */
public class ParamsConvertAssistUtil {

    // 判断类型是否为基本类型
    public static boolean isPrimitiveOrWrapper(Class<?> cls) {
        if (cls == null) {
            return false;
        }
        return cls.isPrimitive() || wrapToPrimitiveMap.containsKey(cls);
    }

    private static final Map<Class<?>, Class<?>> wrapToPrimitiveMap = new HashMap<>();

    static {
        wrapToPrimitiveMap.put(Boolean.class, boolean.class);
        wrapToPrimitiveMap.put(Byte.class, byte.class);
        wrapToPrimitiveMap.put(Character.class, char.class);
        wrapToPrimitiveMap.put(Short.class, short.class);
        wrapToPrimitiveMap.put(Integer.class, int.class);
        wrapToPrimitiveMap.put(Long.class, long.class);
        wrapToPrimitiveMap.put(Float.class, float.class);
        wrapToPrimitiveMap.put(Double.class, double.class);
        wrapToPrimitiveMap.put(Void.class, void.class);
    }

}
