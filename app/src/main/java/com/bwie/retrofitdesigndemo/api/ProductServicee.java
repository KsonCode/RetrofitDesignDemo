package com.bwie.retrofitdesigndemo.api;


import com.bwie.lib_core.base.bean.BaseBean;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.PUT;

/**
 * 商品服务接口
 */
public interface ProductServicee {

    @GET("small/order/verify/v1/findShoppingCart")
    Observable<ResponseBody> getCarts(@Header("userId") String uid,@Header("sessionId") String sessionId);


    @PUT
    @FormUrlEncoded
    Observable<BaseBean> syncData(@HeaderMap HashMap<String,String> headers,@Field("data") String data);

}
