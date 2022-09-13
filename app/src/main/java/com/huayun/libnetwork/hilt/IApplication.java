package com.huayun.libnetwork.hilt;

import com.huayun.lib_network.base_net.call.IHttp;

/**
 * Application接口用于 子工程访问主工程Application内容
 */
public interface IApplication {
    //提供网络访问能力
    IHttp getIHttp();



}
