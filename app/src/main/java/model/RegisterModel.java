package model;

import android.content.Intent;

import bean.Register;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetRequestUtils;

/**
 * Created by DELL on 2017/11/28.
 */

public class RegisterModel {

    private iRegister iRegister;

    public void  getRegister(String regType,String mobile,String password){
        new NetRequestUtils.Builder().addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .addConverterFactories(GsonConverterFactory.create())
                .build().getApiService().getRegister(regType,mobile,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Register>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Register value) {
                        String code = value.code;
                        String msg = value.msg;
                        if("0".equals(code))
                        {
                            iRegister.registerSuccess(msg);
                        }
                        else if("1".equals(code))
                        {
                            iRegister.registerFailure(msg);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iRegister.Failure(e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setiRegister(RegisterModel.iRegister iRegister) {
        this.iRegister = iRegister;
    }

    public interface iRegister{
        void registerSuccess(String msg);
        void registerFailure(String msg);
        void Failure(Throwable e);
    }
}
