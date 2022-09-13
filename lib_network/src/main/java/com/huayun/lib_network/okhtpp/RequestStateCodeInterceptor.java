package com.huayun.lib_network.okhtpp;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 错误状态码拦截器
 */
public class RequestStateCodeInterceptor implements Interceptor {
    private Map<String, Integer> map = new HashMap<>();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
//        Log.e("=_=","response-->"+response.code()+"\t"+response.message());
        stateCode(response.code());
        return response;
    }

    private void stateCode(int code) {
        switch (code) {
            case 401://token失效
            case 666://需要更新
                map.put("netStateCode", code);
                EventBus.getDefault().post(map);
                break;
        }
    }


}