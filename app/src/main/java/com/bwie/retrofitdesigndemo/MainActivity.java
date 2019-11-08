package com.bwie.retrofitdesigndemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.bwie.retrofitdesigndemo.adapter.HomeAdapter;
import com.bwie.retrofitdesigndemo.base.BaseActivity;
import com.bwie.retrofitdesigndemo.base.mvp.IBasePresenter;
import com.bwie.retrofitdesigndemo.contract.HomeContract;
import com.bwie.retrofitdesigndemo.entity.HomeDao;
import com.bwie.retrofitdesigndemo.entity.HomeEntity;
import com.bwie.retrofitdesigndemo.entity.HomeMultipartEntity;
import com.bwie.retrofitdesigndemo.greendao.DaoMaster;
import com.bwie.retrofitdesigndemo.greendao.DaoSession;
import com.bwie.retrofitdesigndemo.model.HomeModel;
import com.bwie.retrofitdesigndemo.presenter.HomePresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements HomeContract.IHmoeView {
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private SQLiteDatabase sqLiteDatabase;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private HomeAdapter homeAdapter;
    private int page = 1;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private HomePresenter homePresenter;

    @Override
    protected void initData() {
        initGreendao();

        loadData();


    }

    /**
     * 加载数据：网络和本地
     */
    private void loadData() {
        homePresenter = new HomePresenter(this);
        homePresenter.attach(this);

        if (isNet()) {//有网

            homePresenter.getHome(new HashMap<>());
        } else {
            List<HomeDao> homeDaos = daoSession.loadAll(HomeDao.class);
            if (homeDaos != null && homeDaos.size() > 0) {
                HomeDao homeDao = homeDaos.get(0);
                String json = homeDao.getJsonResult();
                HomeEntity homeEntity = new Gson().fromJson(json, HomeEntity.class);//解析
                showDatas(homeEntity);
            }
        }
    }

    /**
     * 初始化greendao数据
     */
    private void initGreendao() {

        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        devOpenHelper = new DaoMaster.DevOpenHelper(this, "wdmall.db", null);
        sqLiteDatabase = devOpenHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(sqLiteDatabase);
        daoSession = daoMaster.newSession();

    }

    @Override
    protected void initView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.detach();//解绑
    }


    @Override
    public void success(Object data) {


        if (data instanceof HomeEntity) {//

            showDatas((HomeEntity) data);


        }
    }

    /**
     * 有网展示数据/本地存储和无网展示数据
     *
     * @param data
     */
    private void showDatas(HomeEntity data) {
        //热销新品列表
        List<HomeEntity.ResultBean.RxxpBean.CommodityListBean> rxxpList = ((HomeEntity) data).getResult().getRxxp().getCommodityList();
//
        //魔力时尚的列表
        List<HomeEntity.ResultBean.MlssBean.CommodityListBeanXX> mlshList = ((HomeEntity) data).getResult().getMlss().getCommodityList();
 //品质生活的列表
        List<HomeEntity.ResultBean.PzshBean.CommodityListBeanX> pzshList = ((HomeEntity) data).getResult().getPzsh().getCommodityList();


        //多条目数据
        List<HomeMultipartEntity> list = new ArrayList<>();//保证这个列表只有两条



        HomeMultipartEntity homeMultipartEntity1 = new HomeMultipartEntity();
        homeMultipartEntity1.type = 0;

        for (HomeEntity.ResultBean.RxxpBean.CommodityListBean commodityListBean : rxxpList) {
            HomeMultipartEntity.Home home = new HomeMultipartEntity.Home();
            home.commodityId = commodityListBean.getCommodityId();
            home.commodityName = commodityListBean.getCommodityName();
            home.masterPic = commodityListBean.getMasterPic();
            home.price = commodityListBean.getPrice();


            homeMultipartEntity1.list.add(home);//第一个条目的数据

        }
        list.add(homeMultipartEntity1);

        HomeMultipartEntity homeMultipartEntity2 = new HomeMultipartEntity();
        homeMultipartEntity2.type = 1;
        for (HomeEntity.ResultBean.MlssBean.CommodityListBeanXX commodityListBean : mlshList) {
            HomeMultipartEntity.Home home = new HomeMultipartEntity.Home();
            home.commodityId = commodityListBean.getCommodityId();
            home.commodityName = commodityListBean.getCommodityName();
            home.masterPic = commodityListBean.getMasterPic();
            home.price = commodityListBean.getPrice();


            homeMultipartEntity2.list.add(home);

        }

        list.add(homeMultipartEntity2);


        HomeMultipartEntity homeMultipartEntity3 = new HomeMultipartEntity();
        homeMultipartEntity3.type = 2;
        for (HomeEntity.ResultBean.PzshBean.CommodityListBeanX commodityListBean : pzshList) {
            HomeMultipartEntity.Home home = new HomeMultipartEntity.Home();
            home.commodityId = commodityListBean.getCommodityId();
            home.commodityName = commodityListBean.getCommodityName();
            home.masterPic = commodityListBean.getMasterPic();
            home.price = commodityListBean.getPrice();


            homeMultipartEntity3.list.add(home);

        }

        list.add(homeMultipartEntity3);

        System.out.println("sizeeeee" + list.size());//只有两个

        //设置adapter
        if (page == 1) {
            homeAdapter = new HomeAdapter(this, list);
            recyclerView.setAdapter(homeAdapter);
        } else {//上拉
            if (homeAdapter != null) {
                homeAdapter.notifyDataSetChanged();
            }
        }

        //greendao存储
        String result = new Gson().toJson(data);
        //对象关系映射
        HomeDao homeDao = new HomeDao();
        homeDao.setJsonResult(result);

        //晴空以前的数据
        daoSession.getHomeDaoDao().deleteAll();
        //都存入
        daoSession.getHomeDaoDao().insert(homeDao);



    }

    @Override
    public void failure(String error) {

        showToast(error);
    }


}
