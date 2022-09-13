package com.huayun.lib_network.okhtpp;


import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import rxhttp.wrapper.utils.GsonUtil;
import rxhttp.wrapper.utils.LogUtil;

/**
 * 请求头拦截器
 */
public class RequestHeaderInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        RequestBody requestBody = request.body();
//        LogUtil.log("请求头--->"+ GsonUtil.toJson(request.headers()));
        Request.Builder builder = request.newBuilder();
        return chain.proceed(builder.build());
    }

}