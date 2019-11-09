package com.bwie.retrofitdesigndemo.api;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ProductServicee {

    @GET("small/order/verify/v1/findShoppingCart")
    Observable<ResponseBody> getCarts(@Header("userId") String uid,@Header("sessionId") String sessionId);
}
