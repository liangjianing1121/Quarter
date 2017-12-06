package com.example.dell.quarter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;

import base.BasePresenter;

public class YuLanActivity extends BaseActivity {


    private RelativeLayout rl;
    private Button bt_xiayibu;
    private String videourl;

    @Override
    public BasePresenter initPrsenter() {
        return null;
    }

    @Override
    public void initView() {
        rl = findViewById(R.id.rl);
        bt_xiayibu = findViewById(R.id.bt_xiayibu);
        Intent intent = getIntent();
        videourl = intent.getStringExtra("videourl");
        View player = View.inflate(this, R.layout.simple_player_view_player,rl);

        PlayerView playerView = new PlayerView(this, player);
        playerView.setScaleType(PlayStateParams.fitparent);
        playerView.hideMenu(true);
        playerView.forbidTouch(false);
        playerView.setPlaySource(videourl);
        playerView.startPlay();

        bt_xiayibu.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_yu_lan;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {
        switch (view.getId())
        {
            case R.id.bt_xiayibu:
                Bundle bundle=new Bundle();
                bundle.putString("videourl",videourl);
                startActivity(bundle,PublishCoverActivity.class);


                break;
        }

    }
}
