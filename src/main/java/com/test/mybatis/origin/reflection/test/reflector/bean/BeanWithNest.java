package com.test.mybatis.origin.reflection.test.reflector.bean;

import lombok.Data;

/**
 * @author xh
 * @date 2024-08-28
 * @Description:
 */
@Data
public class BeanWithNest {
    private String name;

    private Integer age;

    private Nest hobby;

}
