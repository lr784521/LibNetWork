package com.huayun.libnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.huayun.lib_network.annotation.BindRxHttp;
import com.huayun.lib_network.base_net.NetSingleCallBackImpl;
import com.huayun.lib_network.base_net.call.IHttp;
import com.huayun.lib_tools.util.GsonUtil;
import com.huayun.lib_tools.util.log.LogUtil;
import com.huayun.libnetwork.hilt.IApplication;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {


    @Inject
    public IApplication http;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cla(View view) {
        http.getIHttp().httpGet(this, "sysConfig/getSysConfigList", new NetSingleCallBackImpl<PublicConfigBean>() {
            @Override
            public void onSuccess(PublicConfigBean response, int code, String msg) {
                LogUtil.xLoge("response-->" + GsonUtil.toJson(response));
            }

            @Override
            public void onError(String msg, int code) {

            }
        });
    }
}