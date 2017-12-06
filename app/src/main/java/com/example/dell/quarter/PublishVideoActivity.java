package com.example.dell.quarter;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


import base.BasePresenter;

public class PublishVideoActivity extends BaseActivity {

    private ImageView iv_gif;
    private ImageView iv_luzhi;
    private Uri uri;

    @Override
    public BasePresenter initPrsenter() {
        return null;
    }

    @Override
    public void initView() {
        iv_gif = findViewById(R.id.iv_shipin_gif);
        iv_luzhi = findViewById(R.id.iv_luzhi);
        iv_luzhi.setOnClickListener(this);
        Glide.with(this).load(R.drawable.timg).asGif().into(iv_gif);
    }

    @Override
    public void initData() {

    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_publish_video;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {
        switch (view.getId())
        {
            case R.id.iv_luzhi:
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);
                startActivityForResult(intent, 1);

            break;
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == PublishVideoActivity.RESULT_OK) {
                uri = null;
                if (data != null) {
                    uri = data.getData();//可以通过这个播放
                    Bundle bundle=new Bundle();

                    String[] proj = { MediaStore.Images.Media.DATA };
                    Cursor actualimagecursor = managedQuery(uri,proj,null,null,null);
                    int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    actualimagecursor.moveToFirst();
                    String img_path = actualimagecursor.getString(actual_image_column_index);

                   // bundle.putString("videourl",img_path);
                    Intent intent=new Intent(this,YuLanActivity.class);
                    intent.putExtra("videourl",img_path);
                    startActivity(intent);
                    System.out.println("+++++++++视频路径+++++++++"+uri);
                    System.out.println("+++++++++视频路径+++++++++"+img_path);
                    //startActivity(bundle,PublishCoverActivity.class);
                }
            }
        }
    }
}
