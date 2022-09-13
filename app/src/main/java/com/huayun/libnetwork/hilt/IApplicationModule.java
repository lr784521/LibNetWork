package com.huayun.libnetwork.hilt;


import com.huayun.libnetwork.MyApplication;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

/**
 * 注入Application接口用于 子工程访问主工程Application内容
 */
@InstallIn(SingletonComponent.class)
@Module
public abstract class IApplicationModule {

    /**
     * 绑定网络请求框架
     *
     * @param http 实现类
     * @return
     */
    @Singleton
    @Binds
    abstract IApplication bindApp(MyApplication http);

}
