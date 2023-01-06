package com.test.mybatis.origin.reflection.test.reflector;

import com.test.mybatis.use.pojo.UserDO;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.TypeParameterResolver;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class Reflector {

    public static void main(String[] args) throws Exception {
        org.apache.ibatis.reflection.Reflector reflector = new org.apache.ibatis.reflection.Reflector(UserDO.class);
        ReflectorFactory factory = new DefaultReflectorFactory();

        //测试TypeParameterResolver
        Field name = UserDO.class.getDeclaredField("name");
        Type type = TypeParameterResolver.resolveFieldType(name, UserDO.class);

        Field stringList = UserDO.class.getDeclaredField("stringList");
        Type stringListType = TypeParameterResolver.resolveFieldType(stringList, UserDO.class);
        System.out.println("=============");

    }
}
