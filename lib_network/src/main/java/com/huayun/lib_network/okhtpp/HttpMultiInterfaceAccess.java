package com.huayun.lib_network.okhtpp;

import android.content.Context;

import com.huayun.lib_network.R;
import com.huayun.lib_network.base_net.ServerCode;
import com.huayun.lib_network.base_net.bean.BaseResponse;
import com.huayun.lib_network.base_net.NetOnErrorImpl;
import com.huayun.lib_tools.util.AppGlobalUtils;
import com.huayun.lib_tools.util.log.LogUtil;

import java.util.List;
import rx.Subscriber;

/**
 * 多接口合并访问
 */
public abstract class HttpMultiInterfaceAccess extends Subscriber<List<BaseResponse>> {
    private Context context= AppGlobalUtils.getApplication();//全局上下文

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        NetOnErrorImpl.onError(context,e, new NetOnErrorImpl.CallBack() {
            @Override
            public void result(String logMsg, String tipsMsg, int code) {
                LogUtil.xLoge("NetError-->" + logMsg);
                onError(tipsMsg, code);
            }
        });
    }

    @Override
    public void onNext(List<BaseResponse> baseResponses) {
        if (isUnsubscribed()) {
            return;
        }
        try {
            if (baseResponses == null) {
                onError(context.getString(R.string.net_error), ServerCode.CODE_ERROR_CODE);
            } else {
                for (BaseResponse baseResponse : baseResponses) {
                    if (!baseResponse.isOk()) {
                        onError(baseResponse.msg,baseResponse.code);
                        return;
                    }
                }
                success(baseResponses);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void success(List<BaseResponse> response);

    public abstract void onError(String msg, int code);

}
