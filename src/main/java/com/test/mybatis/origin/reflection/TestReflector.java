package com.test.mybatis.origin.reflection;

import com.test.mybatis.use.pojo.UserDO;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.TypeParameterResolver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class TestReflector {

    public static void main(String[] args) throws Exception {
        Reflector reflector = new Reflector(UserDO.class);
        ReflectorFactory factory = new DefaultReflectorFactory();

        //测试TypeParameterResolver
        Field name = UserDO.class.getDeclaredField("name");
        Type type = TypeParameterResolver.resolveFieldType(name, UserDO.class);

        Field stringList = UserDO.class.getDeclaredField("stringList");
        Type stringListType = TypeParameterResolver.resolveFieldType(stringList, UserDO.class);
        System.out.println("=============");

    }
}
