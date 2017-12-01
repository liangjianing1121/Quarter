package com.example.dell.quarter;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import base.BasePresenter;
import bean.UpLoad;
import bean.UpdateNickname;
import bean.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import presenter.ChangUserPresenter;
import utils.Utils;
import view.ChangUserView;

public class ChangeUserActivity extends BaseActivity<ChangUserPresenter> implements ChangUserView {


    @BindView(R.id.iv_head)
    SimpleDraweeView ivHead;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl2)
    RelativeLayout rl2;
    private Uri tempUri;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;


    @Override
    public ChangUserPresenter initPrsenter() {
        return new ChangUserPresenter(this,this);
    }

    @Override
    public void initView() {
        System.out.println("进入这个页面");
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_change_user;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {

    }

    @OnClick({R.id.rl1, R.id.rl2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl1:

                showChoosePicDialog();



                break;
            case R.id.rl2:
                final EditText inputServer = new EditText(this);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("修改昵称").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer)
                        .setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences uid = getSharedPreferences("uid", MODE_PRIVATE);
                        int uid1 = uid.getInt("uid", 0);
                        String nickname = inputServer.getText().toString();
                        tvName.setText(nickname);
                        presenter.getUpdateNickNamse(uid1+"",nickname);
                    }
                });
                builder.show();
                break;
        }
    }

    private void showChoosePicDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }


    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Utils utils=new Utils();
            photo = utils.toRoundBitmap(photo); // 这个时候的图片已经被处理成圆形的了
            ivHead.setImageBitmap(photo);
            setFile(photo);
            uploadPic();
        }
    }

    private void uploadPic() {
        File img=new File("mnt/sdcard/mo.jpg");
        System.out.println(img.getName()+img.getAbsolutePath().toString()+"+++++图片的地址++++");
        SharedPreferences uid = getSharedPreferences("uid", MODE_PRIVATE);
        int uid1 = uid.getInt("uid", 0);
        presenter.upload(uid1+"",img);

    }


    private void setFile(Bitmap photo) {
        File file=new File("mnt/sdcard/mo.jpg");
        try {
            BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream(file));
            photo.compress(Bitmap.CompressFormat.JPEG,100,bout);
            bout.flush();
            bout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MainThread,sticky = true)
    public void onEvent(User user){
        System.out.println(user.data.icon+user.data.nickname);
        ivHead.setImageURI(user.data.icon);
        tvName.setText(user.data.nickname);
    }

    /**
     * BaseView 返回的方法
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
     * 修改昵称返回的方法
     * @param updateNickname
     */
    @Override
    public void changnameSuccess(UpdateNickname updateNickname) {

        showToast(updateNickname.msg+"昵称修改成功");
    }

    @Override
    public void changnameFailure(UpdateNickname updateNickname) {
        showToast(updateNickname.msg+"昵称修改失败");
    }

    /**
     * 修改头像返回的方法
     * @param upLoad
     */
    @Override
    public void uploadSuccess(UpLoad upLoad) {
        showToast(upLoad.msg+"头像上传成功");
    }

    @Override
    public void uploadFailure(UpLoad upLoad) {
        showToast(upLoad.msg+"头像上传失败");
    }
}
