package com.bwie.retrofitdesigndemo.presenter;

import com.bwie.retrofitdesigndemo.contract.ProductContract;
import com.bwie.retrofitdesigndemo.model.ProductDetailModel;
import com.bwie.retrofitdesigndemo.utils.NetCallback;

import java.util.HashMap;

public class ProductDetailPresenter extends ProductContract.IProductPresenter {
    private ProductDetailModel productDetailModel;
    private ProductContract.IProductView iProductView;

    public ProductDetailPresenter(ProductContract.IProductView iProductView){
        this.iProductView = iProductView;
        productDetailModel = new ProductDetailModel();

    }


    @Override
    public void getProduct(HashMap<String, String> params, String id) {
        productDetailModel.getProduct(params, id, new NetCallback() {
            @Override
            public void success(Object o) {
                iProductView.success(o);
            }

            @Override
            public void failure(String error) {

                iProductView.failure(error);
            }
        });
    }
}
