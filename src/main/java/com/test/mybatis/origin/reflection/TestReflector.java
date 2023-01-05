package com.test.mybatis.origin.reflection;

import com.test.mybatis.use.pojo.UserDO;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.ReflectorFactory;

public class TestReflector {

    public static void main(String[] args) {
        Reflector reflector = new Reflector(UserDO.class);
        ReflectorFactory factory = new DefaultReflectorFactory();
    }
}
