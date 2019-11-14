package com.bwie.retrofitdesigndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.bwie.lib_core.base.BaseActivity;
import com.bwie.lib_core.base.bean.BaseBean;
import com.bwie.retrofitdesigndemo.api.SQservice;
import com.bwie.retrofitdesigndemo.utils.RetrofitUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SQActivity extends BaseActivity implements XRecyclerView.LoadingListener {


    @BindView(R.id.rv)
    XRecyclerView xRecyclerView;

    @Override
    protected void initData() {
        requestList();
    }

    @Override
    protected void initView() {

        xRecyclerView.setLoadingListener(this);

        xRecyclerView.setLoadingMoreEnabled(true);//加载更多配置


    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_sq;
    }

    @Override
    protected void click(View v) {

    }

    /**
     * 请求列表
     */
    private void requestList() {

        RetrofitUtils.getInstance().createService(SQservice.class)

                .getList(SPUtils.getInstance().getString("userId"),SPUtils.getInstance().getString("sessionId"),1,5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {

                        showToast(baseBean.message);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @OnClick(R.id.post)
    public void post(View view){
        startActivity(new Intent(this,PostActivity.class));
    }
}
