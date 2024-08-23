package com.test.mybatis.origin.reflection.test.reflector;

import com.test.mybatis.origin.reflection.test.reflector.bean.param.QueryCondition;
import com.test.mybatis.origin.reflection.test.reflector.bean.param.XsParamCommon;
import com.test.mybatis.origin.reflection.test.reflector.bean.param.XsParkAreaQueryInfo;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.ReflectorFactory;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Stone
 * @date 2024-08-23
 * @Description: 参数类型转换工具类
 */
public class ParamsConvertUtil {

    // 默认是缓存内容的
    private static ReflectorFactory reflectorFactory = new DefaultReflectorFactory();

    public static String convertParams1(Object o) {
        Reflector reflector = reflectorFactory.findForClass(o.getClass());

        return "";
    }

    public static void main(String[] args) {
        // 准备参数
        XsParamCommon<XsParkAreaQueryInfo> param = new XsParamCommon<>();
        param.setParkKey("parkey");
        param.setAppId("appId");
        XsParkAreaQueryInfo queryInfo = new XsParkAreaQueryInfo();
        queryInfo.setPageIndex(1L);
        queryInfo.setPageSize(100L);
        QueryCondition condition = new QueryCondition();
        condition.setField("field");
        condition.setValue("value");
        queryInfo.setQueryCondition(Collections.singletonList(condition));
        param.setData(queryInfo);

        String result = convertParams1(param);
        System.out.println(result);
    }

}
