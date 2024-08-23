
package com.test.mybatis.origin.reflection.test.reflector.bean.param;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class QueryCondition {
    /* 字段顺序要按照 ASCII 码升序排列*/
    @SerializedName("Field")
    private String field;
    @SerializedName("Operator")
    private Long operator;
    @SerializedName("Value")
    private String value;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
