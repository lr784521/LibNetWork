package com.huayun.lib_network.base_net;

import android.content.Context;

import com.huayun.lib_network.base_net.bean.BaseResponse;
import com.huayun.lib_network.base_net.call.NetMergeCallBack;
import com.huayun.lib_tools.util.AppGlobalUtils;
import com.huayun.lib_tools.util.GsonUtil;
import com.huayun.lib_tools.util.log.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 多网络请求--顶层网络请求接口回调实现
 */
public abstract class NetMergeCallBackImpl implements NetMergeCallBack {

    private Context context = AppGlobalUtils.getApplication();//全局上下文

    @Override
    public void onSuccess(List<Object> resList) {
        boolean isNoJson = false;
        //1.把String转成javaBean对象交给用户
        List<Object> repList = new ArrayList<>();
        for (Object item : resList) {
            if (GsonUtil.isJson(GsonUtil.toJson(item))) {
                BaseResponse t = GsonUtil.gsonToBean(item.toString(), BaseResponse.class);
                repList.add(t.data);
            } else {
                isNoJson = true;
                repList.add(item);
            }
        }
        onComplete(repList, isNoJson);
    }

    @Override
    public void onError(Throwable e) {
        NetOnErrorImpl.onError(context, e, new NetOnErrorImpl.CallBack() {
            @Override
            public void result(String logMsg, String tipsMsg, int code) {
                LogUtil.xLoge("NetError-->" + logMsg);
                onError(tipsMsg, code);
            }
        });
    }


    /**
     * 单个接口请求成功回调
     *
     * @param resList  请求完成所有结果按顺序存在列表中
     * @param isNoJson 是否存在非Json返回结果
     */
    public abstract void onComplete(List<Object> resList, boolean isNoJson);

    /**
     * 失败回调
     *
     * @param msg  提示信息
     * @param code 状态码
     */
    public abstract void onError(String msg, int code);


}
