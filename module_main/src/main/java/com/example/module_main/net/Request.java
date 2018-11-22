package com.example.module_main.net;

import com.hazz.kotlinmvp.api.UriConstant;

import cn.net.yto.baselibrary.net.RetrofitManager;
import cn.net.yto.ytomvp.api.ApiService;

public class Request {

    public static ApiService service = null;
    static {
        RetrofitManager retrofitManager = new RetrofitManager();
       service =  retrofitManager.createApiService(UriConstant.INSTANCE.getBASE_URL(), ApiService.class);
    }


}
