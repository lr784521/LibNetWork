package com.huayun.lib_network.rxhttp;

import android.os.Environment;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.huayun.lib_network.base_net.call.NetMergeCallBack;
import com.huayun.lib_network.base_net.call.NetSingleCallBack;
import com.huayun.lib_network.base_net.call.NetFileDownloadCallBack;
import com.huayun.lib_network.base_net.call.NetFileUploadCallBack;
import com.huayun.lib_network.okhttpImpl.OkHttpMag;
import com.huayun.lib_tools.util.AppGlobalUtils;
import com.huayun.lib_tools.util.log.LogUtil;
import com.rxjava.rxlife.RxLife;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import rxhttp.RxHttp;
import rxhttp.RxHttpPlugins;
import rxhttp.wrapper.annotation.DefaultDomain;
import rxhttp.wrapper.cahce.CacheMode;

/**
 * RxHttp管理
 */
@SuppressWarnings(value = "all")
public class RxHttpMag {

    private static volatile RxHttpMag instance;
    private String cachePath = Environment.getExternalStorageDirectory().getPath()
            + "/Android/data/" + AppGlobalUtils.getApplication().getPackageName() + "/cache/";
    @DefaultDomain() //设置为默认域名
    public static String baseUrl = "";
    private String REQUEST_ERROR_TAG="";//请求失败返回标识
    private Map<String, String> header=new HashMap<>();

