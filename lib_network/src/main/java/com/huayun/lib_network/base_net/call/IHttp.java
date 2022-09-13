package com.huayun.lib_network.base_net.call;

import androidx.lifecycle.LifecycleOwner;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 定义顶层接口
 */
public interface IHttp {

    /**
     * GET请求
     *
     * @param url      接口名
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    Disposable httpGet(String url, NetSingleCallBack callBack);

    /**
     * GET请求
     *
     * @param owner    监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url      接口名
     * @param callBack 请求结果回调
     */
    void httpGet(LifecycleOwner owner, String url, NetSingleCallBack callBack);

    /**
     * GET请求
     *
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    Disposable httpGet(String url, Map<String, Object> params, NetSingleCallBack callBack);

    /**
     * GET请求
     *
     * @param owner    监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     */
    void httpGet(LifecycleOwner owner, String url, Map<String, Object> params, NetSingleCallBack callBack);

    /**
     * GET请求--用于多请求合并
     *
     * @param url 接口名
     * @return 请求观察对象
     */
    Observable httpGet(String url);

    /**
     * GET请求--用于多请求合并
     *
     * @param url    接口名
     * @param params 参数
     * @return 请求观察对象
     */
    Observable httpGet(String url, Map<String, Object> params);

    /**
     * POST请求
     *
     * @param url      接口名
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    Disposable httpPost(String url, NetSingleCallBack callBack);

    /**
     * POST请求
     *
     * @param owner    监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url      接口名
     * @param callBack 请求结果回调
     */
    void httpPost(LifecycleOwner owner, String url, NetSingleCallBack callBack);

    /**
     * POST请求
     *
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    Disposable httpPost(String url, Map<String, Object> params, NetSingleCallBack callBack);


    /**
     * POST请求
     *
     * @param owner    监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     */
    void httpPost(LifecycleOwner owner, String url, Map<String, Object> params, NetSingleCallBack callBack);

    /**
     * POST请求--用于多请求合并
     *
     * @param url 接口名
     * @return 请求观察对象
     */
    Observable httpPost(String url);

    /**
     * POST请求--用于多请求合并
     *
     * @param url    接口名
     * @param params 参数
     * @return 请求观察对象
     */
    Observable httpPost(String url, Map<String, Object> params);

    /**
     * PUT请求
     *
     * @param url      接口名
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    Disposable httpPut(String url, NetSingleCallBack callBack);

    /**
     * PUT请求
     *
     * @param owner    监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url      接口名
     * @param callBack 请求结果回调
     */
    void httpPut(LifecycleOwner owner, String url, NetSingleCallBack callBack);

    /**
     * PUT请求
     *
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    Disposable httpPut(String url, Map<String, Object> params, NetSingleCallBack callBack);

    /**
     * PUT请求
     *
     * @param owner    监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     */
    void httpPut(LifecycleOwner owner, String url, Map<String, Object> params, NetSingleCallBack callBack);

    /**
     * PUT请求--用于多请求合并
     *
     * @param url 接口名
     * @return 请求观察对象
     */
    Observable httpPut(String url);

    /**
     * PUT请求--用于多请求合并
     *
     * @param url    接口名
     * @param params 参数
     * @return 请求观察对象
     */
    Observable httpPut(String url, Map<String, Object> params);

    /**
     * DELETE请求
     *
     * @param url      接口名
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    Disposable httpDel(String url, NetSingleCallBack callBack);

    /**
     * DELETE请求
     *
     * @param owner    监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url      接口名
     * @param callBack 请求结果回调
     */
    void httpDel(LifecycleOwner owner, String url, NetSingleCallBack callBack);

    /**
     * DELETE请求
     *
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    Disposable httpDel(String url, Map<String, Object> params, NetSingleCallBack callBack);

    /**
     * DELETE请求
     *
     * @param owner    监听页面生命周期自动取消网络请求、将回调结果切到主线程
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     */
    void httpDel(LifecycleOwner owner, String url, Map<String, Object> params, NetSingleCallBack callBack);

    /**
     * DELETE请求--用于多请求合并
     *
     * @param url 接口名
     * @return 请求观察对象
     */
    Observable httpDel(String url);

    /**
     * DELETE请求--用于多请求合并
     *
     * @param url    接口名
     * @param params 参数
     * @return 请求观察对象
     */
    Observable httpDel(String url, Map<String, Object> params);

    /**
     * 文件上传
     *
     * @param url            接口名
     * @param files         文件参数(Map<String, File>)类型
     * @param uploadCallBack 上传进度回调
     * @param callBack       上传结果回调
     */
    Disposable httpFileUpload(String url, Map<String, Object> files, NetFileUploadCallBack uploadCallBack, NetSingleCallBack callBack);

    /**
     * 文件上传
     *
     * @param owner          自动管理生命周期
     * @param url            接口名
     * @param files          文件参数(Map<String, File>)类型
     * @param uploadCallBack 上传进度回调
     * @param callBack       上传结果回调
     */
    void httpFileUpload(LifecycleOwner owner, String url, Map<String, Object> files, NetFileUploadCallBack uploadCallBack, NetSingleCallBack callBack);

    /**
     * 文件上传
     *
     * @param owner          自动管理生命周期
     * @param url            接口名
     * @param files          文件参数(Map<String, File>)类型
     * @param uploadCallBack 上传进度回调
     * @param callBack       上传结果回调
     */
    void httpFileUpload(LifecycleOwner owner, String url, String fileKey, List files, NetFileUploadCallBack uploadCallBack, NetSingleCallBack callBack);

    /**
     * 文件上传
     *
     * @param owner          自动管理生命周期
     * @param url            接口名
     * @param files          文件参数(Map<String, File>)类型
     * @param params         其他参数
     * @param uploadCallBack 上传进度回调
     * @param callBack       上传结果回调
     */
    void httpFileUpload(LifecycleOwner owner, String url, Map<String, Object> files, Map<String, Object> params, NetFileUploadCallBack uploadCallBack, NetSingleCallBack callBack);

    /**
     * 文件下载
     *
     * @param url              接口名
     * @param destPath         文件保存路径
     * @param breakpoint       是否断点下载
     * @param downloadCallBack 下载进度回调
     * @param callBack         下载结果回调
     */
    Disposable httpFileDownload(String url, String destPath, boolean breakpoint, NetFileDownloadCallBack downloadCallBack, NetSingleCallBack callBack);

    /**
     * 文件下载
     *
     * @param owner            自动管理生命周期
     * @param url              接口名
     * @param destPath         文件保存路径
     * @param breakpoint       是否断点下载
     * @param downloadCallBack 下载进度回调
     * @param callBack         下载结果回调
     */
    void httpFileDownload(LifecycleOwner owner, String url, String destPath, boolean breakpoint, NetFileDownloadCallBack downloadCallBack, NetSingleCallBack callBack);


    /**
     * 多请求合并
     *
     * @param callBack 请求结果回调
     * @param owner    页面生命周期 Activity、Fragment 传this即可
     * @param reqList  请求列表
     */
    void httpMergeRequest(LifecycleOwner owner, NetMergeCallBack callBack, Map<String, Object> reqList);

}
