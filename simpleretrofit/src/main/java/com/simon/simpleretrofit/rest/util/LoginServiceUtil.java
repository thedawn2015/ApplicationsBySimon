package com.simon.simpleretrofit.rest.util;

import android.util.Log;

import com.simon.simpleretrofit.base.data.DataCenter;
import com.simon.simpleretrofit.base.listener.OnResponseListener;
import com.simon.simpleretrofit.rest.model.LoginResponse;
import com.simon.simpleretrofit.rest.model.User;
import com.simon.simpleretrofit.rest.service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
    public static void getResult(String username, String password, final OnResponseListener onResponseListener) {

        LoginService loginInterface = ServiceProvider.getInstance().getRetrofitService()
                .getLoginService();

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
                // FIXME: 2016/7/28 by xw TODO: 请求之后嵌套访问用户信息，这种实现方式不好，完全没有用到RxJava嵌套请求方法
                getUser(onResponseListener);
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
        Call<User> call = ServiceProvider.getInstance().getRetrofitService()
                .getLoginService()
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

    /**
     * 通过嵌套的方式请求用户信息（逻辑并不是很合理，只是用这个方法试一试）
     *
     * @param username
     * @param password
     * @param onResponseListener
     */
    public static void loginWithRetrofit(String username, String password, OnResponseListener onResponseListener) {
        ServiceProvider.getInstance().getRetrofitService()
                .getLoginService()
                .getTicketWithRetrofit(username, password)
                //flatMap用于嵌套请求，可以实现逻辑转换
                .flatMap(new Func1<LoginResponse, Observable<User>>() {
                    @Override
                    public Observable<User> call(LoginResponse loginResponse) {
                        Log.i(TAG, "call: Observable<User> call");
                        //请求到ticket之后，再进行用户信息的请求（嵌套请求）
                        DataCenter.TICKET = loginResponse.getTicket();
                        Observable<User> observableUser = ServiceProvider.getInstance().getRetrofitService()
                                .getLoginService()
                                .getUserInfoWithRetrofit();
                        return observableUser;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.i(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(User user) {
                        Log.i(TAG, "onNext: user.name=" + user.getName());
                    }
                });

    }
}
