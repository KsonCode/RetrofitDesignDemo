package com.bwie.retrofitdesigndemo.base;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.NetworkUtils;
import com.bwie.retrofitdesigndemo.MainActivity;
import com.bwie.retrofitdesigndemo.base.mvp.IBasePresenter;
import com.bwie.retrofitdesigndemo.base.mvp.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//    }

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayoutId());
        unbinder = ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract void initData();


    protected abstract void initView();


    protected abstract int bindLayoutId();

    /**
     * 网络判断
     *
     * @return
     */
    public boolean isNet() {

        if (NetworkUtils.isConnected()){
            showToast("有网");
            return true;
        }else {
            showToast("无网");
            return false;
        }

    }

    /**
     * 无参跳转
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {

        startActivity(new Intent(this, clz));
    }

    /**
     * 显示toast
     *
     * @param txt
     */
    public void showToast(String txt) {
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }

    }


}
