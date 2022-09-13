package com.huayun.lib_network.rxhttp;

import androidx.lifecycle.LifecycleOwner;

import com.huayun.lib_network.base_net.call.IHttp;
import com.huayun.lib_network.base_net.call.NetSingleCallBack;
import com.huayun.lib_network.base_net.call.NetFileDownloadCallBack;
import com.huayun.lib_network.base_net.call.NetFileUploadCallBack;
import com.huayun.lib_network.base_net.call.NetMergeCallBack;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 * RxHttp业务实现
 * https://github.com/liujingxing/rxhttp/blob/master/README_zh.md
 */
public class IHttpImplToRxHttp implements IHttp {

    @Inject
    public IHttpImplToRxHttp() {
    }

    /**
     * GET请求
     * @param url      接口名
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    @Override
    public Disposable httpGet(String url, NetSingleCallBack callBack) {
        return RxHttpMag.getInstance().httpGet(url, callBack);
    }

    /**
     * GET请求
     *
     * @param owner    监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url      接口名
     * @param callBack 请求结果回调
     */
    @Override
    public void httpGet(LifecycleOwner owner, String url, NetSingleCallBack callBack) {
        RxHttpMag.getInstance().httpGet(owner, url, callBack);
    }

    /**
     * GET请求
     *
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    @Override
    public Disposable httpGet(String url, Map<String, Object> params, NetSingleCallBack callBack) {
        return RxHttpMag.getInstance().httpGet(url, params, callBack);
    }

    /**
     * GET请求
     *
     * @param owner    监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     */
    @Override
    public void httpGet(LifecycleOwner owner, String url, Map<String, Object> params, NetSingleCallBack callBack) {
        RxHttpMag.getInstance().httpGet(owner, url, params, callBack);
    }

    /**
     * GET请求--用于多请求合并
     *
     * @param url 接口名
     * @return 请求观察对象
     */
    @Override
    public Observable httpGet(String url) {
        return RxHttpMag.getInstance().httpGet(url);
    }

    /**
     * GET请求--用于多请求合并
     *
     * @param url    接口名
     * @param params 参数
     * @return 请求观察对象
     */
    @Override
    public Observable httpGet(String url, Map<String, Object> params) {
        return RxHttpMag.getInstance().httpGet(url, params);
    }

    /**
     * POST请求
     *
     * @param url      接口名
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    @Override
    public Disposable httpPost(String url, NetSingleCallBack callBack) {
        return RxHttpMag.getInstance().httpPost(url, callBack);
    }

    /**
     * POST请求
     *
     * @param owner    监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url      接口名
     * @param callBack 请求结果回调
     */
    @Override
    public void httpPost(LifecycleOwner owner, String url, NetSingleCallBack callBack) {
        RxHttpMag.getInstance().httpPost(owner, url, callBack);
    }

    /**
     * POST请求
     *
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    @Override
    public Disposable httpPost(String url, Map<String, Object> params, NetSingleCallBack callBack) {
        return RxHttpMag.getInstance().httpPost(url, params, callBack);
    }


    /**
     * POST请求
     *
     * @param owner    监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     */
    @Override
    public void httpPost(LifecycleOwner owner, String url, Map<String, Object> params, NetSingleCallBack callBack) {
        RxHttpMag.getInstance().httpPost(owner, url, params, callBack);
    }

    /**
     * POST请求--用于多请求合并
     *
     * @param url 接口名
     * @return 请求观察对象
     */
    @Override
    public Observable httpPost(String url) {
        return RxHttpMag.getInstance().httpPost(url);
    }

    /**
     * POST请求--用于多请求合并
     *
     * @param url    接口名
     * @param params 参数
     * @return 请求观察对象
     */
    @Override
    public Observable httpPost(String url, Map<String, Object> params) {
        return RxHttpMag.getInstance().httpPost(url, params);
    }

    /**
     * PUT请求
     *
     * @param url      接口名
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    @Override
    public Disposable httpPut(String url, NetSingleCallBack callBack) {
        return RxHttpMag.getInstance().httpPut(url, callBack);
    }

    /**
     * PUT请求
     *
     * @param owner    监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url      接口名
     * @param callBack 请求结果回调
     */
    @Override
    public void httpPut(LifecycleOwner owner, String url, NetSingleCallBack callBack) {
        RxHttpMag.getInstance().httpPut(owner, url, callBack);
    }

    /**
     * PUT请求
     *
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    @Override
    public Disposable httpPut(String url, Map<String, Object> params, NetSingleCallBack callBack) {
        return RxHttpMag.getInstance().httpPut(url, params, callBack);
    }

    /**
     * PUT请求
     *
     * @param owner    监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     */
    @Override
    public void httpPut(LifecycleOwner owner, String url, Map<String, Object> params, NetSingleCallBack callBack) {
        RxHttpMag.getInstance().httpPut(owner, url, params, callBack);
    }

    /**
     * PUT请求--用于多请求合并
     *
     * @param url 接口名
     * @return 请求观察对象
     */
    @Override
    public Observable httpPut(String url) {
        return RxHttpMag.getInstance().httpPut(url);
    }

    /**
     * PUT请求--用于多请求合并
     *
     * @param url    接口名
     * @param params 参数
     * @return 请求观察对象
     */
    @Override
    public Observable httpPut(String url, Map<String, Object> params) {
        return RxHttpMag.getInstance().httpPut(url, params);
    }

