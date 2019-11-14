package com.bwie.retrofitdesigndemo.contract;

import com.bwie.lib_core.base.mvp.IBasePresenter;
import com.bwie.retrofitdesigndemo.utils.NetCallback;

import java.util.HashMap;

public interface ProductContract {

    interface  IProductModel  {


        void getProduct(HashMap<String,String> params, String id,NetCallback netCallback);

    }


    interface IProductView  {

        void  success(Object data);
        void failure(String error);

    }

    abstract class IProductPresenter extends IBasePresenter {

        public abstract void getProduct(HashMap<String, String> params,String id);

    }
}
