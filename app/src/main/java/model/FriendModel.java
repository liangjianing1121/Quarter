package model;

import bean.RandomFriend;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetRequestUtils;

/**
 * Created by DELL on 2017/12/16.
 */

public class FriendModel  {

    private  iRandomFriend iRandomFriend;
    public void randomFriends(){
        new NetRequestUtils.Builder().addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .addConverterFactories(GsonConverterFactory.create())
                .build().getApiService().getrandomFriends()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RandomFriend>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RandomFriend value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                            iRandomFriend.RandomFriendSuccess(value);
                        }
                        else{
                            iRandomFriend.RandomFriendFailure(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("获取随机钟友====="+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
    public void setiRandomFriend(FriendModel.iRandomFriend iRandomFriend) {
        this.iRandomFriend = iRandomFriend;
    }
    public interface iRandomFriend{
        void RandomFriendSuccess(RandomFriend randomFriend);
        void RandomFriendFailure(RandomFriend randomFriend);



    }
}
