package com.simon.simpleretrofit.download.service;

/**
 * Created by xw on 2016/7/27.
 */
public class ServiceProvider {
    private static ServiceProvider instance = null;

    RetrofitService retrofitService;

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        if (instance == null) {
            synchronized (ServiceProvider.class) {
                if (instance == null) {
                    instance = new ServiceProvider();
                }
            }
        }
        return instance;
    }

    public RetrofitService getRetrofitService() {
        if (retrofitService == null) {
            retrofitService = new RetrofitService();
        }
        return retrofitService;
    }
}
