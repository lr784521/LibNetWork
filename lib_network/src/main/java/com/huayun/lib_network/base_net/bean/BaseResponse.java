package com.huayun.lib_network.base_net.bean;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.huayun.lib_network.base_net.ServerCode;
import com.huayun.lib_tools.util.GsonUtil;

/**
 * 后台返回Json封装处理
 *
 * @param <T>
 */
public class BaseResponse<T> {

    public int code;
    @SerializedName(value = "msg", alternate = {"message"})
    public String msg;
    @SerializedName(value = "data", alternate = {"result"})
    public T data;

    public BaseResponse() {
    }

    public BaseResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public <T> T get(Class<T> clazz) {
        return GsonUtil.fromObject(data, clazz);
    }

    public <T> T get(TypeToken<T> token) {
        return GsonUtil.fromObject(data, token);
    }

    public <T> T getDecrypt(Class<T> clazz) {
        if (data == null) {
            return null;
        }
        return get(clazz);
    }

    public <T> T getDecrypt(TypeToken<T> token) {
        if (data == null) {
            return null;
        }
        return get(token);
    }

    public boolean isOk() {
        if (code == ServerCode.CODE_SUCCESS) return true;
        return false;
    }

    public String getData() {
        return String.valueOf(data);
    }

    public T getDataClass() {
        return data;
    }


    public String toString() {
        return GsonUtil.toJson(this);
    }

    /**
     * 由字符串构造
     *
     * @param value
     * @return
     */
    public static BaseResponse fromString(String value) {
        if (value == null) {
            return null;
        }
        BaseResponse result = GsonUtil.gsonToBean(value, BaseResponse.class);
        return result;
    }

    public static BaseResponse fail() {
        BaseResponse result = new BaseResponse(1, "网络连接不可用，请稍后重试", null);
        return result;
    }
}
