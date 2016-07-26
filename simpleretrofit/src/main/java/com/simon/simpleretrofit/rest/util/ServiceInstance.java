package com.simon.simpleretrofit.rest.util;

import com.simon.simpleretrofit.rest.service.RetrofitService;

/**
 * Created by xw on 2016/7/26.
 */
public class ServiceInstance {
    private static ServiceInstance instance = null;
    RetrofitService retrofitServiceUtil;

    private ServiceInstance() {
    }

    public static ServiceInstance getInstance() {
        if (instance == null) {
            synchronized (ServiceInstance.class) {
                if (instance == null) {
                    instance = new ServiceInstance();
                }
            }
        }
        return instance;
    }

    public RetrofitService getRetrofitService() {
        if (retrofitServiceUtil == null) {
            retrofitServiceUtil = new RetrofitService();
        }
        return retrofitServiceUtil;
    }
}
