package com.huayun.lib_network.util;

import com.google.gson.Gson;
import com.huayun.lib_network.base_net.bean.BaseResponse;
import com.huayun.lib_tools.util.GsonUtil;

public class NetGsonUtil {

    /**
     * 转成bean
     * 用于合并接口请求时返回数据解析处理
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T netGsonToBean(Object gsonString, Class<T> cls) {
        Gson gson = GsonUtil.getGson();
        T t = null;
        if (gson != null && !gsonString.toString().equals(RxHttpConfig.getInstance().getRequestErrorTag())) {
            BaseResponse baseResponse = gson.fromJson(gsonString.toString(), BaseResponse.class);
            t = gson.fromJson(gson.toJson(baseResponse.data), cls);
        }
        return t;
    }
}
