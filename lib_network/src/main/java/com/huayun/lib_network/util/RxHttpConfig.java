package com.huayun.lib_network.util;


import android.os.Environment;

import com.huayun.lib_network.okhttpImpl.OkHttpMag;
import com.huayun.lib_tools.util.AppGlobalUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import rxhttp.RxHttpPlugins;
import rxhttp.wrapper.annotation.DefaultDomain;
import rxhttp.wrapper.cahce.CacheMode;

/**
 * 网络框架配置信息
 */
public class RxHttpConfig {
    private static volatile RxHttpConfig instance;
    //设置为默认域名
    @DefaultDomain()
    public static String baseUrl;
    //错误状态拦截码
    private List<Integer> errorCode;
    //缓存路径
    private String cachePath;
    //请求失败返回标识
    private String REQUEST_ERROR_TAG;
    //请求头
    private Map<String, String> header;
    //是否是Debug模式
    private boolean isDebug;
    //请求返回日志是否打印请求参数
    private boolean resultLogShowParam;
    //OkHttpClient
    private OkHttpClient okHttpClient;
    public static RxHttpConfig getInstance() {
        if (instance == null) {
            synchronized (RxHttpConfig.class) {
                if (instance == null) {
                    instance = new RxHttpConfig();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化默认配置
     */
    private RxHttpConfig(){
        errorCode =new ArrayList<>();
        header=new HashMap<>();
        okHttpClient=OkHttpMag.getInstance().getOkHttpClient();
        REQUEST_ERROR_TAG="APP--REQUEST--SERVER--ERROR";
        cachePath=Environment.getExternalStorageDirectory().getPath()
                + "/Android/data/" + AppGlobalUtils.getApplication().getPackageName() + "/cache/";
    }

    /**
     * 初始化RxHttp 配置OkHttpClient
     * 缓存策略：
     * 存储：内部缓存 同app卸载销毁
     * 缓存大小：最大值10M，超出后根据LRU算法清理
     * 缓存模式：先请求网络，请求成功，写入缓存并返回；否则读取缓存
     * 缓存生命周期：5天
     */
    public void init() {
        RxHttpPlugins.init(okHttpClient)
                .setDebug(isDebug)
                .setCache(new File(cachePath), 10 * 1024 * 1024,
                        CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE, ((60 * 1000) * 60 * 24) * 5);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public RxHttpConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public List<Integer> getErrorCode() {
        return errorCode;
    }

    public RxHttpConfig setErrorCode(List<Integer> errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getCachePath() {
        return cachePath;
    }

    public RxHttpConfig setCachePath(String cachePath) {
        this.cachePath = cachePath;
        return this;
    }

    public String getRequestErrorTag() {
        return REQUEST_ERROR_TAG;
    }

    public RxHttpConfig setRequestErrorTag(String REQUEST_ERROR_TAG) {
        this.REQUEST_ERROR_TAG = REQUEST_ERROR_TAG;
        return this;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public RxHttpConfig setHeader(Map<String, String> header) {
        this.header = header;
        return this;
    }

    public boolean getIsDebug() {
        return isDebug;
    }

    public RxHttpConfig setIsDebug(boolean debug) {
        isDebug = debug;
        return this;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public RxHttpConfig setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        return this;
    }

    public boolean getResultLogShowParam() {
        return resultLogShowParam;
    }

    public RxHttpConfig setResultLogShowParam(boolean resultLogShowParam) {
        this.resultLogShowParam = resultLogShowParam;
        return this;
    }
}
