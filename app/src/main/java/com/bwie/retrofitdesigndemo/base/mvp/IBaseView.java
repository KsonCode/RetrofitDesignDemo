package com.bwie.retrofitdesigndemo.base.mvp;

public interface IBaseView {

    IBasePresenter initPresenter();
    void showDialog();//显示加载进度
    void hideDialog();//隐藏加载进度
}
