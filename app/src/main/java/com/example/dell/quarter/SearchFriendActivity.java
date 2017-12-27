package com.example.dell.quarter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;
import java.util.prefs.PreferenceChangeEvent;

import adapter.RandomAdapter;
import adapter.SearchAdapter;
import base.BasePresenter;
import bean.RandomFriend;
import bean.SearchFriend;
import presenter.FriendPresenter;
import view.FriendView;

public class SearchFriendActivity extends BaseActivity<FriendPresenter> implements FriendView {


    private XRecyclerView xrv2;
    private XRecyclerView xrv1;
    private SearchView searchView;
    private TextView tv_change;
    private int i=1;

    @Override
    public FriendPresenter initPrsenter() {
        return new FriendPresenter(this,this);
    }

    @Override
    public void initView() {

        xrv2 = findViewById(R.id.xrv2);
        xrv1 = findViewById(R.id.xrv1);
        searchView = findViewById(R.id.searchView);
        tv_change = findViewById(R.id.tv_change);

        tv_change.setOnClickListener(this);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrv2.setLayoutManager(linearLayoutManager);
        xrv2.setLoadingMoreEnabled(false);
        xrv2.setPullRefreshEnabled(false);

        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        xrv1.setLayoutManager(linearLayoutManager1);
        xrv1.setPullRefreshEnabled(false);
        xrv1.setLoadingMoreEnabled(false);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.searchFriend(query,1+"");

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        presenter.getRandomFriend();
    }

    @Override
    public void initData() {

    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_search_friend;

    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {
        switch (view.getId())
        {
            case R.id.tv_change:
                presenter.getRandomFriend();
                break;
        }
    }

    @Override
    public void RequestSuccess(Object o) {

    }

    @Override
    public void RequestFailure(Object o) {

    }

    @Override
    public void Failure(Object o) {

    }

    /**
     * 获取随机钟友
     * @param randomFriend
     */
    @Override
    public void RandomFriendSuccess(RandomFriend randomFriend) {
        showToast(randomFriend.msg);
        List<RandomFriend.DataBean> data = randomFriend.data;
        RandomAdapter randomAdapter=new RandomAdapter(this,data);
        xrv2.setAdapter(randomAdapter);

    }

    @Override
    public void RandomFriendFailure(RandomFriend randomFriend) {
        showToast(randomFriend.msg);
    }

    /**
     * 查找钟友
     * @param searchFriend
     */
    @Override
    public void SearchFriendSuccess(SearchFriend searchFriend) {
        //showToast(searchFriend.msg+"           "+searchFriend.data.size());
        System.out.println(searchFriend.msg+"           "+searchFriend.data.size());
        List<SearchFriend.DataBean> data = searchFriend.data;
        System.out.println(data.size()+"===============钟友=================");
        SearchAdapter searchAdapter=new SearchAdapter(this,data);
        xrv1.setAdapter(searchAdapter);
    }

    @Override
    public void SearchFriendFailure(SearchFriend searchFriend) {
        showToast(searchFriend.msg);
    }
}
