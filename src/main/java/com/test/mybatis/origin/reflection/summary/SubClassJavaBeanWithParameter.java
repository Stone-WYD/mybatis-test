package com.test.mybatis.origin.reflection.summary;

import com.test.mybatis.origin.reflection.test.reflector.type.resolve.ClassA;
import com.test.mybatis.origin.reflection.test.reflector.type.resolve.SubClassA;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.invoker.Invoker;

import java.util.HashMap;
import java.util.Map;

public class SubClassJavaBeanWithParameter {
    public static void main(String[] args) throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("w","yd");
        map.put("wyd","Stone");

        SubClassA<String> sa = new SubClassA<>();
        sa.setMap(map);

        //测试ClassA
        Reflector reflector = new Reflector(ClassA.class);
        Invoker mapGet = reflector.getGetInvoker("map");
        ClassA<String, String> testClassA = new ClassA<>();
        testClassA.setMap(map);
        //Invoker返回的对象是Object,类型需要强转
        Map<String, String> reflectorMap = (Map<String, String>) mapGet.invoke(testClassA, new Object[0]);
        for (String k : reflectorMap.keySet()) {
            String v = reflectorMap.get(k);
            System.out.println("key: " + k + ",value: " + v );
        }
        
        //测试SubClassA
    }
}
