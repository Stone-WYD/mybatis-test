package com.test.mybatis.origin.reflection;

import org.apache.ibatis.reflection.property.PropertyCopier;
import org.apache.ibatis.reflection.property.PropertyNamer;
import org.apache.ibatis.reflection.property.PropertyTokenizer;

/**
 * @author xh
 * @date 2024-09-18
 * @Description: 参数工具测试类
 */
public class PropertyTest {

    public static void main(String[] args) {
        // PropertyTokenizer 用法展示
        System.out.println("===========PropertyTokenizer 用法展示==========");
        PropertyTokenizer token = new PropertyTokenizer("order[1].itmes[0].name");
        printContent(token);
        System.out.println("=============");
        PropertyTokenizer next = token.next();
        printContent(next);

        // PropertyNamer 用法展示，看源码可以发现并没有多少内容
        System.out.println("===========PropertyNamer 用法展示==========");
        System.out.println("name 是否是属性判断：" + PropertyNamer.isProperty("isName"));
        System.out.println("getName 是否是get方法判断：" + PropertyNamer.isGetter("getName"));
        System.out.println("setName 是否是set方法判断：" + PropertyNamer.isSetter("setName"));

        // PropertyCopier 用法展示
        System.out.println("===========PropertyCopier 用法展示==========");
        User user1 = new User("", 0);
        User user2 = new User("wyd", 11);
        PropertyCopier.copyBeanProperties(User.class, user2, user1);
        System.out.println(user1);
    }

    private static void printContent(PropertyTokenizer token) {
        System.out.println("name: " + token.getName());
        System.out.println("index: " + token.getIndex());
        System.out.println("indexedName: " + token.getIndexedName());
        System.out.println("children: " + token.getChildren());
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
