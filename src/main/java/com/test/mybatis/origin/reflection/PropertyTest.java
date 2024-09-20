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
        Bean bean1 = new Bean("", 0);
        Bean bean2 = new Bean("wyd", 11);
        PropertyCopier.copyBeanProperties(Bean.class, bean2, bean1);
        System.out.println(bean1);
    }

    private static void printContent(PropertyTokenizer token) {
        System.out.println("name: " + token.getName());
        System.out.println("index: " + token.getIndex());
        System.out.println("indexedName: " + token.getIndexedName());
        System.out.println("children: " + token.getChildren());
    }

}
