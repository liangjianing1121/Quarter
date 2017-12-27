package com.example.dell.quarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;


import java.util.ArrayList;
import java.util.List;

import base.BasePresenter;
import bean.User;
import de.greenrobot.event.EventBus;
import fragment.Fragment1;
import fragment.Fragment2;
import fragment.Fragment3;
import fragment.Fragment4;
import fragment.LeftFragment;
import presenter.UserPresenter;
import utils.ActivityCollector;
import utils.ImmersionUtil;
import view.UserView;

public class HomeActivity extends BaseActivity<UserPresenter> implements UserView {
    private SimpleDraweeView iv;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private DrawerLayout drawerLayout;
    private TextView tv_title;
    private ImageView iv_publish;
    private TextView tv4;
    private ImageView iv4;


    @Override
    public void setListener() {
    }

    @Override
    public void Click(View view) {
        switch (view.getId())
        {
            case R.id.iv:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.ll_tuijian:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new Fragment1()).commit();
                iv1.setImageResource(R.drawable.tuijianlian);
                iv2.setImageResource(R.drawable.duanzi);
                iv3.setImageResource(R.drawable.shipin);
                iv4.setImageResource(R.drawable.find);

                tv1.setTextColor(Color.rgb(18,150,219));
                tv2.setTextColor(Color.rgb(191,201,217));
                tv3.setTextColor(Color.rgb(191,201,217));
                tv4.setTextColor(Color.rgb(191,201,217));

                tv_title.setText("推荐");
                break;
            case R.id.ll_duanzi:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new Fragment2()).commit();
                iv1.setImageResource(R.drawable.tuijian);
                iv2.setImageResource(R.drawable.duanzilan);
                iv3.setImageResource(R.drawable.shipin);
                iv4.setImageResource(R.drawable.find);

                tv1.setTextColor(Color.rgb(191,201,217));
                tv2.setTextColor(Color.rgb(18,150,219));
                tv3.setTextColor(Color.rgb(191,201,217));
                tv4.setTextColor(Color.rgb(191,201,217));
                tv_title.setText("段子");
                break;
            case R.id.ll_shipin:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new Fragment3()).commit();
                iv1.setImageResource(R.drawable.tuijian);
                iv2.setImageResource(R.drawable.duanzi);
                iv3.setImageResource(R.drawable.shipinlan);
                iv4.setImageResource(R.drawable.find);

                tv1.setTextColor(Color.rgb(191,201,217));
                tv2.setTextColor(Color.rgb(191,201,217));
                tv3.setTextColor(Color.rgb(18,150,219));
                tv4.setTextColor(Color.rgb(191,201,217));
                tv_title.setText("视频");
                break;
            case R.id.ll_faxian:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new Fragment4()).commit();
                iv1.setImageResource(R.drawable.tuijian);
                iv2.setImageResource(R.drawable.duanzi);
                iv3.setImageResource(R.drawable.shipin);
                iv4.setImageResource(R.drawable.find2);

                tv1.setTextColor(Color.rgb(191,201,217));
                tv2.setTextColor(Color.rgb(191,201,217));
                tv3.setTextColor(Color.rgb(191,201,217));
                tv4.setTextColor(Color.rgb(18,150,219));
                tv_title.setText("发现");
                break;
            case R.id.iv_publish:
                startActivity(PublishActivity.class);
                break;
        }
    }


    @Override
    public UserPresenter initPrsenter() {
        return new UserPresenter(this,this);
    }

    @Override
    public void initView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new LeftFragment()).commit();

       /* Intent intent = getIntent();
        boolean isYouke = intent.getBooleanExtra("isYouke", false);
        if(!isYouke)
        {
            SharedPreferences uidsp = getSharedPreferences("uid", MODE_PRIVATE);
            int uid = uidsp.getInt("uid", 0);
            presenter.getUser(uid+"");
        }*/


        ImmersionUtil.TransparentStatusbar(this);
        //ImmersionUtil.FullScreenMode(true,this);
        iv = findViewById(R.id.iv);
        LinearLayout ll_tuijian = findViewById(R.id.ll_tuijian);
        LinearLayout ll_duanzi = findViewById(R.id.ll_duanzi);
        LinearLayout ll_shipin = findViewById(R.id.ll_shipin);
        LinearLayout ll_faxian = findViewById(R.id.ll_faxian);
        iv_publish = findViewById(R.id.iv_publish);
        tv_title = findViewById(R.id.tv_title);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        tv4 = findViewById(R.id.tv4);
        iv4 = findViewById(R.id.iv4);
        iv_publish.setOnClickListener(this);
        drawerLayout = findViewById(R.id.drawerLayout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);

        iv.setOnClickListener(this);
        ll_duanzi.setOnClickListener(this);
        ll_shipin.setOnClickListener(this);
        ll_tuijian.setOnClickListener(this);
        ll_faxian.setOnClickListener(this);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                drawerView.setClickable(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new LeftFragment()).commit();


            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new Fragment1()).commit();
        iv1.setImageResource(R.drawable.tuijianlian);
        tv1.setTextColor(Color.rgb(18,150,219));
        tv_title.setText("推荐");

    }


    @Override
    public void initData() {

    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_home;
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        Intent intent = getIntent();
        boolean isYouke = intent.getBooleanExtra("isYouke", false);
        if(!isYouke)
        {
            SharedPreferences uidsp = getSharedPreferences("uid", MODE_PRIVATE);
            int uid = uidsp.getInt("uid", 0);
            presenter.getUser(uid+"");
        }
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    @Override
    public void RequestSuccess(User user) {
        String msg = user.msg;
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        User.DataBean data = user.data;
        String nickname = data.nickname;
        String icon = data.icon;
        iv.setImageURI(icon);

        if("2".equals(user.code))
        {
            Intent intent=new Intent(this,OtherLoginActivity.class);
            startActivity(intent);
        }

        EventBus.getDefault().postSticky(user);


    }

    @Override
    public void RequestFailure(User user) {

    }

    @Override
    public void Failure(User user) {

    }

    private long exitTime = 0;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            ActivityCollector.finishAll();
            System.exit(0);
        }
    }
}
