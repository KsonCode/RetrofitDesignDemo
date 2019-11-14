package com.bwie.retrofitdesigndemo.model;

import com.bwie.retrofitdesigndemo.api.ProductServicee;
import com.bwie.retrofitdesigndemo.contract.ProductContract;
import com.bwie.retrofitdesigndemo.entity.ProductDetailBean;
import com.bwie.retrofitdesigndemo.utils.NetCallback;
import com.bwie.retrofitdesigndemo.utils.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ProductDetailModel implements ProductContract.IProductModel {


    @Override
    public void getProduct(HashMap<String, String> params, String id, NetCallback netCallback) {
        RetrofitUtils.getInstance().createService(ProductServicee.class)

                .getDetail(params,id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProductDetailBean>() {
                    @Override
                    public void accept(ProductDetailBean productDetailBean) throws Exception {

                        netCallback.success(productDetailBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        netCallback.failure("网络异常");
                    }
                });
    }
}