    public static RxHttpMag getInstance() {
        if (instance == null) {
            synchronized (OkHttpMag.class) {
                if (instance == null) {
                    instance = new RxHttpMag();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化RxHttp 配置OkHttpClient
     * 缓存策略：
     * 存储：内部缓存 同app卸载销毁
     * 缓存大小：最大值10M，超出后根据LRU算法清理
     * 缓存模式：先请求网络，请求成功，写入缓存并返回；否则读取缓存
     * 缓存生命周期：5天
     */
    public void init(String baseUrl,boolean isDebug) {
        this.baseUrl=baseUrl;

        RxHttpPlugins.init(OkHttpMag.getInstance().getOkHttpClient())
                .setDebug(isDebug)
                .setCache(new File(cachePath), 10 * 1024 * 1024,
                        CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE, ((60 * 1000) * 60 * 24) * 5);
    }

    /**
     * 获取请求头
     * @return
     */
    public Map<String, String> getHeader() {
        return header;
    }

    /**
     * GET请求
     *
     * @param url      接口名
     * @param callBack 请求结果回调
     */
    public Disposable httpGet(String url, NetSingleCallBack callBack) {
        return RxHttp.get(url)
                .addAllHeader(header)
                .asString()
                .subscribe(s -> {
                    //请求成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //请求失败
                    callBack.onError(throwable);
                });
    }


    /**
     * GET请求
     *
     * @param url      接口名
     * @param callBack 请求结果回调
     */
    public void httpGet(LifecycleOwner owner, String url, NetSingleCallBack callBack) {
        RxHttp.get(url)
                .addAllHeader(header)
                .asString()
                .to(RxLife.asOnMain(owner, Lifecycle.Event.ON_DESTROY)) //感知生命周期，自动关闭请求
                .subscribe(s -> {
                    //请求成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //请求失败
                    callBack.onError(throwable);
                });
    }

    /**
     * GET请求
     *
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    public Disposable httpGet(String url, Map<String, Object> params, NetSingleCallBack callBack) {
        return RxHttp.get(url)
                .addAllHeader(header)
                .addAll(params)
                .asString()
                .subscribe(s -> {
                    //请求成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //请求失败
                    callBack.onError(throwable);
                });

    }

    /**
     * GET请求
     *
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     */
    public void httpGet(LifecycleOwner owner, String url, Map<String, Object> params, NetSingleCallBack callBack) {
        RxHttp.get(url)
                .addAllHeader(header)
                .addAll(params)
                .asString()
                .to(RxLife.asOnMain(owner, Lifecycle.Event.ON_DESTROY)) //感知生命周期，自动关闭请求
                .subscribe(s -> {
                    //请求成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //请求失败
                    callBack.onError(throwable);
                });
    }


    /**
     * GET请求-用于多请求合并
     *
     * @param url 接口名
     * @return 请求观察对象
     */
    public Observable httpGet(String url) {
        return RxHttp.get(url)
                .addAllHeader(header)
                .asString()
                .onErrorReturn(new Function<Throwable, String>() {
                    @Override
                    public String apply(Throwable throwable) throws Throwable {
                        return REQUEST_ERROR_TAG;
                    }
                });

    }


    /**
     * GET请求-用于多请求合并
     *
     * @param url    接口名
     * @param params 参数
     * @return 请求观察对象
     */
    public Observable httpGet(String url, Map<String, Object> params) {
        return RxHttp.get(url)
                .addAllHeader(header)
                .addAll(params)
                .asString()
                .onErrorReturn(new Function<Throwable, String>() {
                    @Override
                    public String apply(Throwable throwable) throws Throwable {
                        return REQUEST_ERROR_TAG;
                    }
                });
    }

    /**
     * POST请求
     *
     * @param url      接口名
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    public Disposable httpPost(String url, NetSingleCallBack callBack) {
        return RxHttp.postJson(url)
                .addAllHeader(header)
                .asString()
                .subscribe(s -> {
                    //请求成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //请求失败
                    callBack.onError(throwable);
                });
    }

    /**
     * POST请求
     *
     * @param url      接口名
     * @param callBack 请求结果回调
     */
    public void httpPost(LifecycleOwner owner, String url, NetSingleCallBack callBack) {
        RxHttp.postJson(url)
                .addAllHeader(header)
                .asString()
                .to(RxLife.asOnMain(owner, Lifecycle.Event.ON_DESTROY)) //感知生命周期，自动关闭请求
                .subscribe(s -> {
                    //请求成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //请求失败
                    callBack.onError(throwable);
                });
    }

    /**
     * POST请求
     *
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    public Disposable httpPost(String url, Map<String, Object> params, NetSingleCallBack callBack) {
        return RxHttp.postJson(url)
                .addAllHeader(header)
                .addAll(params)
                .asString()
                .subscribe(s -> {
                    //请求成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //请求失败
                    callBack.onError(throwable);
                });
    }


    /**
     * POST请求
     *
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     */
    public void httpPost(LifecycleOwner owner, String url, Map<String, Object> params, NetSingleCallBack callBack) {
        RxHttp.postJson(url)
                .addAllHeader(header)
                .addAll(params)
                .asString()
                .to(RxLife.asOnMain(owner, Lifecycle.Event.ON_DESTROY)) //感知生命周期，自动关闭请求
                .subscribe(s -> {
                    //请求成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //请求失败
                    callBack.onError(throwable);
                });
    }

    /**
     * POST请求-用于多请求合并
     *
     * @param url 接口名
     * @return 请求观察对象
     */
    public Observable httpPost(String url) {
        return RxHttp.postJson(url)
                .addAllHeader(header)
                .asString()
                .onErrorReturn(new Function<Throwable, String>() {
                    @Override
                    public String apply(Throwable throwable) throws Throwable {
                        return REQUEST_ERROR_TAG;
                    }
                });
    }

    /**
     * POST请求-用于多请求合并
     *
     * @param url    接口名
     * @param params 参数
     * @return 请求观察对象
     */
    public Observable httpPost(String url, Map<String, Object> params) {
        return RxHttp.postJson(url)
                .addAllHeader(header)
                .addAll(params)
                .asString()
                .onErrorReturn(new Function<Throwable, String>() {
                    @Override
                    public String apply(Throwable throwable) throws Throwable {
                        return REQUEST_ERROR_TAG;
                    }
                });
    }

    /**
     * PUT请求
     *
     * @param url      接口名
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    public Disposable httpPut(String url, NetSingleCallBack callBack) {
        return RxHttp.putJson(url)
                .addAllHeader(header)
                .asString()
                .subscribe(s -> {
                    //请求成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //请求失败
                    callBack.onError(throwable);
                });
    }

    /**
     * PUT请求
     *
     * @param url      接口名
     * @param callBack 请求结果回调
     */
    public void httpPut(LifecycleOwner owner, String url, NetSingleCallBack callBack) {
        RxHttp.putJson(url)
                .addAllHeader(header)
                .asString()
                .to(RxLife.asOnMain(owner, Lifecycle.Event.ON_DESTROY)) //感知生命周期，自动关闭请求
                .subscribe(s -> {
                    //请求成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //请求失败
                    callBack.onError(throwable);
                });
    }

    /**
     * PUT请求
     *
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    public Disposable httpPut(String url, Map<String, Object> params, NetSingleCallBack callBack) {
        return RxHttp.putJson(url)
                .addAllHeader(header)
                .addAll(params)
                .asString()
                .subscribe(s -> {
                    //请求成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //请求失败
                    callBack.onError(throwable);
                });
    }

    /**
     * PUT请求
     *
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     */
    public void httpPut(LifecycleOwner owner, String url, Map<String, Object> params, NetSingleCallBack callBack) {
        RxHttp.putJson(url)
                .addAllHeader(header)
                .addAll(params)
                .asString()
                .to(RxLife.asOnMain(owner, Lifecycle.Event.ON_DESTROY)) //感知生命周期，自动关闭请求
                .subscribe(s -> {
                    //请求成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //请求失败
                    callBack.onError(throwable);
                });
    }

    /**
     * PUT请求-用于多请求合并
     *
     * @param url 接口名
     * @return 请求观察对象
     */
    public Observable httpPut(String url) {
        return RxHttp.putJson(url)
                .addAllHeader(header)
                .asString()
                .onErrorReturn(new Function<Throwable, String>() {
                    @Override
                    public String apply(Throwable throwable) throws Throwable {
                        return REQUEST_ERROR_TAG;
                    }
                });
    }

    /**
     * PUT请求-用于多请求合并
     *
     * @param url    接口名
     * @param params 参数
     * @return 请求观察对象
     */
    public Observable httpPut(String url, Map<String, Object> params) {
        return RxHttp.putJson(url)
                .addAllHeader(header)
                .addAll(params)
                .asString()
                .onErrorReturn(new Function<Throwable, String>() {
                    @Override
                    public String apply(Throwable throwable) throws Throwable {
                        return REQUEST_ERROR_TAG;
                    }
                });
    }

    /**
     * DELETE请求
     *
     * @param url      接口名
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    public Disposable httpDel(String url, NetSingleCallBack callBack) {
        return RxHttp.deleteJson(url)
                .addAllHeader(header)
                .asString()
                .subscribe(s -> {
                    //请求成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //请求失败
                    callBack.onError(throwable);
                });
    }

    /**
     * DELETE请求
     *
     * @param url      接口名
     * @param callBack 请求结果回调
     */
    public void httpDel(LifecycleOwner owner, String url, NetSingleCallBack callBack) {
        RxHttp.deleteJson(url)
                .addAllHeader(header)
                .asString()
                .to(RxLife.asOnMain(owner, Lifecycle.Event.ON_DESTROY)) //感知生命周期，自动关闭请求
                .subscribe(s -> {
                    //请求成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //请求失败
                    callBack.onError(throwable);
                });
    }

    /**
     * DELETE请求
     *
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     * @return 用于关闭网络请求
     */
    public Disposable httpDel(String url, Map<String, Object> params, NetSingleCallBack callBack) {
        return RxHttp.deleteJson(url)
                .addAllHeader(header)
                .addAll(params)
                .asString()
                .subscribe(s -> {
                    //请求成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //请求失败
                    callBack.onError(throwable);
                });
    }

    /**
     * DELETE请求
     *
     * @param url      接口名
     * @param params   参数
     * @param callBack 请求结果回调
     */
    public void httpDel(LifecycleOwner owner, String url, Map<String, Object> params, NetSingleCallBack callBack) {
        RxHttp.deleteJson(url)
                .addAllHeader(header)
                .addAll(params)
                .asString()
                .to(RxLife.asOnMain(owner, Lifecycle.Event.ON_DESTROY)) //感知生命周期，自动关闭请求
                .subscribe(s -> {
                    //请求成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //请求失败
                    callBack.onError(throwable);
                });
    }

    /**
     * DELETE请求-用于多请求合并
     *
     * @param url 接口名
     * @return 请求观察对象
     */
    public Observable httpDel(String url) {
        return RxHttp.deleteJson(url)
                .addAllHeader(header)
                .asString()
                .onErrorReturn(new Function<Throwable, String>() {
                    @Override
                    public String apply(Throwable throwable) throws Throwable {
                        return REQUEST_ERROR_TAG;
                    }
                });
    }

    /**
     * DELETE请求-用于多请求合并
     *
     * @param url    接口名
     * @param params 参数
     * @return 请求观察对象
     */
    public Observable httpDel(String url, Map<String, Object> params) {
        return RxHttp.deleteJson(url)
                .addAllHeader(header)
                .addAll(params)
                .asString()
                .onErrorReturn(new Function<Throwable, String>() {
                    @Override
                    public String apply(Throwable throwable) throws Throwable {
                        return REQUEST_ERROR_TAG;
                    }
                });
    }

    /**
     * 文件上传
     *
     * @param url            接口名
     * @param files         参数
     * @param uploadCallBack 上传进度回调
     * @param callBack       上传结果回调
     */
    public Disposable httpFileUpload(String url, Map<String, Object> files, NetFileUploadCallBack uploadCallBack, NetSingleCallBack callBack) {
        return RxHttp.postForm(url) //发送Form表单形式的Post请求
                .addAllHeader(header)
                .addFiles(files)
                .upload(AndroidSchedulers.mainThread(), progress -> {
                    //上传进度回调,0-100，仅在进度有更新时才会回调
                    int currentProgress = progress.getProgress(); //当前进度 0-100
                    long currentSize = progress.getCurrentSize(); //当前已上传的字节大小
                    long totalSize = progress.getTotalSize();     //要上传的总字节大小
                    uploadCallBack.fileUploadCallBack(currentProgress, currentSize, totalSize);
                })
                .asString()
                .subscribe(s -> {
                    //上传成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //上传失败
                    callBack.onError(throwable);
                });
    }

    /**
     * 文件上传
     *
     * @param owner          自动管理生命周期
     * @param url            接口名
     * @param file         参数
     * @param uploadCallBack 上传进度回调
     * @param callBack       上传结果回调
     */
    public void httpFileUpload(LifecycleOwner owner, String url, Map<String, Object> file, NetFileUploadCallBack uploadCallBack, NetSingleCallBack callBack) {
        RxHttp.postForm(url) //发送Form表单形式的Post请求
                .addAllHeader(header)
                .addFiles(file)
                .upload(AndroidSchedulers.mainThread(), progress -> {
                    //上传进度回调,0-100，仅在进度有更新时才会回调
                    int currentProgress = progress.getProgress(); //当前进度 0-100
                    long currentSize = progress.getCurrentSize(); //当前已上传的字节大小
                    long totalSize = progress.getTotalSize();     //要上传的总字节大小
                    uploadCallBack.fileUploadCallBack(currentProgress, currentSize, totalSize);
                })
                .asString()
                .to(RxLife.asOnMain(owner, Lifecycle.Event.ON_DESTROY)) //感知生命周期，自动关闭请求
                .subscribe(s -> {
                    //上传成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //上传失败
                    callBack.onError(throwable);
                });
    }

    /**
     * 文件上传
     *
     * @param owner          自动管理生命周期
     * @param url            接口名
     * @param file         参数
     * @param uploadCallBack 上传进度回调
     * @param callBack       上传结果回调
     */
    public void httpFileUpload(LifecycleOwner owner, String url,String fileKey,List file, NetFileUploadCallBack uploadCallBack, NetSingleCallBack callBack) {
        RxHttp.postForm(url) //发送Form表单形式的Post请求
                .addAllHeader(header)
                .addFiles(fileKey,file)
                .upload(AndroidSchedulers.mainThread(), progress -> {
                    //上传进度回调,0-100，仅在进度有更新时才会回调
                    int currentProgress = progress.getProgress(); //当前进度 0-100
                    long currentSize = progress.getCurrentSize(); //当前已上传的字节大小
                    long totalSize = progress.getTotalSize();     //要上传的总字节大小
                    uploadCallBack.fileUploadCallBack(currentProgress, currentSize, totalSize);
                })
                .asString()
                .to(RxLife.asOnMain(owner, Lifecycle.Event.ON_DESTROY)) //感知生命周期，自动关闭请求
                .subscribe(s -> {
                    //上传成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //上传失败
                    callBack.onError(throwable);
                });
    }

    /**
     * 文件上传
     *
     * @param owner          自动管理生命周期
     * @param url            接口名
     * @param params         参数
     * @param uploadCallBack 上传进度回调
     * @param callBack       上传结果回调
     */
    public void httpFileUpload(LifecycleOwner owner, String url, Map<String, Object> file,Map<String, Object> params, NetFileUploadCallBack uploadCallBack, NetSingleCallBack callBack) {
        List<File> f=new ArrayList<>();
        RxHttp.postForm(url) //发送Form表单形式的Post请求
                .addAllHeader(header)
                .addFiles(file)
                .addAll(params)
                .upload(AndroidSchedulers.mainThread(), progress -> {
                    //上传进度回调,0-100，仅在进度有更新时才会回调
                    int currentProgress = progress.getProgress(); //当前进度 0-100
                    long currentSize = progress.getCurrentSize(); //当前已上传的字节大小
                    long totalSize = progress.getTotalSize();     //要上传的总字节大小
                    uploadCallBack.fileUploadCallBack(currentProgress, currentSize, totalSize);
                })
                .asString()
                .to(RxLife.asOnMain(owner, Lifecycle.Event.ON_DESTROY)) //感知生命周期，自动关闭请求
                .subscribe(s -> {
                    //上传成功
                    callBack.onSuccess(s);
                }, throwable -> {
                    //上传失败
                    callBack.onError(throwable);
                });
    }

    /**
     * 普通文件下载
     *
     * @param url              接口名
     * @param destPath         文件保存路径
     * @param downloadCallBack 下载进度回调
     * @param callBack         下载结果回调
     */
    public Disposable httpFileDownload(String url, String destPath, NetFileDownloadCallBack downloadCallBack, NetSingleCallBack callBack) {
        return RxHttp.get(url)
                .addAllHeader(header)
                .asDownload(destPath, AndroidSchedulers.mainThread(), progress -> {     //第二个参数指定主线程回调
                    //下载进度回调,0-100，仅在进度有更新时才会回调，最多回调101次，最后一次回调文件存储路径
                    int currentProgress = progress.getProgress(); //当前进度 0-100
                    long currentSize = progress.getCurrentSize(); //当前已下载的字节大小
                    long totalSize = progress.getTotalSize();     //要下载的总字节大小
                    downloadCallBack.fileUploadCallBack(currentProgress, currentSize, totalSize);
                })
                .subscribe(s -> {//s为String类型，这里为文件存储路径
                    //下载完成，处理相关逻辑
                    callBack.onSuccess(s);
                }, throwable -> {
                    //下载失败，处理相关逻辑
                    callBack.onError(throwable);
                });
    }

    /**
     * 普通文件下载
     *
     * @param owner            自动管理生命周期
     * @param url              接口名
     * @param destPath         文件保存路径
     * @param downloadCallBack 下载进度回调
     * @param callBack         下载结果回调
     */
    public void httpFileDownload(LifecycleOwner owner, String url, String destPath, NetFileDownloadCallBack downloadCallBack, NetSingleCallBack callBack) {
        RxHttp.get(url)
                .addAllHeader(header)
                .asDownload(destPath, AndroidSchedulers.mainThread(), progress -> {     //第二个参数指定主线程回调
                    //下载进度回调,0-100，仅在进度有更新时才会回调，最多回调101次，最后一次回调文件存储路径
                    int currentProgress = progress.getProgress(); //当前进度 0-100
                    long currentSize = progress.getCurrentSize(); //当前已下载的字节大小
                    long totalSize = progress.getTotalSize();     //要下载的总字节大小
                    downloadCallBack.fileUploadCallBack(currentProgress, currentSize, totalSize);
                })
                .to(RxLife.asOnMain(owner, Lifecycle.Event.ON_DESTROY)) //感知生命周期，自动关闭请求
                .subscribe(s -> {//s为String类型，这里为文件存储路径
                    //下载完成，处理相关逻辑
                    callBack.onSuccess(s);
                }, throwable -> {
                    //下载失败，处理相关逻辑
                    callBack.onError(throwable);
                });
    }

    /**
     * 断点文件下载
     *
     * @param url              接口名
     * @param destPath         文件保存路径
     * @param downloadCallBack 下载进度回调
     * @param callBack         下载结果回调
     */
    public Disposable httpFileAppendDownload(String url, String destPath, NetFileDownloadCallBack downloadCallBack, NetSingleCallBack callBack) {
        return RxHttp.get(url)
                .addAllHeader(header)
                .asAppendDownload(destPath, AndroidSchedulers.mainThread(), progress -> {     //第二个参数指定主线程回调
                    //下载进度回调,0-100，仅在进度有更新时才会回调，最多回调101次，最后一次回调文件存储路径
                    int currentProgress = progress.getProgress(); //当前进度 0-100
                    long currentSize = progress.getCurrentSize(); //当前已下载的字节大小
                    long totalSize = progress.getTotalSize();     //要下载的总字节大小
                    downloadCallBack.fileUploadCallBack(currentProgress, currentSize, totalSize);
                })
                .subscribe(s -> {//s为String类型，这里为文件存储路径
                    //下载完成，处理相关逻辑
                    callBack.onSuccess(s);
                }, throwable -> {
                    //下载失败，处理相关逻辑
                    callBack.onError(throwable);
                });
    }

    /**
     * 断点文件下载
     *
     * @param owner            自动管理生命周期
     * @param url              接口名
     * @param destPath         文件保存路径
     * @param downloadCallBack 下载进度回调
     * @param callBack         下载结果回调
     */
    public void httpFileAppendDownload(LifecycleOwner owner, String url, String destPath, NetFileDownloadCallBack downloadCallBack, NetSingleCallBack callBack) {
        RxHttp.get(url)
                .addAllHeader(header)
                .asAppendDownload(destPath, AndroidSchedulers.mainThread(), progress -> {     //第二个参数指定主线程回调
                    //下载进度回调,0-100，仅在进度有更新时才会回调，最多回调101次，最后一次回调文件存储路径
                    int currentProgress = progress.getProgress(); //当前进度 0-100
                    long currentSize = progress.getCurrentSize(); //当前已下载的字节大小
                    long totalSize = progress.getTotalSize();     //要下载的总字节大小
                    downloadCallBack.fileUploadCallBack(currentProgress, currentSize, totalSize);
                })
                .to(RxLife.asOnMain(owner, Lifecycle.Event.ON_DESTROY)) //感知生命周期，自动关闭请求
                .subscribe(s -> {//s为String类型，这里为文件存储路径
                    //下载完成，处理相关逻辑
                    callBack.onSuccess(s);
                }, throwable -> {
                    //下载失败，处理相关逻辑
                    callBack.onError(throwable);
                });
    }

    /**
     * 合并请求
     *
     * @param callBack 请求结果回调
     * @param owner    生命周期监听  Activity、Fragment传 this即可
     * @param reqList  所有请求的集合
     */
    public void httpMergeRequest(LifecycleOwner owner, NetMergeCallBack callBack, Map<String, Object> reqList) {
        List<Object> resList = new ArrayList<>();//存储每个请求返回的结果
        List<Observable<Object>> obsList = new ArrayList<>();//取出所有请求
        Iterator<Map.Entry<String, Object>> iterator = reqList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            obsList.add((Observable) next.getValue());
        }
        //合并请求 concat 按集合顺序依次执行所有请求
        Observable.concat(obsList)
                .to(RxLife.asOnMain(owner, Lifecycle.Event.ON_DESTROY)) //感知生命周期，自动关闭请求
                .subscribe(o -> {
                    //获取每次请求的结果
                    resList.add(o);
                }, throwable -> {
                    //请求失败
                    callBack.onError(throwable);
                }, () -> {
                    //完成所有请求
                    callBack.onSuccess(resList);
                });

    }

}
