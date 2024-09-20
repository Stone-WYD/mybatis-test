package com.test.mybatis.origin.reflection;

import lombok.Data;

import java.util.List;

/**
 * @author xh
 * @date 2024-09-19
 * @Description:
 */
@Data
public class Bean {

    private String name;

    private Integer age;

    private List<String> hobbyList;


    public Bean(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
