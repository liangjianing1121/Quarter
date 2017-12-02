package com.example.dell.quarter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import base.BasePresenter;
import utils.ClearCacheUtils;
import utils.SharedPreferencesUtil;

public class SettingActivity extends BaseActivity {


    private TextView tv_huancun;
    private RelativeLayout rl4;
    private Button bt_exit;

    @Override
    public BasePresenter initPrsenter() {
        return null;
    }

    @Override
    public void initView() {
        tv_huancun = findViewById(R.id.tv_huancun);
        rl4 = findViewById(R.id.rl4);
        bt_exit = findViewById(R.id.bt_exit);
        bt_exit.setOnClickListener(this);
        rl4.setOnClickListener(this);

    }

    @Override
    public void initData() {
        try {
            String totalCacheSize = ClearCacheUtils.getTotalCacheSize(this);
            tv_huancun.setText(totalCacheSize);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_setting;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {
        switch (view.getId())
        {
            case R.id.rl4:
                ClearCacheUtils.clearAllCache(this);
                try {
                    String totalCacheSize = ClearCacheUtils.getTotalCacheSize(this);
                    tv_huancun.setText(totalCacheSize);
                    showToast("缓存已清除");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_exit:
                SharedPreferencesUtil.clearPreferences("uid");
                showToast("退出登录");
                startActivity(OtherLoginActivity.class);



                break;


        }

    }
}
