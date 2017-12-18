package model;

import com.example.dell.quarter.GetFollowUser;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableGroupBy;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetRequestUtils;

/**
 * Created by DELL on 2017/12/14.
 */

public class GetFollowUserModel {

    private  iGetFollowUser iGetFollowUser;

    public void getFollowUser(String uid){
        new NetRequestUtils.Builder().addConverterFactories(GsonConverterFactory.create())
                .addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .build().getApiService().getFollowUser(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetFollowUser>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetFollowUser value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                          iGetFollowUser.getFollowUserSuccess(value);
                        }
                        else
                        {
                            iGetFollowUser.getFollowUserFailuer(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString()+"======关注用户列表=====");

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setiGetFollowUser(GetFollowUserModel.iGetFollowUser iGetFollowUser) {
        this.iGetFollowUser = iGetFollowUser;
    }

    public interface iGetFollowUser{
        void getFollowUserSuccess(GetFollowUser getFollowUser);
        void getFollowUserFailuer(GetFollowUser getFollowUser);
    }
}
