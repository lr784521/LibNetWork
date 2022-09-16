package com.huayun.libnetwork;

import android.app.Application;

import com.huayun.lib_network.annotation.BindRxHttp;
import com.huayun.lib_network.base_net.call.IHttp;
import com.huayun.lib_network.rxhttp.RxHttpMag;
import com.huayun.lib_tools.util.log.LogUtil;
import com.huayun.libnetwork.hilt.IApplication;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApplication extends Application implements IApplication {
    //注入网络访问接口  一句话切换底层实现框架
    @BindRxHttp
    @Inject
    IHttp http;

    @Inject
    public MyApplication(){}

    @Override
    public IHttp getIHttp() {
        return http;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.init(true,"=_=");
        RxHttpMag.getInstance().init("https://app-api.yidaz.cn/yd-api-member/",false,"");
    }
}
