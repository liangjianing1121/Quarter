package com.example.dell.quarter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.imagepipeline.producers.BranchOnSeparateImagesProducer;

import base.BasePresenter;
import utils.ImmersionUtil;

public class LoginActivity extends BaseActivity {

    private TextView login_tv;
    private RelativeLayout relativeLayout2;


    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {
        switch (view.getId())
        {
            case R.id.login_tv:
                startActivity(OtherLoginActivity.class);
                break;
            case R.id.relativeLayout2:
                startActivity(MapActivity.class);
                break;
        }

    }

    @Override
    public BasePresenter initPrsenter() {
        return null;
    }

    @Override
    public void initView() {
        ImmersionUtil.TransparentStatusbar(this);
        login_tv = findViewById(R.id.login_tv);
        relativeLayout2 = findViewById(R.id.relativeLayout2);
        relativeLayout2.setOnClickListener(this);
        login_tv.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_login;
    }


}
