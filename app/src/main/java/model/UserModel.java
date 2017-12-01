package model;

import android.content.Context;
import android.content.Intent;

import bean.User;
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

public class UserModel {
    private  igetUser igetUser;

    public void getUser(String uid){
        new NetRequestUtils.Builder().addConverterFactories(GsonConverterFactory.create())
                .addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .build().getApiService().getUser(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(User value) {
                        String code = value.code;
                        if("0".equals(code)){
                            igetUser.getUserSuccess(value);
                        }
                        else if("1".equals(code)) {
                            igetUser.getUserFailure(value);
                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void setIgetUser(UserModel.igetUser igetUser) {
        this.igetUser = igetUser;
    }

    public interface igetUser{
        void getUserSuccess(User user);
        void getUserFailure(User user);

    }

}
