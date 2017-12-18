package com.example.dell.quarter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.facebook.drawee.view.SimpleDraweeView;

import bean.VideoDetail;
import presenter.VideoDetailPresenter;
import view.VideoDetailView;

public class VideoDetailActivity extends BaseActivity<VideoDetailPresenter> implements VideoDetailView {



    private RelativeLayout player;
    private TextView tv_title;
    private SimpleDraweeView iv;
    private View  view;
    private ImageView iv_fanhui;

    @Override
    public VideoDetailPresenter initPrsenter() {
        return new VideoDetailPresenter(this,this);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String wid = intent.getStringExtra("wid");
        Toast.makeText(this, "wid"+wid, Toast.LENGTH_SHORT).show();
        presenter.getVideoDetail(wid);
        player = findViewById(R.id.player);
        tv_title = findViewById(R.id.tv_title);
        iv = findViewById(R.id.iv);
        iv_fanhui = findViewById(R.id.iv_fanhui);

        iv_fanhui.setOnClickListener(this);


    }

    @Override
    public void initData() {

    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_video_detail;
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

    @Override
    public void RequestSuccess(VideoDetail videoDetail) {
        showToast(videoDetail.msg);
        iv.setImageURI(videoDetail.data.user.icon);
        tv_title.setText(videoDetail.data.workDesc);
        view = View.inflate(this, R.layout.simple_player_view_player, player);

        PlayerView playerView = new PlayerView(VideoDetailActivity.this,player)
                .setTitle(videoDetail.data.workDesc)
                .setScaleType(PlayStateParams.fitparent)
                .forbidTouch(false)
                .setPlaySource(videoDetail.data.videoUrl)
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

    }

    @Override
    public void RequestFailure(VideoDetail videoDetail) {

    }

    @Override
    public void Failure(VideoDetail videoDetail) {

    }
}
