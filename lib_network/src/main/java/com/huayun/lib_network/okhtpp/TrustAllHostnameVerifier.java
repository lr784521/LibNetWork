package com.huayun.lib_network.okhtpp;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * 设置用于确认响应证书适用于所请求主机名的验证程序
 * HTTPS连接。
 * <p>如果未设置，将使用默认主机名验证程序。
 */
public class TrustAllHostnameVerifier implements HostnameVerifier {

    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }

}
