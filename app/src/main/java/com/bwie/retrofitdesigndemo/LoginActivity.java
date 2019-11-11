package com.bwie.retrofitdesigndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.bwie.retrofitdesigndemo.api.ProductServicee;
import com.bwie.retrofitdesigndemo.api.UserService;
import com.bwie.retrofitdesigndemo.base.BaseActivity;
import com.bwie.retrofitdesigndemo.entity.UserEntity;
import com.bwie.retrofitdesigndemo.utils.RetrofitUtils;

import java.util.HashMap;

import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {



    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.login)
    public void login(View v){
        HashMap<String ,String> params = new HashMap<>();
        params.put("phone","18612991023");
        params.put("pwd","111111");

        RetrofitUtils.getInstance().createService(UserService.class)
                .login(params)
                .subscribeOn(Schedulers.io())//请求网络的线程，io线程，子线程
                .observeOn(AndroidSchedulers.mainThread())//响应数据的主线程
                .subscribe(new Consumer<UserEntity>() {//订阅关系
            @Override
            public void accept(UserEntity userEntity) throws Exception {//成功请求

                SPUtils.getInstance().put("userId",userEntity.result.userId);
                SPUtils.getInstance().put("sessionId",userEntity.result.sessionId);

                getCarts();

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {//失败请求

            }
        });

        RetrofitUtils.getInstance().createService(UserService.class)
                .login1(new HashMap<>())
                .enqueue(new Callback<UserEntity>() {
                    @Override
                    public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {


                    }

                    @Override
                    public void onFailure(Call<UserEntity> call, Throwable t) {

                    }
                });

    }

    /**
     * 获取购物车列表
     */
    private void getCarts() {

        RetrofitUtils.getInstance().createService(ProductServicee.class)
                .getCarts(SPUtils.getInstance().getString("userId"),SPUtils.getInstance().getString("sessionId"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {

                        String result = responseBody.string();
                        showToast(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
