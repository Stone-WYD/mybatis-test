package com.test.mybatis.origin.reflection.test.reflector.metaclass;

import com.test.mybatis.origin.reflection.test.reflector.bean.BeanWithNest;
import com.test.mybatis.origin.reflection.test.reflector.bean.param.QueryCondition;
import com.test.mybatis.origin.reflection.test.reflector.bean.param.XsParamCommon;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;

/**
 * @author xh
 * @date 2024-08-28
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        DefaultReflectorFactory reflectorFactory = new DefaultReflectorFactory();
        MetaClass metaClass = MetaClass.forClass(BeanWithNest.class, reflectorFactory);
        String property = metaClass.findProperty("hobby.hobbyName");
        System.out.println(property);
    }
}
