package com.huayun.lib_network.okhtpp;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 此接口的实例管理哪些X509证书
 * 可用于验证安全的远程端
 * 插座。 决策可能基于可信证书
 * 当局，证书撤销清单，在线
 * 状态检查或其他方式。
 *
 * @since 1.4
 */
public class TrustAllCerts implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
