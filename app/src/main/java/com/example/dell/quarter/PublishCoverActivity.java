package com.example.dell.quarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import base.BasePresenter;
import bean.PublishVideos;
import presenter.PublishVideoPresenter;
import utils.GlideLoader;
import utils.ImmersionUtil;
import view.PublishVideoView;

public class PublishCoverActivity extends BaseActivity<PublishVideoPresenter> implements PublishVideoView {

    private Button bt_cover;
    private ArrayList<String> path = new ArrayList<>();
    private ImageView iv_head;
    private EditText et_desc;
    private String desc;
    private Button bt_publish;
    private File cover;
    private File video;

    @Override
    public PublishVideoPresenter initPrsenter() {
        return new PublishVideoPresenter(this,this);
    }

    @Override
    public void initView() {
        ImmersionUtil.TransparentStatusbar(this);
        Intent intent = getIntent();
        String videourl = intent.getStringExtra("videourl");
        video = new File(videourl);
        System.out.println("=======传过来的视频路径======="+ video);
        bt_cover = findViewById(R.id.bt_cover);
        iv_head = findViewById(R.id.iv_head);
        et_desc = findViewById(R.id.et_desc);
        bt_publish = findViewById(R.id.bt_publish);

        bt_publish.setOnClickListener(this);
        desc = et_desc.getText().toString();
        bt_cover.setOnClickListener(this);
    }

    @Override
    public void initData() {



    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_publish_cover;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {
        switch (view.getId())
        {
            case R.id.bt_cover:
                ImageConfig imageConfig
                        = new ImageConfig.Builder(
                        // GlideLoader 可用自己用的缓存库
                        new GlideLoader())
                        // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
                        .steepToolBarColor(Color.rgb(24,172,252))
                        // 标题的背景颜色 （默认黑色）
                        .titleBgColor(Color.rgb(24,172,252))
                        // 提交按钮字体的颜色  （默认白色）
                        .titleSubmitTextColor(getResources().getColor(R.color.white))
                        // 标题颜色 （默认白色）
                        .titleTextColor(getResources().getColor(R.color.white))
                        // 开启多选   （默认为多选）  (单选 为 singleSelect)
                        .singleSelect()
                        .crop()
                        // 多选时的最大数量   （默认 9 张）
                        .mutiSelectMaxSize(9)
                        // 已选择的图片路径
                        .pathList(path)
                        // 拍照后存放的图片路径（默认 /temp/picture）
                        .filePath("/ImageSelector/Pictures")
                        // 开启拍照功能 （默认开启）
                        .showCamera()
                        .requestCode(REQUEST_CODE)
                        .build();

                ImageSelector.open(PublishCoverActivity.this, imageConfig); // 开启图片选择器
                break;

            case R.id.bt_publish:
                System.out.println(video+"             "+cover);
                SharedPreferences uid = getSharedPreferences("uid", MODE_PRIVATE);
                int uid1 = uid.getInt("uid", 0);
                if(path!=null){
                    presenter.publishVideo(uid1+"",video,cover,desc,100+"",100+"");
                }
                Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public static final int REQUEST_CODE = 1000;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            for (String path : pathList) {
                Log.i("ImagePathList", path);
                Glide.with(this).load(path).into(iv_head);
                cover = new File(path);
            }
            path.clear();
            path.addAll(pathList);

        }
    }



    @Override
    public void RequestSuccess(PublishVideos publishVideos) {
        showToast(publishVideos.msg);
        startActivity(PulishSuccessActivity.class);
    }

    @Override
    public void RequestFailure(PublishVideos publishVideos) {
        showToast(publishVideos.msg);
    }

    @Override
    public void Failure(PublishVideos publishVideos) {

    }
}
