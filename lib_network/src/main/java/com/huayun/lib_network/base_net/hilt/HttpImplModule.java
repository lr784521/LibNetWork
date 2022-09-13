package com.huayun.lib_network.base_net.hilt;

import com.huayun.lib_network.annotation.BindRxHttp;
import com.huayun.lib_network.base_net.call.IHttp;
import com.huayun.lib_network.rxhttp.IHttpImplToRxHttp;
import javax.inject.Singleton;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

/**
 * 接口注入实现隔离层
 */
@InstallIn(SingletonComponent.class)
@Module
public abstract class HttpImplModule {

    //注入RxHttp实现
    @BindRxHttp
    @Singleton
    @Binds
    abstract IHttp bindRxHttp(IHttpImplToRxHttp http);
}
