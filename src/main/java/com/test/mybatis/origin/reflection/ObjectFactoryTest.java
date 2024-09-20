package com.test.mybatis.origin.reflection;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

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
        Bean bean = of.create(Bean.class, clist, params);
        System.out.println(bean);
    }

}
