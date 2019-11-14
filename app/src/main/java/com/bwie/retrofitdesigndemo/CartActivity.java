package com.bwie.retrofitdesigndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.blankj.utilcode.util.SPUtils;
import com.bwie.retrofitdesigndemo.api.ProductServicee;
import com.bwie.retrofitdesigndemo.common.Constansts;
import com.bwie.retrofitdesigndemo.utils.RetrofitUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        addCart();
    }

    /**
     * 同步购物车
     */
    private void addCart() {

//        XRecyclerView

//        HashMap<String,String> headers = new HashMap<>();
//        headers.put(Constansts.USER_ID, SPUtils.getInstance().getString(Constansts.USER_ID));
//        headers.put(Constansts.SESSION_ID,SPUtils.getInstance().getString(Constansts.SESSION_ID));
//
//        RetrofitUtils.getInstance().createService(ProductServicee.class)
//                .syncData(headers,"{}");
    }
}
