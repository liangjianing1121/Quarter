package com.example.dell.quarter;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import base.BasePresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublishActivity extends BaseActivity {


    @BindView(R.id.iv_duanzi)
    ImageView ivDuanzi;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.include)
    RelativeLayout include;
    @BindView(R.id.iv_shipin)
    ImageView ivShipin;
    @BindView(R.id.textView2)
    TextView textView2;

    @Override
    public BasePresenter initPrsenter() {
        return null;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ivShipin.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_publish;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {
        switch (view.getId())
        {
            case R.id.iv_shipin:
                
                break;
        }



    }


    @OnClick(R.id.iv_duanzi)
    public void onViewClicked() {
        startActivity(WriteDuanActivity.class);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
