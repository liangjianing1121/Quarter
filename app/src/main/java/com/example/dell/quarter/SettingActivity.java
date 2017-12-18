package com.example.dell.quarter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.AsynchronousChannelGroup;

import base.BasePresenter;
import bean.Version;
import common.Common;
import presenter.VersionPresenter;
import utils.ClearCacheUtils;
import utils.SharedPreferencesUtil;
import view.VersionView;

public class SettingActivity extends BaseActivity<VersionPresenter> implements VersionView {


    private TextView tv_huancun;
    private RelativeLayout rl4;
    private Button bt_exit;
    private RelativeLayout rl1;
    private Handler handler;
    private ProgressDialog progressDialog;
    private String appName;
    private String versionCode;
    private String apkUrl;
    private String versionName;


    @Override
    public VersionPresenter initPrsenter() {
        return new VersionPresenter(this,this);
    }

    @Override
    public void initView() {
        tv_huancun = findViewById(R.id.tv_huancun);
        rl4 = findViewById(R.id.rl4);
        bt_exit = findViewById(R.id.bt_exit);
        rl1 = findViewById(R.id.rl1);
        handler = new Handler();
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(false);
        bt_exit.setOnClickListener(this);
        rl4.setOnClickListener(this);
        rl1.setOnClickListener(this);
        appName = "quarter.apk";

    }

    @Override
    public void initData() {
        try {
            String totalCacheSize = ClearCacheUtils.getTotalCacheSize(this);
            tv_huancun.setText(totalCacheSize);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_setting;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {
        switch (view.getId())
        {
            case R.id.rl4:
                ClearCacheUtils.clearAllCache(this);
                try {
                    String totalCacheSize = ClearCacheUtils.getTotalCacheSize(this);
                    tv_huancun.setText(totalCacheSize);
                    showToast("缓存已清除");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_exit:
                SharedPreferencesUtil.clearPreferences("uid");
                showToast("退出登录");
                startActivity(OtherLoginActivity.class);
                break;
            case R.id.rl1:

                presenter.getVersion();
                //new checkNewestVersionAsyncTask().execute();
                break;

        }

    }

    @Override
    public void RequestSuccess(Version version) {
        Version.DataBean data = version.data;
        versionCode = data.versionCode;
        apkUrl = data.apkUrl;
        versionName = data.versionName;
        int verCode = Common.getVerCode(getApplicationContext());
        //System.out.println(verCode+"++++++++++Version+++++++++"+versionCode);
        if(Integer.parseInt(versionCode)>verCode)
        {
            doNewVersionUpdate();
        }
        else
        {

            notNewVersionDlgShow();
        }
    }


    private void notNewVersionDlgShow()
    {
        int verCode = Common.getVerCode(this);
        String verName = Common.getVerName(this);
        String str="当前版本:"+verName+" Code:"+verCode+",/n已是最新版,无需更新!";
        Dialog dialog = new AlertDialog.Builder(this).setTitle("软件更新")
                .setMessage(str)// 设置内容
                .setPositiveButton("确定",// 设置确定按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                finish();
                            }
                        }).create();// 创建
        // 显示对话框
        dialog.show();
    }
    @Override
    public void RequestFailure(Version version) {

    }

    @Override
    public void Failure(Version version) {

    }

   /* class checkNewestVersionAsyncTask extends AsyncTask<Void,Void,Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {
            int verCode = Common.getVerCode(getApplicationContext());
            System.out.println(verCode+"++++++++++Version+++++++++"+versionCode);
            if(Integer.parseInt(versionCode)>verCode)
            {
                return  true;
            }
            else
            {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if(result)
            {
                doNewVersionUpdate();
            }
        }
    }
*/
    private void doNewVersionUpdate() {
        int verCode = Common.getVerCode(getApplicationContext());
        String verName = Common.getVerName(getApplicationContext());

        String str= "当前版本："+verName+" Code:"+verCode+" ,发现新版本："+versionName+
                " Code:"+versionCode+" ,是否更新？";
        Dialog dialog = new AlertDialog.Builder(this).setTitle("软件更新").setMessage(str)
                // 设置内容
                .setPositiveButton("更新",// 设置确定按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                progressDialog.setTitle("正在下载");
                                progressDialog.setMessage("请稍候...");
                                downFile(apkUrl);  //开始下载
                            }
                        })
                .setNegativeButton("暂不更新",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                // 点击"取消"按钮之后退出程序
                                finish();
                            }
                        }).create();// 创建
        // 显示对话框
        dialog.show();
    }



    private void downFile(final String url)
    {
        System.out.println("++++++++开始下载+++++++++");
        progressDialog.show();
        new Thread() {
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
                HttpResponse response;
                try {
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    long length = entity.getContentLength();

                    progressDialog.setMax((int)length);//设置进度条的最大值

                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {
                        File file = new File(
                                Environment.getExternalStorageDirectory(),
                                appName);
                        fileOutputStream = new FileOutputStream(file);
                        byte[] buf = new byte[1024];
                        int ch = -1;
                        int count = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            count += ch;
                            if (length > 0) {
                                progressDialog.setProgress(count);
                            }
                        }
                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    down();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    private void down() {
        handler.post(new Runnable() {
            public void run() {
                progressDialog.cancel();
                update();
            }
        });
    }

    void update() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory(), appName)),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }


}
