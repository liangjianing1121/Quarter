package com.example.dell.quarter;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;
import com.igexin.sdk.PushManager;

import com.umeng.analytics.MobclickAgent;

import java.util.Timer;
import java.util.TimerTask;

import base.BasePresenter;
import utils.ImmersionUtil;

public class MainActivity extends BaseActivity {

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int num= (int) msg.obj;
            if(num==0)
            {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
    private Timer timer;



    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {

    }

    @Override
    public BasePresenter initPrsenter() {
        return null;
    }

    @Override
    public void initView() {
        ImmersionUtil.TransparentStatusbar(this);

    }

    @Override
    public void initData() {
        timer = new Timer();
        TimerTask task=new TimerTask() {
            private int num=3;
            @Override
            public void run() {
                num--;
                Message msg = new Message();
                msg.obj=num;
                handler.sendMessage(msg);

            }
        };
        timer.schedule(task,1000,1000);
    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_main;
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
