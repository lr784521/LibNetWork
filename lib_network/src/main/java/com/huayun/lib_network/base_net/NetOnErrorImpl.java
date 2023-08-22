package com.huayun.lib_network.base_net;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.huayun.lib_network.R;
import com.huayun.lib_network.util.NetworkUtils;
import com.huayun.lib_tools.util.AppGlobalUtils;
import com.huayun.lib_tools.util.log.LogUtil;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/**
 * 处理网络请求异常
 */
public class NetOnErrorImpl {

    public static void onError(Context context, Throwable e, CallBack callBack) {
        String logMsg = "";
        String tipsMsg = "";
        LogUtil.xLoge("请求错误Throwable==>" + e.toString());
        if (!NetworkUtils.isNetworkAvailable(AppGlobalUtils.getApplication())) {
            callBack.result(e.toString(), "请检查网络是否连接", ServerCode.CODE_ERROR_CODE);
            //网络连接断开
            return;
        }
        if (e instanceof UnknownHostException) {
            logMsg = "域名无法访问：UnknownHostException";
//            context.getString(R.string.net_error)
            //域名无法访问 切换Base域名
//            if(!e.toString().contains("dev5coappapi.chaindown.com") && !e.toString().contains("stagingredpacket.chaindown.com")
//                    && !e.toString().contains("qaws3.chaindown.com")){
//                actionServerAddress();
//            }
        } else if (e instanceof SocketException) {
            logMsg = "服务器连接失败：SocketException";
            //服务器连接失败 切换Base域名
//            actionServerAddress();
//            onHandleError(ChainUpApp.appContext.getString(R.string.warn_net_exception));
        } else if (e instanceof SocketTimeoutException) {
            logMsg = "服务器连接超时：SocketTimeoutException";
            //连接超时  可以根据规则记录多少次超时之后切换备用域名
//            onHandleError(ChainUpApp.appContext.getString(R.string.warn_request_timeout));
        } else if (e instanceof TimeoutException) {
            logMsg = "接口请求超时：TimeoutException";
            //请求超时  可以根据规则记录多少次超时之后切换备用域名
            //提示用户检查网络状态
//            actionServerAddress();
//            onHandleError(ChainUpApp.appContext.getString(R.string.warn_request_timeout));
        } else if (e instanceof JsonParseException || e instanceof ClassCastException) {
            //数据解析失败
            logMsg = "数据解析失败：JsonParseException";
            tipsMsg = context.getString(R.string.net_data_error);
        } else {
            //未知错误
            logMsg = context.getString(R.string.unknown_mistake);
            tipsMsg = context.getString(R.string.unknown_mistake);
        }
        callBack.result(logMsg, tipsMsg, ServerCode.CODE_ERROR_CODE);
    }

    public interface CallBack {
        void result(String logMsg, String tipsMsg, int code);
    }
}
