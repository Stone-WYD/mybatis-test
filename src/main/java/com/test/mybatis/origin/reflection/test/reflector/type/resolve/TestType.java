package com.test.mybatis.origin.reflection.test.reflector.type.resolve;

import org.apache.ibatis.reflection.TypeParameterResolver;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TestType {

    private SubClassA<String> sa;

    @Override
    public String toString() {
        if (sa != null) return sa.toString();
        return "sa 没有内容！";
    }

    public TestType() {
    }

    public static void main(String[] args) throws Exception{
        //获取ClassA中的map成员变量的field
        Field f = ClassA.class.getDeclaredField("map");
        //map的Type的实现类是ParameterizedType
        System.out.println(f.getGenericType());
        System.out.println(f.getGenericType() instanceof ParameterizedType);
        //resolveFieldType: 参数1:Field field  参数2:Type srcType
        Type type = TypeParameterResolver.resolveFieldType(f,
                //ParameterizedType三个参数:rawType(原始类型)，actualTypeArguments(泛型实际类型)，ownerType(所属类型)
                ParameterizedTypeImpl.make(SubClassA.class, new Type[]{Long.class}, TestType.class));
        //上一行第二个参数的另一种写法：TestType.class.getDeclaredField("sa").getGenericType()
        System.out.println(type.getClass());

        ParameterizedType p = (ParameterizedType) type;
        System.out.println(p.getRawType());
        System.out.println(p.getOwnerType());
        for (Type t : p.getActualTypeArguments()) {
            System.out.println(t);
        }
        /*
        * 1.在这个测试例子中，f来自父类ClassA，传入resolveFieldType()方法的srcType却是SubClassA对应的ParameterizedType
        * 2.在resolveFieldType()内部的resolveType()方法中，
        *   传参1(type): f的type,ParameterizedType类型的Map<K,V>
        *   传参2(srcType): SubClassA对应的ParameterizedType: SubClassA<Long>
        *   传参3(declaringClass): ClassA的class
        * 3.resolveFieldType()方法的返回结果为ParameterizedType(TypeParameterResolver内部ParameterizedType的一个实现类)，对于该返回结果:
        *   RawType: Map
        *   OwnerType: null
        *   ActualTypeArguments: Long,Long
        * */

    }
}
