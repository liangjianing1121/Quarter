package com.example.dell.quarter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.List;

import adapter.GetWorkInfoAdapter;
import adapter.UserVideoAdapter;
import base.BasePresenter;
import bean.GetWorkInfo;
import presenter.GetWorkInfoPresenter;
import view.GetWorkInfoView;

public class GetWorkInfoActivity extends BaseActivity<GetWorkInfoPresenter> implements GetWorkInfoView {


    private XRecyclerView xrv;
    private  List<GetWorkInfo.DataBean.WorksEntitiesBean> list;
    private GetWorkInfoAdapter getWorkInfoAdapter;
    private String uid;
    private int i;
    private SimpleDraweeView iv;
    private TextView tv_fensi;
    private TextView tv_guanzhu;
    private TextView tv_name;
    private Button bt_follow;


    @Override
    protected void onResume() {
        super.onResume();
        presenter.getWorkInfo(uid);
    }

    @Override
    public GetWorkInfoPresenter initPrsenter() {
        return new GetWorkInfoPresenter(this,this);
    }

    @Override
    public void initView() {
        iv = findViewById(R.id.iv);
        list=new ArrayList<>();
        tv_fensi = findViewById(R.id.tv_fensi);
        tv_guanzhu = findViewById(R.id.tv_guanzhu);
        tv_name = findViewById(R.id.tv_name);
        bt_follow = findViewById(R.id.bt_follow);
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");

        Toast.makeText(this, uid, Toast.LENGTH_SHORT).show();

        xrv = findViewById(R.id.xrv);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrv.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void initData() {

    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_get_work_info;
    }

    @Override
    public void setListener() {

    }



    @Override
    public void Click(View view) {

    }


    @Override
    public void RequestSuccess(GetWorkInfo getWorkInfo) {
        Toast.makeText(this, getWorkInfo.msg, Toast.LENGTH_SHORT).show();
        GetWorkInfo.DataBean data = getWorkInfo.data;
        List<GetWorkInfo.DataBean.WorksEntitiesBean> worksEntities = data.worksEntities;
        iv.setImageURI(data.user.icon);
        tv_fensi.setText(data.user.fans);
        tv_guanzhu.setText(data.user.follow+"");
        tv_name.setText(data.user.nickname);
        showToast(getWorkInfo.msg);
        list.addAll(worksEntities);
        if(getWorkInfoAdapter==null)
        {
            getWorkInfoAdapter=new GetWorkInfoAdapter(this,data);
            xrv.setAdapter(getWorkInfoAdapter);
        }
        else
        {
            getWorkInfoAdapter.notifyDataSetChanged();
        }
        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                presenter.getWorkInfo(uid);
                xrv.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                i++;
                presenter.getWorkInfo(uid);
                xrv.loadMoreComplete();
            }
        });

        if(data.user.follow)
        {
            bt_follow.setBackgroundColor(R.drawable.user_bg2);
            bt_follow.setText("已关注");
            bt_follow.setTextColor(Color.WHITE);
        }
    }

    @Override
    public void RequestFailure(GetWorkInfo getWorkInfo) {

        Toast.makeText(this, getWorkInfo.msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Failure(GetWorkInfo getWorkInfo) {

    }
}
