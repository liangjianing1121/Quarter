package com.example.dell.quarter;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import adapter.MyZuoPinAdapter;
import adapter.TabAdapter;
import base.BasePresenter;
import fragment.GuanZhuFragment;
import fragment.ReMenFragment;
import fragment.ShangChuanFragment;
import fragment.ZuoPinFragment;

public class MyZuoPinActivity extends BaseActivity {

    private View view;
    private TabLayout tablayout;
    private ViewPager vp;
    private List<Fragment> list;
    private TextView tv_fanhui;

    @Override
    public BasePresenter initPrsenter() {
        return null;
    }

    @Override
    public void initView() {
        tablayout = findViewById(R.id.tablayout);
        tv_fanhui = findViewById(R.id.tv_fanhui);
        vp = findViewById(R.id.vp);
        list = new ArrayList<>();
        list.add(new ZuoPinFragment());
        list.add(new ShangChuanFragment());
        tablayout.setupWithViewPager(vp);
        MyZuoPinAdapter tabAdapter=new MyZuoPinAdapter(getSupportFragmentManager(),this,list);
        vp.setAdapter(tabAdapter);
        MysetIndicator(tablayout,40,40);

        tv_fanhui.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_my_zuo_pin;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {
        switch (view.getId())
        {
            case  R.id.tv_fanhui:
                finish();
                break;
        }

    }

    public void MysetIndicator(TabLayout tabs,int leftDip,int rightDip){
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }

    }



}
