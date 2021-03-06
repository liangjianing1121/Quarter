package com.example.dell.quarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.igexin.sdk.aidl.IGexinMsgService;

import java.util.Observable;

import bean.Login;
import presenter.LoginPresenter;
import utils.ImmersionUtil;
import utils.MapUtils;
import view.LoginView;

public class OtherLoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    private TextView tv_register;
    private ImageView iv_fh1;
    private TextView tv_youke;
    private EditText et_mobile;
    private EditText et_psd;
    private Button bt_login;

    @Override
    public void setListener() {

    }
    @Override
    public void Click(View view) {
        switch (view.getId())
        {
            case R.id.tv_register:
                startActivity(RegisterActivity.class);
                break;
            case R.id.iv_fh1:
                finish();
                break;
            case R.id.tv_youke:
                Bundle bundle=new Bundle();
                bundle.putBoolean("isYouke",true);
                startActivity(bundle,HomeActivity.class);
                break;
            case R.id.but_log:
                String mobile = et_mobile.getText().toString();
                String psd = et_psd.getText().toString();
                presenter.login(mobile,psd);
                break;
        }
    }
    @Override
    public LoginPresenter initPrsenter() {
        return new LoginPresenter(this,this);
    }

    @Override
    public void initView() {
        ImmersionUtil.TransparentStatusbar(this);
        tv_register = findViewById(R.id.tv_register);
        iv_fh1 = findViewById(R.id.iv_fh1);
        tv_youke = findViewById(R.id.tv_youke);
        et_mobile = findViewById(R.id.et_mobile);
        et_psd = findViewById(R.id.et_psd);
        bt_login = findViewById(R.id.but_log);
        iv_fh1.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_youke.setOnClickListener(this);
        bt_login.setOnClickListener(this);
    }

    @Override
    public void initData() {
        String sha1 = MapUtils.getSHA1(this);
        System.out.println(sha1+"++++++++++++++++++++++++++");
    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_author_login;
    }


    @Override
    public void RequestSuccess(Login login) {
        Toast.makeText(this, login.msg, Toast.LENGTH_SHORT).show();
        SharedPreferences tokensp = getSharedPreferences("token", MODE_PRIVATE);
        SharedPreferences.Editor edit = tokensp.edit();
        edit.putString("token",login.data.token);
        edit.commit();
        startActivity(HomeActivity.class);
        SharedPreferences uidsp = getSharedPreferences("uid", MODE_PRIVATE);
        SharedPreferences.Editor edit1 = uidsp.edit();
        edit1.putInt("uid",login.data.uid);
        edit1.commit();


        Login.DataBean data = login.data;
        String mobile = data.mobile;
        String password = data.password;

        EMClient.getInstance().login(mobile,password, new EMCallBack() {
            @Override
            public void onSuccess() {
              //  startActivity(new Intent(OtherLoginActivity.this,MainActivity.class));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(OtherLoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                System.out.println("登录失败"+s);

            }

            @Override
            public void onProgress(int i, String s) {

            }
        });

    }

    @Override
    public void RequestFailure(Login login) {
        Toast.makeText(this, login.msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void Failure(Login login) {

    }



}
