package com.test.mybatis.origin.reflection.test.reflector;

import cn.hutool.crypto.SecureUtil;
import com.test.mybatis.origin.reflection.test.reflector.bean.param.QueryCondition;
import com.test.mybatis.origin.reflection.test.reflector.bean.param.XsParamCommon;
import com.test.mybatis.origin.reflection.test.reflector.bean.param.XsParkAreaQueryInfo;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.invoker.Invoker;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static com.test.mybatis.origin.reflection.test.reflector.ParamsConvertAssistUtil.isPrimitiveOrWrapper;

/**
 * @author Stone
 * @date 2024-08-23
 * @Description: 参数类型转换工具类
 */
public class ParamsConvertUtil {

    // 默认是缓存内容的
    private static final ReflectorFactory reflectorFactory = new DefaultReflectorFactory();

    public static String convertParamsToStr(Object o) throws InvocationTargetException, IllegalAccessException {
        // 注意：Data为Null时也要参与签名，其他字段为Null不参与签名
        Reflector reflector = reflectorFactory.findForClass(o.getClass());
        String[] getNames = reflector.getGetablePropertyNames();
        // 1. 根据 asc 码进行升序排列
        Arrays.sort(getNames, String::compareTo);
        // 2. 获取字符串 value，进行结果的拼装
        StringBuilder resultStr = new StringBuilder();
        for (String name : getNames) {
            Invoker getInvoker = reflector.getGetInvoker(name);
            // 如果是 Data 字段
            Object value = getInvoker.invoke(o, null);
            if (name.equals("data")) {
                resultStr.append(dataConvert(value));
                continue;
            }
            // 其他字段不会包含对象属性，这里直接拼接
            if (value != null) {
                resultStr.append(name).append("=").append(value).append("&");
            }
        }
        return resultStr.substring(0, resultStr.length() - 1);
    }

    private static String dataConvert(Object data) throws InvocationTargetException, IllegalAccessException {
        // 下面逻辑中没有考虑数组的使用，所以属性中不要包含数组，请使用集合替代
        StringBuilder resultStr = new StringBuilder("data=");
        if (data == null) {
            // todo data 为 null 时如何处理，是否需要加密？
            return resultStr.append(SecureUtil.md5("null")).append("&").toString();
        }
        StringBuilder dataStr = new StringBuilder();
        if ( data instanceof Collection ) {
            // 如果是集合类型
            Collection<?> collection = (Collection<?>) data;
            for (Object object : collection) {
                dataStr.append(doDataConvert(object));
            }
        } else {
            dataStr.append(doDataConvert(data));
        }
        System.out.println(dataStr);
        return resultStr.append(SecureUtil.md5(dataStr.substring(0, dataStr.length() - 1))).append("&").toString();
    }

    private static String doDataConvert(Object data) throws InvocationTargetException, IllegalAccessException {
        // 1. 获取反射
        Reflector reflector = reflectorFactory.findForClass(data.getClass());
        String[] getNames = reflector.getGetablePropertyNames();
        Arrays.sort(getNames, String::compareTo);
        StringBuilder resultStr = new StringBuilder();
        for (String name : getNames) {
            // 2. 获取属性，判断是否是对象类型
            resultStr.append(name).append("=");
            Invoker getInvoker = reflector.getGetInvoker(name);
            Object value = getInvoker.invoke(data, null);
            if (value == null) {
                // 2.1 空处理
                resultStr.append("&");
                continue;
            }
            if (isPrimitiveOrWrapper(value.getClass()) || String.class == value.getClass()) {
                // 2.2. 是基本类型，直接拼接
                resultStr.append(value).append("&");
            }  else {
                // 2.3. 判断是否为集合类型
                if (value instanceof Collection<?>) {
                    // 2.3.1. 遍历后再处理
                    Collection<?> collection = (Collection<?>) value;
                    if (collection.isEmpty()) {
                        resultStr.append("&");
                    } else {
                        for (Object c : collection) {
                            resultStr.append(doDataConvert(c));
                        }
                    }
                } else {
                    // 2.3.2. 不是，则递归调用
                    resultStr.append(doDataConvert(value)).append("&");
                }
            }
        }
        // 3. 返回结果
        return resultStr.toString();
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
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

        String result = convertParamsToStr(param);
        System.out.println(result);
    }

}
