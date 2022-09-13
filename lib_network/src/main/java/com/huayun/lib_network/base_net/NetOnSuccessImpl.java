package com.huayun.lib_network.base_net;

import com.huayun.lib_network.base_net.bean.BaseResponse;

/**
 * 请求成功回调处理
 */
public class NetOnSuccessImpl {


    /**
     * 成功请求处理
     * @param response  数据
     * @param callBack  回调
     */
    public static void onSuccess(BaseResponse response, CallBack callBack) {
        switch (response.code) {
            case ServerCode.CODE_SUCCESS://成功
                callBack.onSuccess(response);
                break;
            default:
                callBack.onError(response.msg,response.code);
                break;
        }
    }

    interface CallBack {
        //成功--状态码处理
        void onSuccess(BaseResponse response);

        //失败--状态码处理
        void onError(String tipsMsg, int code);
    }
}
