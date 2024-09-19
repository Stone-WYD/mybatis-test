package com.test.mybatis.origin.reflection.reflector;

import com.test.mybatis.origin.reflection.reflector.type.resolve.ClassA;
import com.test.mybatis.origin.reflection.reflector.type.resolve.SubClassA;
import com.test.mybatis.origin.reflection.reflector.type.resolve.TestType;
import com.test.mybatis.use.pojo.UserDO;
import org.apache.ibatis.reflection.TypeParameterResolver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射相关测试
 * */
public class ReflectTests {

    public static void main(String[] args) throws Exception {

        advancedTypeParameterResolverTest();

    }

    public static void basicTypeParameterResolverTest() throws Exception {
        // 基本测试 TypeParameterResolver
        Field name = UserDO.class.getDeclaredField("name");
        Type type = TypeParameterResolver.resolveFieldType(name, UserDO.class);

        Field stringList = UserDO.class.getDeclaredField("stringList");
        Type stringListType = TypeParameterResolver.resolveFieldType(stringList, UserDO.class);
        System.out.println("=============");

        System.out.println("name 字段解析前后信息：");
        System.out.println("解析前：" + name.getType().getName());
        System.out.println("解析后：" + type.getTypeName());

        System.out.println("===============");
        System.out.println("stringList 字段解析前后信息：");
        System.out.println("解析前：" + stringList.getType().getTypeName());
        System.out.println("解析后：" + stringListType.getTypeName());

        System.out.println("===============");
        // 如果要获取 stringList 的泛型信息
        Type stringListGenericType = stringList.getGenericType();
        if (stringListGenericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) stringListGenericType;
            for (Type at : parameterizedType.getActualTypeArguments()) {
                System.out.println(at.getTypeName());
            }
        }
    }

    /**
     *  目标：创建一个 TestType 对象 tt，再创建一个 SubCalssA 对象 sa，然后将 sa 塞入 tt。
     *  另外， sa 中要初始化 map，map 中存入 “1L -> 2L” 的键值对。
     * */
    public static void advancedTypeParameterResolverTest() throws Exception {

        // 1. 使用 MyBatis 工具类
        System.out.println("========= 使用 MyBatis 工具类 =========");
        // 1.1 创建最外层对象
        TestType testType = TestType.class.newInstance();
        // 1.2 创建 SubClassA 对象并塞入 testType 中
        SubClassA subClassA = SubClassA.class.newInstance();
        Field sa = TestType.class.getDeclaredField("sa");
        sa.setAccessible(true);
        sa.set(testType, subClassA);
        // 1.3 创建 map 对象并塞入 sa 中
        Field f = ClassA.class.getDeclaredField("map");
        Type type = TypeParameterResolver.resolveFieldType(f,
                TestType.class.getDeclaredField("sa").getGenericType());
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            // 获取最外层类型创建实例
            Class<?> rawType = (Class<?>) parameterizedType.getRawType();
            if (!rawType.isAssignableFrom(Map.class)) {
                System.out.println("有问题。。。");
                return;
            }
            Object result = HashMap.class.newInstance();
            // 获取泛型类型创建实例
            Object key, value;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Class firstArgClass = (Class) actualTypeArguments[0];
            Class secondArgClass = (Class) actualTypeArguments[1];
            key = getBasicObject(firstArgClass, "1");
            value = getBasicObject(secondArgClass, "2");

            Method putMethod = Map.class.getDeclaredMethod("put", Object.class, Object.class);
            putMethod.invoke(result, key, value);
            Field map = SubClassA.class.getSuperclass().getDeclaredField("map");
            map.setAccessible(true);
            map.set(subClassA, result);
        }

        // 打印 testType 中的内容
        System.out.println(testType);

        /**
         * 使用 advancedTypeParameterResolverTest 时，并不在意泛型的具体类型。
         * 但如果不使用 MyBatis 工具类，获取 map 的泛型类型会比较困难。
         * */
    }

    private static Object getBasicObject(Class cl, String content) {
        if (cl == Long.class) {
            return Long.parseLong(content);
        }
        if (cl == Integer.class) {
            return Integer.parseInt(content);
        }
        if (cl == Double.class) {
            return Double.parseDouble(content);
        }
        if (cl == Float.class) {
            return Float.parseFloat(content);
        }
        if (cl == Character.class) {
            return content.charAt(0);
        }
        if (cl == String.class) {
            return content;
        }
        throw new RuntimeException("传入类型不是基本类型，无法初始化！");
    }
}
