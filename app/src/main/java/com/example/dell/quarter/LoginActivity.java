package com.example.dell.quarter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.imagepipeline.producers.BranchOnSeparateImagesProducer;

import base.BasePresenter;
import utils.ImmersionUtil;

public class LoginActivity extends BaseActivity {

    private TextView login_tv;


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
