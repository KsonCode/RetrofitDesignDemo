package com.bwie.retrofitdesigndemo.presenter;

import com.bwie.retrofitdesigndemo.contract.HomeContract;
import com.bwie.retrofitdesigndemo.model.HomeModel;
import com.bwie.retrofitdesigndemo.utils.NetCallback;

import java.util.HashMap;

public  class HomePresenter extends HomeContract.IHomePresenter {

    private HomeModel homeModel;
    private HomeContract.IHmoeView iHmoeView;
    public HomePresenter(HomeContract.IHmoeView iHmoeView){

        this.iHmoeView = iHmoeView;
        homeModel = new HomeModel();

    }

    @Override
    public void getHome(HashMap<String, String> params) {

     homeModel.getHome(params, new NetCallback() {
         @Override
         public void success(Object o) {
             if (iHmoeView!=null)
             iHmoeView.success(o);

         }

         @Override
         public void failure(String error) {

             iHmoeView.failure(error);

         }
     });

    }
}
