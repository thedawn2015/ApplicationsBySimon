package com.simon.simpleretrofit.rest.util;

import android.util.Log;

import com.simon.simpleretrofit.base.data.DataCenter;
import com.simon.simpleretrofit.base.listener.OnResponseListener;
import com.simon.simpleretrofit.rest.model.LoginResponse;
import com.simon.simpleretrofit.rest.model.User;
import com.simon.simpleretrofit.rest.service.LoginInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xw on 2016/7/26.
 */
public class LoginServiceUtil {
    public static String TAG = LoginServiceUtil.class.getSimpleName();

    /**
     * 请求ticket
     *
     * @param username
     * @param password
     * @param onResponseListener
     */
    public static void getResult(String username, String password, OnResponseListener onResponseListener) {

        LoginInterface loginInterface = ServiceInstance.getInstance().getRetrofitService()
                .getLoginInterface();

        Call<LoginResponse> call = loginInterface.getTicket(username, password);

        //异步请求
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                int statusCode = response.code();
                Log.i(TAG, "enqueue onResponse: statusCode=" + statusCode);
                LoginResponse loginResponse = response.body();
                DataCenter.TICKET = loginResponse.getTicket();
                Log.i(TAG, "enqueue onResponse: ticket=" + loginResponse.getTicket());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.i(TAG, "enqueue onFailure: ");
            }
        });

        //同步请求
        //同步的代码会阻塞线程，因此你不能在安卓的主线程中调用，不然会面临NetworkOnMainThreadException。
        // 如果你想调用execute方法，请在后台线程执行。
        /*try {
            Response<LoginResponse> loginResponseResponse = call.execute();
            LoginResponse loginResponse = loginResponseResponse.body();
            Log.i(TAG, "execute onResponse: ticket=" + loginResponse.getTicket());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 请求用户数据
     *
     * @param onResponseListener
     */
    public static void getUser(OnResponseListener onResponseListener) {
        Call<User> call = ServiceInstance.getInstance().getRetrofitService()
                .getLoginInterface()
                .getUserInfo();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                int statusCode = response.code();
                Log.i(TAG, "onResponse: statusCode=" + statusCode);
                User user = response.body();
                Log.i(TAG, "onResponse: user=" + user.getName());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
            }
        });
    }
}
