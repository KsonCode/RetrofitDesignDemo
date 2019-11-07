package com.bwie.retrofitdesigndemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.bwie.retrofitdesigndemo.base.BaseActivity;
import com.bwie.retrofitdesigndemo.base.mvp.IBasePresenter;
import com.bwie.retrofitdesigndemo.contract.HomeContract;
import com.bwie.retrofitdesigndemo.entity.HomeDao;
import com.bwie.retrofitdesigndemo.entity.HomeEntity;
import com.bwie.retrofitdesigndemo.greendao.DaoMaster;
import com.bwie.retrofitdesigndemo.greendao.DaoSession;
import com.bwie.retrofitdesigndemo.model.HomeModel;
import com.bwie.retrofitdesigndemo.presenter.HomePresenter;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements HomeContract.IHmoeView {
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private SQLiteDatabase sqLiteDatabase;
    private DaoMaster.DevOpenHelper devOpenHelper;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private HomePresenter homePresenter;

    @Override
    protected void initData() {
        initGreendao();
        homePresenter = new HomePresenter(this);
        homePresenter.attach(this);

        if (isNet()){//有网
            homePresenter.getHome(new HashMap<>());
        }else{
            List<HomeDao> homeDaos = daoSession.loadAll(HomeDao.class);
            if (homeDaos!=null&&homeDaos.size()>0){
                HomeDao homeDao = homeDaos.get(0);
                String json = homeDao.getJsonResult();
                HomeEntity homeEntity = new Gson().fromJson(json, HomeEntity.class);//解析
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

        if (data instanceof HomeEntity){//

            List<HomeEntity.ResultBean.MlssBean.CommodityListBeanXX> list1 = ((HomeEntity) data).getResult().getMlss().getCommodityList();
//            List<HomeEntity.ResultBean.MlssBean.CommodityListBeanXX> list2 = ((HomeEntity) data).getResult().getMlss().getCommodityList();
//            List<HomeEntity.ResultBean.MlssBean.CommodityListBeanXX> list2 = ((HomeEntity) data).getResult().getMlss().getCommodityList();


            HomeEntity homeEntity = (HomeEntity) data;

            String result = new Gson().toJson(homeEntity);

            HomeDao homeDao = new HomeDao();
            homeDao.setJsonResult(result);

            daoSession.getHomeDaoDao().deleteAll();
            //都存入
            daoSession.getHomeDaoDao().insert(homeDao);

            //设置adapter


        }
    }

    @Override
    public void failure(String error) {

        showToast(error);
    }
}
