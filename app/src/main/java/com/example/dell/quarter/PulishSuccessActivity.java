package com.example.dell.quarter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import base.BasePresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PulishSuccessActivity extends BaseActivity {

    @BindView(R.id.tv_fanhui)
    TextView tvFanhui;

    @Override
    public BasePresenter initPrsenter() {
        return null;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_pulish_success;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {

    }

    @OnClick(R.id.tv_fanhui)
    public void onViewClicked() {
        finish();
    }
}
