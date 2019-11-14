package com.bwie.retrofitdesigndemo.api;

import com.bwie.lib_core.base.bean.BaseBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface SQservice {

    @GET("techApi/community/v1/findCommunityList")
    Observable<BaseBean> getList(@Header("userId") String uid, @Header("sessionId") String sid, @Query("page") int page, @Query("count") int count);

    @POST("techApi/community/verify/v1/releasePost")
    @Multipart
//多表单上传，可以上传文件和字符串
    Observable<BaseBean> postContent(@Header("userId") String uid, @Header("sessionId") String sid, @Part("content") RequestBody content, @Part List<MultipartBody.Part> files);

    @POST("techApi/community/verify/v1/releasePost")
    @Multipart
//上传头像
    Observable<BaseBean> uploadHeadPic(@Header("userId") String uid, @Header("sessionId") String sid, @Field("content") String content, @Part MultipartBody.Part file);


}
