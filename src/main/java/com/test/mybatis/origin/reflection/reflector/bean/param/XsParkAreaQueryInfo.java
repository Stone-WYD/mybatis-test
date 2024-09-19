
package com.test.mybatis.origin.reflection.reflector.bean.param;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class XsParkAreaQueryInfo {
    /* 字段顺序要按照 ASCII 码升序排列*/
    @SerializedName("OrderBy")
    private String orderBy;
    @SerializedName("OrderType")
    private Long orderType;
    @SerializedName("PageIndex")
    private Long pageIndex;
    @SerializedName("PageSize")
    private Long pageSize;
    @SerializedName("QueryCondition")
    private List<QueryCondition> queryCondition;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Long getOrderType() {
        return orderType;
    }

    public void setOrderType(Long orderType) {
        this.orderType = orderType;
    }

    public Long getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public List<QueryCondition> getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(List<QueryCondition> queryCondition) {
        this.queryCondition = queryCondition;
    }

}
