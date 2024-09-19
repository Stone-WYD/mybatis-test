package com.test.mybatis.origin.reflection.class_loader;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.ResolverUtil;

/**
 * @author xh
 * @date 2024-09-02
 * @Description:
 */
public class ResolverUtilTest {

    public static void main(String[] args) {
        ResolverUtil<Object> resolverUtil = new ResolverUtil<>();
        ResolverUtil<Object> annotatedResolverUtil = resolverUtil.findAnnotated(Mapper.class, "com.test.mybatis.use.proxy");
        for (Class<?> aClass : annotatedResolverUtil.getClasses()) {
            System.out.println(aClass);
        }

    }
}
