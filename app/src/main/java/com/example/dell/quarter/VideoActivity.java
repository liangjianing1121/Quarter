package com.example.dell.quarter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.facebook.drawee.view.SimpleDraweeView;

import base.BasePresenter;

public class VideoActivity extends BaseActivity {

    private RelativeLayout player;
    private TextView tv_title;
    private SimpleDraweeView iv;
    private View  view;
    private ImageView iv_fanhui;

    @Override
    public BasePresenter initPrsenter() {
        return null;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String videourl = intent.getStringExtra("videourl");
        String icon = intent.getStringExtra("icon");
        String desc = intent.getStringExtra("desc");

        player = findViewById(R.id.player);
        tv_title = findViewById(R.id.tv_title);
        iv = findViewById(R.id.iv);
        iv_fanhui = findViewById(R.id.iv_fanhui);

        iv.setImageURI(icon);
        iv_fanhui.setOnClickListener(this);


        view = View.inflate(this, R.layout.simple_player_view_player, player);

        PlayerView playerView = new PlayerView(VideoActivity.this,player)
                .setTitle(desc)
                .setScaleType(PlayStateParams.fitparent)
                .forbidTouch(false)
                .setPlaySource(videourl)
                .startPlay();

        //隐藏返回键，true 隐藏，false 为显示
        playerView.hideBack(true);
        //隐藏菜单键，true 隐藏，false 为显示
        playerView.hideMenu(true);
        //隐藏分辨率按钮，true 隐藏，false 为显示
        playerView.hideSteam(true);
        //隐藏旋转按钮，true 隐藏，false 为显示
        playerView.hideRotation(true);
        //隐藏全屏按钮，true 隐藏，false 为显示
        playerView.hideFullscreen(true);
        //隐藏中间播放按钮,ture 为隐藏，false 为不做隐藏处理，但不是显示
        playerView.hideCenterPlayer(false);
        //隐藏全屏按钮，true 隐藏，false 为显示
        playerView.hideFullscreen(true);
        //设置是否禁止双击
        playerView.setForbidDoulbeUp(true);
        //隐藏全屏按钮，true 隐藏，false 为显示
        playerView.hideFullscreen(true);

        tv_title.setText(desc);
    }

    @Override
    public void initData() {

    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_video;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {
        switch (view.getId())
        {
            case R.id.iv_fanhui:
                finish();
                break;

        }

    }

}
