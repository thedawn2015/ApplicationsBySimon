package com.simon.simpleretrofit.rest.service;

import com.simon.simpleretrofit.base.data.DataCenter;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit  Service
 * <p>
 * Created by xw on 2016/7/26.
 */
public class RetrofitService {
    public static String TAG = RetrofitService.class.getSimpleName();

    public final static String BASE_URL = DataCenter.HTTP_SERVICE_URL;

    private LoginService loginInterface;

    public RetrofitService() {
        initRetrofit();
    }

    private void initRetrofit() {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("cookie", "ticket=" + DataCenter.TICKET)
                                .build();
                        return chain.proceed(request);
                    }
                });
        OkHttpClient httpClient = httpBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loginInterface = retrofit.create(LoginService.class);
    }

    public LoginService getLoginInterface() {
        return loginInterface;
    }

}
