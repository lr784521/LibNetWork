package com.huayun.lib_network.okhttpImpl;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.huayun.lib_network.okhtpp.RequestHeaderInterceptor;
import com.huayun.lib_network.okhtpp.RequestLogInterceptor;
import com.huayun.lib_network.okhtpp.RequestStateCodeInterceptor;
import com.huayun.lib_network.okhtpp.TrustAllHostnameVerifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rxhttp.wrapper.ssl.HttpsUtils;

/**
 * OkHttp配置管理
 */
public class OkHttpMag {

    public final static long DEFAULT_TIMEOUT = 30;//默认超时时间长30S
    private static final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();//保存cookie
    private static volatile OkHttpMag instance;
    private OkHttpClient client;
    private Gson gson = new Gson();

    public static OkHttpMag getInstance() {
        if (instance == null) {
            synchronized (OkHttpMag.class) {
                if (instance == null) {
                    instance = new OkHttpMag();
                }
            }
        }
        return instance;
    }

    //获取OkHttpClient
    public OkHttpClient getOkHttpClient() {
        return client;
    }


    //初始化OkHttpClient
    private OkHttpMag() {
        //设置可访问所有的https网站
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        client = new OkHttpClient.Builder()
                .addInterceptor(new RequestLogInterceptor())
                .addInterceptor(new RequestHeaderInterceptor())
                .addInterceptor(new RequestStateCodeInterceptor())
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager) //添加信任证书
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request request;
                        request = chain.request().newBuilder()
                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                .build();
                        return chain.proceed(request);
                    }
                }).cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put("sessionid", cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get("sessionid");
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .build();
    }
}
