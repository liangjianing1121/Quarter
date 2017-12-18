package model;

import bean.Follow;
import bean.User;
import bean.UserVideos;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetRequestUtils;

/**
 * Created by DELL on 2017/12/6.
 */

public class GetUserVideosModel {
    private iGetUserVideos iGetUserVideos;

    public void getUserVideo(String uid,String page){

        new NetRequestUtils.Builder().addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .addConverterFactories(GsonConverterFactory.create())
                .build().getApiService().getUserVideos(uid,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserVideos>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(UserVideos value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                            iGetUserVideos.getUserVideosSuccess(value);
                        }
                        else
                        {
                            iGetUserVideos.getUserVideoFailure(value);
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
                            iGetUserVideos.getUserSuccess(value);
                        }
                        else if("1".equals(code)) {
                            iGetUserVideos.getUserFailure(value);
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

    public void getFollow(String uid,String followid){
        new NetRequestUtils.Builder().addConverterFactories(GsonConverterFactory.create())
                .addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .build().getApiService().geteFollow(uid,followid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Follow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Follow value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                            iGetUserVideos.FollowSuccess(value);
                        }
                        else
                        {
                            iGetUserVideos.FollowFailure(value);
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
    public void setiGetUserVideos(GetUserVideosModel.iGetUserVideos iGetUserVideos) {
        this.iGetUserVideos = iGetUserVideos;
    }

    public  interface  iGetUserVideos{
        void getUserVideosSuccess(UserVideos userVideos);
        void getUserVideoFailure(UserVideos videos);

        void getUserSuccess(User user);
        void getUserFailure(User user);

        void FollowSuccess(Follow follow);
        void FollowFailure(Follow follow);



    }
}
