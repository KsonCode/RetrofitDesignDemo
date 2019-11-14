package com.bwie.retrofitdesigndemo.utils;

import android.database.sqlite.SQLiteDatabase;

import com.bwie.retrofitdesigndemo.api.Api;
import com.bwie.retrofitdesigndemo.app.App;
import com.bwie.retrofitdesigndemo.greendao.DaoMaster;
import com.bwie.retrofitdesigndemo.greendao.DaoSession;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GreendaoUtils {
    //私有属性
    private static  GreendaoUtils mInstance;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private SQLiteDatabase sqLiteDatabase;
    private DaoMaster.DevOpenHelper devOpenHelper;

    //私有构造
    private GreendaoUtils(){

        initGreendao();


    }

    //公共实例

    public static GreendaoUtils getInstance(){
        if (mInstance==null){
            synchronized (GreendaoUtils.class){
                if (mInstance==null){
                    mInstance = new GreendaoUtils();
                }
            }
        }

        return mInstance;
    }
    /**
     * 初始化greendao数据
     */
    private void initGreendao() {

        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        devOpenHelper = new DaoMaster.DevOpenHelper(App.context, "wdmall.db", null);
        sqLiteDatabase = devOpenHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(sqLiteDatabase);
        daoSession = daoMaster.newSession();

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
