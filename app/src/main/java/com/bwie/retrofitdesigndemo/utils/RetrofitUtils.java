package com.bwie.retrofitdesigndemo.utils;

import com.bwie.retrofitdesigndemo.api.Api;
import com.bwie.retrofitdesigndemo.api.HomeService;
import com.bwie.retrofitdesigndemo.api.UserService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络工具类封装
 */
public class RetrofitUtils {

    //私有属性
    private static  RetrofitUtils mInstance;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    //私有构造
    private RetrofitUtils(){

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    //公共实例

    public static RetrofitUtils getInstance(){
        if (mInstance==null){
            synchronized (RetrofitUtils.class){
                if (mInstance==null){
                    mInstance = new RetrofitUtils();
                }
            }
        }

        return mInstance;
    }


    /**
     * 动态代理模式的，apiservice
     *
     */
    public <T> T createService(Class<T> clz){

        return retrofit.create(clz);
    }

    /**
     * 判断网络
     * @return
     */
    public boolean isNet(){

        //
        //
        //
        return true;
    }



}
