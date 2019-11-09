package com.bwie.retrofitdesigndemo.api;

import com.bwie.retrofitdesigndemo.entity.UserEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

/**
 * 用户相关接口
 */
public interface UserService {

    @POST("small/user/v1/login")
    @FormUrlEncoded
    Observable<UserEntity> login(@FieldMap HashMap<String,String> params);
}
