package com.bwie.retrofitdesigndemo.api;

import com.bwie.lib_core.base.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface SQservice {

    @GET("techApi/community/v1/findCommunityList")
    Observable<BaseBean> getList(@Header("userId") String uid, @Header("sessionId") String sid, @Query("page") int page,@Query("count") int count);
}
