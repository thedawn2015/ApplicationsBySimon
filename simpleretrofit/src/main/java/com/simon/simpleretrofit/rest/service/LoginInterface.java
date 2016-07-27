package com.simon.simpleretrofit.rest.service;

import com.simon.simpleretrofit.rest.model.LoginResponse;
import com.simon.simpleretrofit.rest.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by xw on 2016/7/26.
 */
public interface LoginInterface {

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter
    @FormUrlEncoded
    @POST ("auth/login/")
    Call<LoginResponse> getTicket(@Field ("username") String username,
                                  @Field ("password") String password);

    @GET("auth/info/")
    Call<User> getUserInfo();
}
