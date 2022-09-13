package com.huayun.lib_network.base_net.call;

/**
 * 文件上传进度回调
 */
public interface NetFileUploadCallBack {
    /**
     * 上传进度回调,0-100，仅在进度有更新时才会回调
     * @param currentProgress 当前进度 0-100
     * @param currentSize 当前已上传的字节大小
     * @param totalSize 要上传的总字节大小
     */
    void fileUploadCallBack(int currentProgress,long currentSize,long totalSize);
}
