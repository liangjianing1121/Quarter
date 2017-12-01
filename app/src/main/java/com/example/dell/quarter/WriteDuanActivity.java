package com.example.dell.quarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import adapter.Adapter;
import bean.PublishJoke;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import presenter.PublishJokePresenter;
import utils.GlideLoader;
import view.PublishJokeview;

public class WriteDuanActivity extends BaseActivity<PublishJokePresenter> implements PublishJokeview {

    private TextView tv_baocun;
    private TextView tv_bubaocun;
    private TextView tv_finish;
    private PopupWindow mpopupWindow;
    private TextView tv_quxiao;
    private TextView tv_fabiao;
    private EditText et_duanzi;
    private RelativeLayout rl;

    private Adapter adapter;
    private RecyclerView recycler;

    private ArrayList<String> path = new ArrayList<>();
    private ImageView iv_tupian;

    @Override
    public PublishJokePresenter initPrsenter() {
        return new PublishJokePresenter(this, this);
    }

    @Override
    public void initView() {

        View popview = View.inflate(this, R.layout.pop_layout, null);

        mpopupWindow = new PopupWindow(popview);
        mpopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mpopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        tv_baocun = popview.findViewById(R.id.tv_baocun);
        tv_bubaocun = popview.findViewById(R.id.tv_bubaocun);
        tv_finish = popview.findViewById(R.id.tv_finish);


        tv_quxiao = findViewById(R.id.tv_quxiao);
        tv_fabiao = findViewById(R.id.tv_fabiao);
        et_duanzi = findViewById(R.id.et_duanzi);
        rl = findViewById(R.id.rl);
        iv_tupian = findViewById(R.id.iv_tupian);

        tv_finish.setOnClickListener(this);
        iv_tupian.setOnClickListener(this);
        tv_quxiao.setOnClickListener(this);
        tv_fabiao.setOnClickListener(this);

        recycler = findViewById(R.id.recycler);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void initData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recycler.setLayoutManager(gridLayoutManager);
        adapter = new Adapter(this, path);
        recycler.setAdapter(adapter);
    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_write_duan;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {
        switch (view.getId()) {

             case R.id.tv_quxiao:
                View rootview = View.inflate(this, R.layout.activity_write_duan, null);
                mpopupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
                ColorDrawable dw = new ColorDrawable(0xb0000000);
                rl.setBackgroundDrawable(dw);
                break;


            case R.id.tv_finish:
                ColorDrawable cd = new ColorDrawable(0000000);
                rl.setBackgroundDrawable(cd);
                mpopupWindow.dismiss();
                startActivity(HomeActivity.class);
                break;

            case R.id.tv_fabiao:
                String duanzi = et_duanzi.getText().toString();
                SharedPreferences uid = getSharedPreferences("uid", MODE_PRIVATE);
                int uid1 = uid.getInt("uid", 0);
                if(duanzi==null||duanzi.equals(""))
                {
                    showToast("段子内容不能为空");
                }
                else
                {
                    presenter.getPublishJoke(uid1+"" ,duanzi,path);

                }
                break;
            case R.id.iv_tupian:

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
                        .mutiSelect()
//                        .crop()
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

                ImageSelector.open(WriteDuanActivity.this, imageConfig); // 开启图片选择器
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
            }

            path.clear();
            path.addAll(pathList);
            adapter.notifyDataSetChanged();


        }
    }

    @Override
    public void RequestSuccess(PublishJoke publishJoke) {
        String msg = publishJoke.msg;
        showToast(msg);
        startActivity(PulishSuccessActivity.class);

    }

    @Override
    public void RequestFailure(PublishJoke publishJoke) {
        System.out.println(publishJoke.msg);

    }

    @Override
    public void Failure(PublishJoke publishJoke) {

    }

}
