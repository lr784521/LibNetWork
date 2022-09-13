package com.huayun.lib_network.base_net.call;

import java.util.List;

/**
 * 多个网络请求回调
 */
public interface NetMergeCallBack {

    /**
     * 请求成功回调--合并请求 按请求顺序依次执行
     * @param resList 请求完成所有结果按顺序存在列表中
     */
    void onSuccess(List<Object> resList);

    /**
     * 请求失败回调
     *
     * @param error 返回类型 一般为Throwable 类型
     */
    void onError(Throwable error);

}
