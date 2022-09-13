package com.huayun.lib_network.base_net.call;

import java.util.List;
import java.util.Map;

/**
 * 单个网络请求回调
 */
public interface NetSingleCallBack {

    /**
     * 请求成功回调--单个请求
     *
     * @param response 返回字符串结果
     */
    void onSuccess(String response);

    /**
     * 请求失败回调
     *
     * @param error 返回类型 一般为Throwable 类型
     */
    void onError(Throwable error);
}
