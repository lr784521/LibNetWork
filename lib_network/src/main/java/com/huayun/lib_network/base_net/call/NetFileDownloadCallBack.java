package com.huayun.lib_network.base_net.call;

/**
 * 文件下载进度回调
 */
public interface NetFileDownloadCallBack {

    /**
     * 下载进度回调,0-100，仅在进度有更新时才会回调，最多回调101次，最后一次回调文件存储路径
     * @param currentProgress 当前进度 0-100
     * @param currentSize 当前已下载的字节大小
     * @param totalSize 要下载的总字节大小
     */
    void fileUploadCallBack(int currentProgress,long currentSize,long totalSize);
}
