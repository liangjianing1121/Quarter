package com.example.dell.quarter;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import adapter.GuanZhuAdapter;
import base.BasePresenter;
import presenter.GetFollowPresenter;
import view.GetFollowUserView;

public class FollowActivity extends BaseActivity<GetFollowPresenter> implements GetFollowUserView {


    private XRecyclerView xrv;
    private TextView tv_fanhui;

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("uid", MODE_PRIVATE);
        int uid = sp.getInt("uid", 0);
        presenter.getFollowUser(uid+"");
    }

    @Override
    public GetFollowPresenter initPrsenter() {
        return new GetFollowPresenter(this,this);
    }

    @Override
    public void initView() {
        xrv = findViewById(R.id.xrv);
        tv_fanhui = findViewById(R.id.tv_fanhui);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrv.setLayoutManager(linearLayoutManager);
        tv_fanhui.setOnClickListener(this);
        xrv.setLoadingMoreEnabled(false);
        xrv.setPullRefreshEnabled(false);

    }

    @Override
    public void initData() {

    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_follow;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {
        switch (view.getId())
        {
            case R.id.tv_fanhui:
                finish();
                break;
        }
    }
    @Override
    public void RequestSuccess(GetFollowUser getFollowUser) {
        List<GetFollowUser.DataBean> data = getFollowUser.data;
        GuanZhuAdapter guanZhuAdapter=new GuanZhuAdapter(this,data);
        xrv.setAdapter(guanZhuAdapter);

    }

    @Override
    public void RequestFailure(GetFollowUser getFollowUser) {

    }

    @Override
    public void Failure(GetFollowUser getFollowUser) {

    }
}
