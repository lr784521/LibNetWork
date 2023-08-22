package com.huayun.lib_network.base_net;

import android.content.Context;
import android.os.Build;

import com.huayun.lib_network.R;
import com.huayun.lib_network.base_net.bean.BaseResponse;
import com.huayun.lib_network.base_net.call.NetSingleCallBack;
import com.huayun.lib_network.util.CheckUtil;
import com.huayun.lib_tools.util.AppGlobalUtils;
import com.huayun.lib_tools.util.GsonUtil;
import com.huayun.lib_tools.util.StringUtil;
import com.huayun.lib_tools.util.log.LogUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


/**
 * 单个网络请求--顶层网络请求接口回调实现
 */
public abstract class NetSingleCallBackImpl<T> implements NetSingleCallBack {

    private Context context = AppGlobalUtils.getApplication();//全局上下文

    @Override
    public void onSuccess(String result) {
        //1.判断是否是Json字符串
        if (GsonUtil.isJson(result)) {
            actionJson(result);
        } else {
            Class<?> aClass = analysisClassInfo(this);
            actionNoJson(aClass, result);
        }
    }

    @Override
    public void onError(Throwable e) {
        NetOnErrorImpl.onError(context, e, new NetOnErrorImpl.CallBack() {
            @Override
            public void result(String logMsg, String tipsMsg, int code) {
                LogUtil.xLoge("NetError-->" + logMsg);
                onError(tipsMsg, code);
            }
        });
    }

    /**
     * 处理请求返回的Json字符串
     *
     * @param result 请求返回结果
     */
    private void actionJson(String result) {
        //2.创建基类
        BaseResponse baseResponse = GsonUtil.gsonToBean(result, BaseResponse.class);
        NetOnSuccessImpl.onSuccess(baseResponse, new NetOnSuccessImpl.CallBack() {
            @Override
            public void onSuccess(BaseResponse response) {
                if (GsonUtil.isJson(GsonUtil.toJson(response.data))) {
                    //普通泛型-->
                    //3.获取用户传进来的泛型的真实类型
                    Class<?> aClass = analysisClassInfo(NetSingleCallBackImpl.this);
                    //4.将用户想要的泛型与基类泛型映射
                    T t = (T) GsonUtil.gsonToBean(GsonUtil.toJson(response.data), aClass);
                    //5.把结果交给用户
                    NetSingleCallBackImpl.this.onSuccess(t, response.code, response.msg);
                } else if (GsonUtil.isJsonList(GsonUtil.toJson(response.data))) {
                    //List泛型-->
                    Class<?> aClass = analysisClassInfoList(NetSingleCallBackImpl.this);
                    List<?> tList = GsonUtil.gsonToList(GsonUtil.toJson(response.data), aClass);
                    T t = (T) tList;
                    NetSingleCallBackImpl.this.onSuccess(t, response.code, response.msg);
                } else {
                    if (response.data==null || StringUtil.isBlank(response.data.toString())) {
                        //空data
                        NetSingleCallBackImpl.this.onSuccess(null, response.code, response.msg);
                    } else {
                        try {
                            //其他基本类型(泛型)
                            //分析泛型
                            Class aClass = analysisClassInfo(NetSingleCallBackImpl.this);
                            //将非JSON返回结果转为具体泛型
                            Object obj = response.getDecrypt(aClass);
                            T t = (T) obj;
                            NetSingleCallBackImpl.this.onSuccess(t, response.code, response.msg);
                        } catch (Exception e) {
//                            Log.e("=_=","网络请求成功但数据解析失败-->"+e.getMessage());
                            //网络请求是成功的，但由于传递进来的泛型与返回数据不匹配，导致数据转换失败
                            NetSingleCallBackImpl.this.onError(context.getString(R.string.net_data_error), ServerCode.CODE_ERROR_CODE);
                        }
                    }
                }
            }

            @Override
            public void onError(String tipsMsg, int code) {
                NetSingleCallBackImpl.this.onError(tipsMsg, code);
            }
        });
    }

    /**
     * 处理请求返回的字符串非Json格式
     *
     * @param aClass 泛型类型
     * @param result 返回值
     */
    private void actionNoJson(Class<?> aClass, String result) {
        if (aClass.equals(java.util.List.class)) {
        } else if (CheckUtil.isBaseDefaultValue(aClass) && !StringUtil.isBlank(result)) {
            //判断是否是基本类型&&返回结果不为空
            T t = (T) result;
            onSuccess(t, ServerCode.CODE_SUCCESS, context.getString(R.string.net_success));
        } else {
            onError(context.getString(R.string.net_error), ServerCode.CODE_ERROR_CODE);
        }
    }

    /**
     * 分析当前接口上的泛型T的真正类型
     *
     * @param obj 泛型
     * @return 泛型的真正类型
     */
    private Class<?> analysisClassInfo(Object obj) {
        //getGenericSuperclass()返回一个类型 对象
        //这个对象可以得到包含原始类型，参数化，数组，类型变量，基本数据类型
        Type type = obj.getClass().getGenericSuperclass();
        //获取所有参数化泛型传参  因为泛型可以传多个参数  所以这里是数组
        Type[] arguments = ((ParameterizedType) type).getActualTypeArguments();
        String className;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            className = arguments[0].getTypeName();
        } else {
            className = arguments[0].toString();
        }
        if (className.contains("java.util.List")) {
            return List.class;
        }
        return (Class<?>) arguments[0];
    }

    /**
     * 获取泛型List类型中的真实类型
     *
     * @param obj
     * @return
     */
    private Class<?> analysisClassInfoList(Object obj) {
        //这个对象可以得到包含原始类型，参数化，数组，类型变量，基本数据类型
        Type type = obj.getClass().getGenericSuperclass();
        //获取所有参数化泛型传参  因为泛型可以传多个参数  所以这里是数组
        Type[] arguments = ((ParameterizedType) type).getActualTypeArguments();
        String className;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            className = arguments[0].getTypeName();
        } else {
            className = arguments[0].toString();
        }
        if (className.contains("java.util.List")) {
            String replace = className.replace("java.util.List<", "").replace(">", "");
            try {
                Class<?> aClass = Class.forName(replace);
                return aClass;
            } catch (ClassNotFoundException e) {
                return String.class;
            }
        }
        return (Class<?>) arguments[0];
    }

    /**
     * 成功回调
     *
     * @param response 返回结果 泛型类型
     * @param msg      成功提示
     */
    public abstract void onSuccess(T response, int code, String msg);

    /**
     * 错误回调
     *
     * @param msg  错误提示
     * @param code 错误码
     */
    public abstract void onError(String msg, int code);


}
