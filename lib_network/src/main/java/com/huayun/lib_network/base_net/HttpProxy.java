package com.huayun.lib_network.base_net;

import androidx.lifecycle.LifecycleOwner;

import com.huayun.lib_network.base_net.call.IHttp;
import com.huayun.lib_network.base_net.call.NetFileDownloadCallBack;
import com.huayun.lib_network.base_net.call.NetFileUploadCallBack;
import com.huayun.lib_network.base_net.call.NetMergeCallBack;
import com.huayun.lib_network.base_net.call.NetSingleCallBack;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 网络请求代理
 * 当前Lib以依赖库形式向外提供时如果依赖方项目未使用Hilt依赖注入
 * 或对Hilt依赖注入不了解时  可使用代理方式初始化
 * 初始化方法：建议在Application中初始化
 * HttpProxy.getInstance().init(new IHttpImplToOkHttp());
 * 使用方法:
 * HttpProxy.getInstance().httpGet("url");
 */
public class HttpProxy implements IHttp {
    private static HttpProxy instance;
    private IHttp iHttp;

    public static HttpProxy getInstance() {
        if (instance == null) {
            synchronized (HttpProxy.class) {
                if (instance == null) {
                    instance = new HttpProxy();
                }
            }
        }
        return instance;
    }

    public void init(IHttp iHttp) {
        this.iHttp = iHttp;
    }


    @Override
    public Disposable httpGet(String url, NetSingleCallBack callBack) {
        return iHttp.httpGet(url, callBack);
    }

    @Override
    public void httpGet(LifecycleOwner owner, String url, NetSingleCallBack callBack) {
        iHttp.httpGet(owner, url, callBack);
    }

    @Override
    public Disposable httpGet(String url, Map<String, Object> params, NetSingleCallBack callBack) {
        return iHttp.httpGet(url, params, callBack);
    }

    @Override
    public void httpGet(LifecycleOwner owner, String url, Map<String, Object> params, NetSingleCallBack callBack) {
        iHttp.httpGet(owner, url, params, callBack);
    }

    @Override
    public Observable httpGet(String url) {
        return iHttp.httpGet(url);
    }

    @Override
    public Observable httpGet(String url, Map<String, Object> params) {
        return iHttp.httpGet(url, params);
    }

    @Override
    public Disposable httpPost(String url, NetSingleCallBack callBack) {
        return iHttp.httpPost(url, callBack);
    }

    @Override
    public void httpPost(LifecycleOwner owner, String url, NetSingleCallBack callBack) {
        iHttp.httpPost(owner, url, callBack);
    }

    @Override
    public Disposable httpPost(String url, Map<String, Object> params, NetSingleCallBack callBack) {
        return iHttp.httpPost(url, params, callBack);
    }

    @Override
    public void httpPost(LifecycleOwner owner, String url, Map<String, Object> params, NetSingleCallBack callBack) {
        iHttp.httpPost(owner, url, params, callBack);
    }

    @Override
    public Observable httpPost(String url) {
        return iHttp.httpPost(url);
    }

    @Override
    public Observable httpPost(String url, Map<String, Object> params) {
        return iHttp.httpPost(url, params);
    }

    @Override
    public Disposable httpPut(String url, NetSingleCallBack callBack) {
        return iHttp.httpPut(url, callBack);
    }

    @Override
    public void httpPut(LifecycleOwner owner, String url, NetSingleCallBack callBack) {
        iHttp.httpPut(owner, url, callBack);
    }

    @Override
    public Disposable httpPut(String url, Map<String, Object> params, NetSingleCallBack callBack) {
        return iHttp.httpPut(url, params, callBack);
    }

    @Override
    public void httpPut(LifecycleOwner owner, String url, Map<String, Object> params, NetSingleCallBack callBack) {
        iHttp.httpPut(owner, url, params, callBack);
    }

    @Override
    public Observable httpPut(String url) {
        return iHttp.httpPut(url);
    }

    @Override
    public Observable httpPut(String url, Map<String, Object> params) {
        return iHttp.httpPut(url, params);
    }

    @Override
    public Disposable httpDel(String url, NetSingleCallBack callBack) {
        return iHttp.httpDel(url, callBack);
    }

    @Override
    public void httpDel(LifecycleOwner owner, String url, NetSingleCallBack callBack) {
        iHttp.httpDel(owner, url, callBack);
    }

    @Override
    public Disposable httpDel(String url, Map<String, Object> params, NetSingleCallBack callBack) {
        return iHttp.httpDel(url, params, callBack);
    }

    @Override
    public void httpDel(LifecycleOwner owner, String url, Map<String, Object> params, NetSingleCallBack callBack) {
        iHttp.httpDel(owner, url, params, callBack);
    }

    @Override
    public Observable httpDel(String url) {
        return iHttp.httpDel(url);
    }

    @Override
    public Observable httpDel(String url, Map<String, Object> params) {
        return iHttp.httpDel(url, params);
    }

    @Override
    public Disposable httpFileUpload(String url, Map<String, Object> params, NetFileUploadCallBack uploadCallBack, NetSingleCallBack callBack) {
        return iHttp.httpFileUpload(url, params, uploadCallBack, callBack);
    }

    @Override
    public void httpFileUpload(LifecycleOwner owner, String url, Map<String, Object> files, NetFileUploadCallBack uploadCallBack, NetSingleCallBack callBack) {
        iHttp.httpFileUpload(owner, url, files, uploadCallBack, callBack);
    }

    @Override
    public void httpFileUpload(LifecycleOwner owner, String url, String fileKey, List files, NetFileUploadCallBack uploadCallBack, NetSingleCallBack callBack) {
        iHttp.httpFileUpload(owner, url, fileKey, files, uploadCallBack, callBack);
    }

    @Override
    public void httpFileUpload(LifecycleOwner owner, String url, Map<String, Object> files, Map<String, Object> params, NetFileUploadCallBack uploadCallBack, NetSingleCallBack callBack) {
        iHttp.httpFileUpload(owner, url, files, params, uploadCallBack, callBack);
    }

    @Override
    public Disposable httpFileDownload(String url, String destPath, boolean breakpoint, NetFileDownloadCallBack downloadCallBack, NetSingleCallBack callBack) {
        return iHttp.httpFileDownload(url, destPath, breakpoint, downloadCallBack, callBack);
    }

    @Override
    public void httpFileDownload(LifecycleOwner owner, String url, String destPath, boolean breakpoint, NetFileDownloadCallBack downloadCallBack, NetSingleCallBack callBack) {
        iHttp.httpFileDownload(owner, url, destPath, breakpoint, downloadCallBack, callBack);
    }

    @Override
    public void httpMergeRequest(LifecycleOwner owner, NetMergeCallBack callBack, Map<String, Object> reqList) {
        iHttp.httpMergeRequest(owner, callBack, reqList);
    }
}