    /**
     * DELETE请求
     *
     * @param url      接口名
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    @Override
    public Disposable httpDel(String url, NetSingleCallBack callBack) {
        return RxHttpMag.getInstance().httpDel(url, callBack);
    }

    /**
     * DELETE请求
     *
     * @param owner    监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url      接口名
     * @param callBack 请求结果回调
     */
    @Override
    public void httpDel(LifecycleOwner owner, String url, NetSingleCallBack callBack) {
        RxHttpMag.getInstance().httpDel(owner, url, callBack);
    }

    /**
     * DELETE请求
     *
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    @Override
    public Disposable httpDel(String url, Map<String, Object> params, NetSingleCallBack callBack) {
        return RxHttpMag.getInstance().httpDel(url, params, callBack);
    }

    /**
     * DELETE请求
     *
     * @param owner    监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     */
    @Override
    public void httpDel(LifecycleOwner owner, String url, Map<String, Object> params, NetSingleCallBack callBack) {
        RxHttpMag.getInstance().httpDel(owner, url, params, callBack);
    }

    /**
     * DELETE请求--用于多请求合并
     *
     * @param url 接口名
     * @return 请求观察对象
     */
    @Override
    public Observable httpDel(String url) {
        return RxHttpMag.getInstance().httpDel(url);
    }

    /**
     * DELETE请求--用于多请求合并
     *
     * @param url    接口名
     * @param params 参数
     * @return 请求观察对象
     */
    @Override
    public Observable httpDel(String url, Map<String, Object> params) {
        return RxHttpMag.getInstance().httpDel(url, params);
    }

    /**
     * 文件上传
     *
     * @param url            接口名
     * @param files         参数
     * @param uploadCallBack 上传进度回调
     * @param callBack       上传结果回调
     */
    @Override
    public Disposable httpFileUpload(String url, Map<String, Object> files, NetFileUploadCallBack uploadCallBack, NetSingleCallBack callBack) {
        return RxHttpMag.getInstance().httpFileUpload(url, files, uploadCallBack, callBack);
    }

    /**
     * 文件上传
     *
     * @param owner          监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url            接口名
     * @param files         参数
     * @param uploadCallBack 上传进度回调
     * @param callBack       上传结果回调
     */
    @Override
    public void httpFileUpload(LifecycleOwner owner, String url, Map<String, Object> files, NetFileUploadCallBack uploadCallBack, NetSingleCallBack callBack) {
        RxHttpMag.getInstance().httpFileUpload(owner, url, files, uploadCallBack, callBack);
    }

    @Override
    public void httpFileUpload(LifecycleOwner owner, String url, String fileKey, List files, NetFileUploadCallBack uploadCallBack, NetSingleCallBack callBack) {
        RxHttpMag.getInstance().httpFileUpload(owner, url, fileKey, files, uploadCallBack, callBack);
    }

    /**
     * 文件上传
     *
     * @param owner          监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url            接口名
     * @param files          文件
     * @param params         参数
     * @param uploadCallBack 上传进度回调
     * @param callBack       上传结果回调
     */
    @Override
    public void httpFileUpload(LifecycleOwner owner, String url, Map<String, Object> files, Map<String, Object> params, NetFileUploadCallBack uploadCallBack, NetSingleCallBack callBack) {
        RxHttpMag.getInstance().httpFileUpload(owner, url,files, params, uploadCallBack, callBack);
    }

    /**
     * 文件下载
     *
     * @param url              接口名
     * @param destPath         文件保存路径
     * @param breakpoint       是否断点下载
     * @param downloadCallBack 下载进度回调
     * @param callBack         下载结果回调
     */
    @Override
    public Disposable httpFileDownload(String url, String destPath, boolean breakpoint, NetFileDownloadCallBack downloadCallBack, NetSingleCallBack callBack) {
        if (breakpoint) {
            return RxHttpMag.getInstance().httpFileAppendDownload(url, destPath, downloadCallBack, callBack);
        } else {
            return RxHttpMag.getInstance().httpFileDownload(url, destPath, downloadCallBack, callBack);
        }
    }

    /**
     * 文件下载
     *
     * @param owner            监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url              接口名
     * @param destPath         文件保存路径
     * @param breakpoint       是否断点下载
     * @param downloadCallBack 下载进度回调
     * @param callBack         下载结果回调
     */
    @Override
    public void httpFileDownload(LifecycleOwner owner, String url, String destPath, boolean breakpoint, NetFileDownloadCallBack downloadCallBack, NetSingleCallBack callBack) {
        if (breakpoint) {
            RxHttpMag.getInstance().httpFileAppendDownload(owner, url, destPath, downloadCallBack, callBack);
        } else {
            RxHttpMag.getInstance().httpFileDownload(owner, url, destPath, downloadCallBack, callBack);
        }
    }

    /**
     * 多网络请求合并
     *
     * @param owner    页面生命周期 Activity、Fragment 传this即可
     * @param callBack 请求结果回调
     * @param reqList  请求列表
     */
    @Override
    public void httpMergeRequest(LifecycleOwner owner, NetMergeCallBack callBack, Map<String, Object> reqList) {
        RxHttpMag.getInstance().httpMergeRequest(owner, callBack, reqList);
    }

}
