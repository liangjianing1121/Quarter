package com.example.dell.quarter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.igexin.sdk.aidl.IGexinMsgService;

import base.BasePresenter;
import bean.Register;
import presenter.RegisterPresenter;
import utils.ImmersionUtil;
import view.RegisterView;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView {

    private TextView tv_register;
    private ImageView iv_fh;
    private Button bt_reg;
    private EditText et_phone;
    private EditText et_psd;
    private String mobile;
    private String psd;

    @Override
    public RegisterPresenter initPrsenter() {
        return new RegisterPresenter(this,this);
    }

    @Override
    public void initView() {
        ImmersionUtil.TransparentStatusbar(this);
        tv_register = findViewById(R.id.tv_register);
        iv_fh = findViewById(R.id.iv_fh1);
        bt_reg = findViewById(R.id.bt_reg);
        et_phone = findViewById(R.id.et_phone);
        et_psd = findViewById(R.id.et_psd);
        tv_register.setOnClickListener(this);
        iv_fh.setOnClickListener(this);
        bt_reg.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public int setLayoutID() {
        return R.layout.activity_register;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void Click(View view) {
        switch (view.getId())
        {
            case R.id.tv_register:
                finish();
                break;
            case R.id.iv_fh1:
                finish();
                break;
            case R.id.bt_reg:
                mobile = et_phone.getText().toString();
                psd = et_psd.getText().toString();
                presenter.Refgister(mobile,psd);
            break;
        }
    }


    @Override
    public void RequestSuccess(Object o) {
        Toast.makeText(this, o.toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void RequestFailure(Object o) {
        Toast.makeText(this, o.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Failure(Object o) {
        Toast.makeText(this, o.toString(), Toast.LENGTH_SHORT).show();
    }
}
