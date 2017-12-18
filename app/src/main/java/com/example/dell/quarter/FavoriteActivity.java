package com.example.dell.quarter;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import adapter.FavoriteAdapter;
import base.BasePresenter;
import bean.GetFavorite;
import presenter.GetFavoritePresenter;
import view.GetFavoriteView;

public class FavoriteActivity extends  BaseActivity<GetFavoritePresenter> implements GetFavoriteView {


    private XRecyclerView favorite_xrv;
    private TextView tv_fanhui;

    @Override
    public GetFavoritePresenter initPrsenter() {
        return new GetFavoritePresenter(this,this);
    }

    @Override
    public void initView() {
        favorite_xrv = findViewById(R.id.favorite_xrv);
        tv_fanhui = findViewById(R.id.tv_fanhui);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        favorite_xrv.setLayoutManager(linearLayoutManager);

        favorite_xrv.setPullRefreshEnabled(false);
        favorite_xrv.setLoadingMoreEnabled(false);
        tv_fanhui.setOnClickListener(this);
        SharedPreferences uid = getSharedPreferences("uid", MODE_PRIVATE);
        int uid1 = uid.getInt("uid", 0);
        presenter.getFavorite(uid1+"");


    }

    @Override
    public void initData() {

    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_favorite;
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
    public void RequestSuccess(GetFavorite getFavorite) {
        showToast(getFavorite.msg);
        List<GetFavorite.DataBean> data = getFavorite.data;
        FavoriteAdapter adapter=new FavoriteAdapter(this,data);
        favorite_xrv.setAdapter(adapter);
    }

    @Override
    public void RequestFailure(GetFavorite getFavorite) {
        showToast(getFavorite.msg);
    }

    @Override
    public void Failure(GetFavorite getFavorite) {

    }
}
