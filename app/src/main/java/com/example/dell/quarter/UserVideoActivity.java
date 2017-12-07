package com.example.dell.quarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.UserVideoAdapter;
import bean.Follow;
import bean.User;
import bean.UserVideos;
import bean.Videos;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import presenter.GetUserVideoPresenter;
import utils.ImmersionUtil;
import utils.SharedPreferencesUtil;
import view.GetUserVideoView;

public class UserVideoActivity extends BaseActivity<GetUserVideoPresenter> implements GetUserVideoView {

    private TextView tv_name;
    private XRecyclerView xrv;
    private UserVideoAdapter userVideoAdapter;
    private List<UserVideos.DataBean> list;
    private int i=1;
    private SimpleDraweeView iv;
    private int uid1;
    private TextView tv_fensi;
    private TextView tv_guanzhu;
    private User user2;
    private String token;
    private Button bt_follow;

    @Override
    public GetUserVideoPresenter initPrsenter() {
        return new GetUserVideoPresenter(this,this);
    }

    @Override
    public void initView() {
        //ImmersionUtil.TransparentStatusbar(this);
        list=new ArrayList<>();
        user2=new User();
        tv_name = findViewById(R.id.tv_name);
        xrv = findViewById(R.id.xrv);
        iv = findViewById(R.id.iv);
        tv_fensi = findViewById(R.id.tv_fensi);
        tv_guanzhu = findViewById(R.id.tv_guanzhu);
        bt_follow = findViewById(R.id.bt_follow);
        xrv.setPullRefreshEnabled(true);
        xrv.setLoadingMoreEnabled(true);

        bt_follow.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrv.setLayoutManager(linearLayoutManager);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_user_video;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {
        switch (view.getId())
        {
            case R.id.bt_follow:
                SharedPreferences uidsp = getSharedPreferences("uid", MODE_PRIVATE);
                int uid = uidsp.getInt("uid", 0);
                System.out.println("要关注的用户id"+uid1);
                presenter.getFollow(uid+"",uid1+"");
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MainThread,sticky = true)
    public void getEevent(Videos.DataBean dataBean){
        String nickname = (String) dataBean.user.nickname;
        uid1 = dataBean.uid;
        iv.setImageURI(dataBean.user.icon);
        tv_name.setText(nickname);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getUser(uid1+"");

    }

    /**
     * BaseView返回的方法
     * @param o
     */
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
     * 获取用户视频列表
     * @param userVideos
     */
    @Override
    public void getUserVideosSuccess(UserVideos userVideos) {
        showToast(userVideos.msg);
        list.addAll(userVideos.data);
        if(userVideoAdapter==null)
        {
            userVideoAdapter=new UserVideoAdapter(this,list,user2);
            xrv.setAdapter(userVideoAdapter);
        }
        else
        {
            userVideoAdapter.notifyDataSetChanged();
        }
        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                System.out.println("获取到用户的uid====================="+uid1);
                presenter.getUserVideo(uid1+"",1+"");
                xrv.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                i++;
                presenter.getUserVideo(uid1+"",i+"");
                xrv.loadMoreComplete();
            }
        });

    }

    @Override
    public void getUserVideoFailure(UserVideos videos) {
        showToast(videos.msg);
    }

    /**
     * 获取用户信息
     * @param user
     */
    @Override
    public void getUserSuccess(User user) {
        user2=user;
        showToast(user.msg);
        User.DataBean data = user.data;
        int fans = data.fans;
        int follow = data.follow;
        tv_fensi.setText("  "+fans+"  粉丝  |   ");
        tv_guanzhu.setText(follow+"     关注");
        token = user.data.token;

        System.out.println(token+"======获取用户token======");

        SharedPreferences tokensp = getSharedPreferences("token", MODE_PRIVATE);
        SharedPreferences.Editor edit = tokensp.edit();
        edit.putString("token",token).commit();
        presenter.getUserVideo(uid1+"",1+"");
    }
    @Override
    public void getUserFailure(User user) {
        showToast(user.msg);
    }

    /**
     * 关注返回的接口
     * @param follow
     */
    @Override
    public void FollowSuccess(Follow follow) {
        showToast(follow.msg);

    }

    @Override
    public void FollowFailure(Follow follow) {
        showToast(follow.msg);

    }
}
