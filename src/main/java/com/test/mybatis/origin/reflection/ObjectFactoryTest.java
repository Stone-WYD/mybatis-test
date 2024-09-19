package com.test.mybatis.origin.reflection;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xh
 * @date 2024-09-18
 * @Description:
 */
public class ObjectFactoryTest {

    public static void main(String[] args) {
        DefaultObjectFactory of = new DefaultObjectFactory();
        List<Class<?>> clist = Arrays.asList(String.class, Integer.class);
        List<Object> params = Arrays.asList("小明", 11);
        User user = of.create(User.class, clist, params);
        System.out.println(user);
    }

    private static class User {

        private String name;

        private Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
