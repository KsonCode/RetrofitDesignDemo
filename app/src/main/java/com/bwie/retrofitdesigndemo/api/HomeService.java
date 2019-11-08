package com.bwie.retrofitdesigndemo.api;

import com.bwie.retrofitdesigndemo.entity.HomeEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HomeService {

    @GET("small/commodity/v1/commodityList")
    Observable<HomeEntity> getHomeData();
}
