package com.huayun.lib_network.okhtpp;

import com.huayun.lib_network.util.RxHttpConfig;
import com.huayun.lib_tools.util.log.LogUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import rxhttp.wrapper.utils.GsonUtil;

/**
 * 请求日志拦截器
 */
public class RequestLogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        String responseBodyString = (responseBody == null ? "null" : responseBody.string());
        if (RxHttpConfig.getInstance().getResultLogShowParam()) {
            LogUtil.xLoge("请求路径结果And返回数据==>" +
                    request.method() + ' ' + request.url() + "  请求头：" + GsonUtil.toJson(request.headers()) + "\n" + getParam(request) + "\n返回结果：" + responseBodyString);
        } else {
            LogUtil.xLoge("请求路径结果And返回数据==>" +
                    request.method() + ' ' + request.url() + "  请求头：" + GsonUtil.toJson(request.headers()) + "\n返回结果：" + responseBodyString);
        }
        return response.newBuilder().body(ResponseBody.create(responseBody == null ? MediaType.parse("application/json") : responseBody.contentType(),
                responseBodyString.getBytes())).build();
    }

    /**
     * 拦截请求参数并签名
     */
    private String getParam(Request request) {
        String param = "参数：";
        try {
            String type = request.method();
            if (type.equals("GET") && request.url().encodedQuery() != null) {//获取get请求的参数
                String encodedQuery = request.url().encodedQuery();
                String[] querys = encodedQuery.split("&");
                Map<String, String> queryMap = new HashMap<>();
                for (String query : querys) {
                    String[] split = query.split("=");
                    queryMap.put(split[0], split[1]);
                }
                param += GsonUtil.toJson(queryMap);
            } else if (type.equals("POST")) {//获取POST请求的参数
                param += bodyToString(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
            param+="参数解析异常-如出现崩溃请检查传参是否正确！";
        }
        return param;
    }

    /**
     * post 请求参数获取
     */
    private String bodyToString(final Request request) {
        final Request copy = request.newBuilder().build();
        final Buffer buffer = new Buffer();
        try {
            copy.body().writeTo(buffer);
        } catch (IOException e) {
            return "get Request param Error";
        }
        return buffer.readUtf8();
    }
}
