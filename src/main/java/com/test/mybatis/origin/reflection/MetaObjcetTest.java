package com.test.mybatis.origin.reflection;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xh
 * @date 2024-09-19
 * @Description:
 */
public class MetaObjcetTest {

    public static void main(String[] args) {
        /*
        // 简单测试 MetaObject 和 ObjectWrapper 的使用
        simpleTest();
        */

        /*
        // 测试 MetaObject 和 ObjectWrapper 的关系
        metaObjectTest();
        */

        // 测试自定义 ObjectWrapperFactory 的使用
        OutBean ob = new OutBean();
        Bean b = new Bean("222", 11);
        ob.setBean(b);
        ob.setName("outBean");
        MetaObject metaObject = MetaObject.forObject(ob, new DefaultObjectFactory(), new MyObjectWrapperFactory(), new DefaultReflectorFactory());
        System.out.println(metaObject.getValue("bean.name"));

    }

    private static void metaObjectTest() {
        OutBean ob = new OutBean();
        Bean b = new Bean("222", 11);
        ob.setBean(b);
        ob.setName("outBean");

        MetaObject oriMetaObject = MetaObject.forObject(ob, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
        // 直接创建 ObjectWrapper 的情况
        BeanWrapper beanWrapper = new BeanWrapper(oriMetaObject, ob);
        MapWrapper mapWrapper = new MapWrapper(oriMetaObject, new HashMap<>());

        Object o = beanWrapper.get(new PropertyTokenizer("bean.name"));
        System.out.println(o);
        System.out.println("==============");
        Object mapO = mapWrapper.get(new PropertyTokenizer("bean.name"));
        System.out.println(mapO);
        System.out.println("==============");
        Object value = oriMetaObject.getValue("bean.name");
        System.out.println(value);

        System.out.println("==============");
        System.out.println("测试map");
        Map<String, OutBean> map = new HashMap<>();
        map.put("out", ob);
        MetaObject mapMetaObject = MetaObject.forObject(map, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
        System.out.println(mapMetaObject.getValue("out.bean.name"));
    }


    private static void simpleTest() {
        Bean bean = new Bean("王布布", 14);
        List<String> hobbyList = new ArrayList<>();
        hobbyList.add("打篮球");
        hobbyList.add("玩游戏");
        bean.setHobbyList(hobbyList);

        MetaObject metaobject = MetaObject.forObject(bean, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
        BeanWrapper beanWrapper = new BeanWrapper(metaobject, bean);

        System.out.println(beanWrapper.get(new PropertyTokenizer("hobbyList[1]")));
    }


    private static class MyObjectWrapperFactory implements ObjectWrapperFactory {

        @Override
        public boolean hasWrapperFor(Object o) {
            return o.getClass().isAssignableFrom(OutBean.class);
        }

        @Override
        public ObjectWrapper getWrapperFor(MetaObject metaObject, Object o) {
            return new MyObjectWrapper(metaObject, o);
        }
    }

    private static class MyObjectWrapper extends BeanWrapper {

        public MyObjectWrapper(MetaObject metaObject, Object object) {
            super(metaObject, object);
        }

        @Override
        public Object get(PropertyTokenizer prop) {
            Object o = super.get(prop);
            System.out.println("这里是加强内容，只是简单的打印一句话...");
            return o;
        }
    }
}
