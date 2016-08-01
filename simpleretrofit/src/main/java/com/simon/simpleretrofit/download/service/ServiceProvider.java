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

    public RetrofitService getRetrofitService(long startPoint) {
        if (retrofitService == null) {
            retrofitService = new RetrofitService();
        }
        retrofitService.setStartPoints(startPoint);
        return retrofitService;
    }

    private RetrofitService getRetrofitService() {
        return getRetrofitService(0);
    }
}
