
package com.test.mybatis.origin.reflection.test.reflector.bean.param;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class XsParamCommon<T> {
    /* 字段顺序要按照 ASCII 码升序排列*/
    @SerializedName("AppId")
    private String appId;
    @SerializedName("Data")
    private T data;
    @SerializedName("Nonce")
    private String nonce;
    @SerializedName("ParkKey")
    private String parkKey;
    @SerializedName("Sign")
    private String sign;
    @SerializedName("TimeStamp")
    private String timeStamp;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getParkKey() {
        return parkKey;
    }

    public void setParkKey(String parkKey) {
        this.parkKey = parkKey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}
