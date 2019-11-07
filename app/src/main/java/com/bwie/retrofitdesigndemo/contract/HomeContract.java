package com.bwie.retrofitdesigndemo.contract;

import com.bwie.retrofitdesigndemo.base.mvp.IBaseModel;
import com.bwie.retrofitdesigndemo.base.mvp.IBasePresenter;
import com.bwie.retrofitdesigndemo.base.mvp.IBaseView;
import com.bwie.retrofitdesigndemo.utils.NetCallback;

import java.util.HashMap;

public interface HomeContract {

    interface  IHomeModel  {


        void getHome(HashMap<String,String> params, NetCallback netCallback);

    }


    interface IHmoeView  {

        void  success(Object data);
        void failure(String error);

    }

    abstract class IHomePresenter extends IBasePresenter{

        public abstract void getHome(HashMap<String, String> params);

    }
}
