package com.bwie.retrofitdesigndemo.model;

import com.bwie.retrofitdesigndemo.api.HomeService;
import com.bwie.retrofitdesigndemo.contract.HomeContract;
import com.bwie.retrofitdesigndemo.entity.HomeEntity;
import com.bwie.retrofitdesigndemo.utils.NetCallback;
import com.bwie.retrofitdesigndemo.utils.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeModel implements HomeContract.IHomeModel {
    @Override
    public void getHome(HashMap<String, String> params, NetCallback netCallback) {

        RetrofitUtils.getInstance().createService(HomeService.class)
                .getHomeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeEntity>() {
                    @Override
                    public void accept(HomeEntity homeEntity) throws Exception {

                        if (netCallback!=null){
                            netCallback.success(homeEntity);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        netCallback.failure("网络有问题");
                    }
                });


    }
}
