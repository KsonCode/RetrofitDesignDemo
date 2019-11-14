package com.bwie.retrofitdesigndemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.SPUtils;
import com.bwie.lib_core.base.BaseActivity;
import com.bwie.lib_core.base.bean.BaseBean;
import com.bwie.retrofitdesigndemo.adapter.PostAdapter;
import com.bwie.retrofitdesigndemo.api.SQservice;
import com.bwie.retrofitdesigndemo.common.Constansts;
import com.bwie.retrofitdesigndemo.utils.RetrofitUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PostActivity extends BaseActivity {

    @BindView(R.id.add)
    ImageView addIv;

    @BindView(R.id.dialog_layout)
    RelativeLayout dialogLayout;

    @BindView(R.id.camara)
    Button camaraBtn;
    @BindView(R.id.photo)
    Button photoBtn;
    @BindView(R.id.cancel)
    Button cancelBtn;

    @BindView(R.id.rv_pics)
    RecyclerView recyclerView;

    @BindView(R.id.et_content)
    EditText et;

    List<LocalMedia> selectList;//文件列表


    @OnClick(R.id.add)
    public void add(View view) {
        //调用相机或相册
        dialogLayout.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.camara)
    public void camara(View view) {
        dialogLayout.setVisibility(View.GONE);
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())//打开相机
                .compress(true)

                .forResult(PictureConfig.CHOOSE_REQUEST);
    // 是否压缩 true or false
    }

    @OnClick(R.id.photo)
    public void photo(View view) {
        dialogLayout.setVisibility(View.GONE);
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())//打开相册
                .compress(true)
                .forResult(PictureConfig.CHOOSE_REQUEST);

    }

    @OnClick(R.id.cancel)
    public void cancel(View view) {
        dialogLayout.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        recyclerView.setLayoutManager(new GridLayoutManager(this,3));


    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_post;
    }

    @Override
    protected void click(View v) {

    }


    @OnClick(R.id.btn_post_data)
    public void btn(View view){

        //多表单文件对象列表，通过集合维护
        List<MultipartBody.Part> filesPart = new ArrayList<>();

        //遍历已经选择的图片数据，并且转换成文件对象，保存到文件对象列表中
        for (LocalMedia localMedia : selectList) {
            //床架文件对象
            File file = new File(localMedia.getCompressPath());

            //把文件对象包装成请求体对象
//            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            // MultipartBody.Part  和后端约定好Key，这里的partName是用file
            //最终转换成需要的多表单对象
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

            filesPart.add(body);
        }

        if (et.getText().toString().length()==0){
            showToast("输入内容不能为空");
            return;
        }

        //文本
        RequestBody content = RequestBody.create(MediaType.parse("multipart/form-data"),et.getText().toString());

        //发布帖子接口
        RetrofitUtils.getInstance().createService(SQservice.class)
                .postContent(SPUtils.getInstance().getString("userId"),SPUtils.getInstance().getString("seessionId"),content,filesPart)
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    if (selectList!=null&&selectList.size()>0){
                        selectList.clear();
                    }
                    // 图片、视频、音频选择结果回调,框架的回调
                    selectList = PictureSelector.obtainMultipleResult(data);

                    PostAdapter postAdapter = new PostAdapter(PostActivity.this,selectList);
                    recyclerView.setAdapter(postAdapter);



                    break;
            }
        }
    }
}
