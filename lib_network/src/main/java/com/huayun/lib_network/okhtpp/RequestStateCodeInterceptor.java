package com.huayun.lib_network.okhtpp;

import android.os.Handler;
import android.os.Looper;

import com.huayun.lib_network.util.RxHttpConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 错误状态码拦截器
 */
public class RequestStateCodeInterceptor implements Interceptor {

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
//        Log.e("=_=","response-->"+response.code()+"\t"+response.message());
        if (RxHttpConfig.getInstance().getErrorCode().contains(response.code())) {
            if (RxHttpConfig.getInstance().getNetErrorListener() != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        RxHttpConfig.getInstance().getNetErrorListener().netError(response.code());
                    }
                });
            }
        }
        return response;
    }


}
