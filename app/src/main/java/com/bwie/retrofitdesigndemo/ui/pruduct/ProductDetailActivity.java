package com.bwie.retrofitdesigndemo.ui.pruduct;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.blankj.utilcode.util.SPUtils;
import com.bwie.lib_core.base.BaseActivity;
import com.bwie.lib_core.base.bean.BaseBean;
import com.bwie.retrofitdesigndemo.LoginActivity;
import com.bwie.retrofitdesigndemo.R;
import com.bwie.retrofitdesigndemo.api.ProductServicee;
import com.bwie.retrofitdesigndemo.contract.ProductContract;
import com.bwie.retrofitdesigndemo.entity.ProductDetailBean;
import com.bwie.retrofitdesigndemo.entity.SyncDataBean;
import com.bwie.retrofitdesigndemo.presenter.ProductDetailPresenter;
import com.bwie.retrofitdesigndemo.utils.GreendaoUtils;
import com.bwie.retrofitdesigndemo.utils.RetrofitUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ProductDetailActivity extends BaseActivity implements ProductContract.IProductView {

//    @BindView(R.id.wv_detail)
//    WebView webView;

    private ProductDetailPresenter productDetailPresenter;

    ProductDetailBean productDetailBean;
    @Override
    protected void initData() {
        productDetailPresenter = new ProductDetailPresenter(this);
        productDetailPresenter.getProduct(new HashMap<>(),"25");

//        webView.loadUrl("");

//        webView.loadData("代码片段", "text/html", "UTF-8");//API提供的标准用法，无法解决乱码问题
        }

    @Override
    protected void initView() {

        initSettings();
    }

    /**
     *  初始化webview的设置
     */
    private void initSettings() {

//        webView.getSettings().setJavaScriptEnabled(true);

    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void click(View v) {

    }

    /**
     * 商品详情的对象
     * @param data
     */
    @Override
    public void success(Object data) {

        if (data instanceof ProductDetailBean){
            productDetailBean = (ProductDetailBean) data;
//            webView.loadData(productDetailBean.details, "text/html;charset=UTF-8", null);//这种写法可以正确解码

            String pictures = productDetailBean.picture;//"http.jpg,http.jpg,,,,,,"

            String[] urls = pictures.split(",");//分割字符串的api成一个数组

            for (String url : urls) {



            }
        }

    }

    @Override
    public void failure(String error) {

    }

    /**
     * 加入购物车
     * @param view
     */
    @OnClick(R.id.add)
    public void add(View view){

//        HashMap<String,String> headers = new HashMap<>();
//        headers.put("userId", SPUtils.getInstance().getString("userId"));
//        headers.put("sessionId", SPUtils.getInstance().getString("sessionId"));

        String cid = "25";


        SyncDataBean dataBean = new SyncDataBean();
        dataBean.commodityId = cid;
        dataBean.count = 1;

        GreendaoUtils.getInstance().getDaoSession().insertOrReplace(dataBean);


        List<SyncDataBean> list1 = GreendaoUtils.getInstance().getDaoSession().loadAll(SyncDataBean.class);


        String data = new Gson().toJson(list1);

        System.out.println(data);//[{},{},{}]


        RetrofitUtils.getInstance().createService(ProductServicee.class)
                .syncData(SPUtils.getInstance().getString("userId"),SPUtils.getInstance().getString("sessionId"),data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        showToast(baseBean.message);

                        if (!"0000".equals(baseBean.status)){
                            startActivity(LoginActivity.class);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
