package model;

import android.net.http.DelegatingSSLSession;
import android.text.AndroidCharacter;

import bean.BaseBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetRequestUtils;

/**
 * Created by DELL on 2017/12/15.
 */

public class CommentJokeModel {

    private iCommentJoke iCommentJoke;
    public void commentJoke(String uid,String jid,String content){

        new NetRequestUtils.Builder().addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .addConverterFactories(GsonConverterFactory.create())
                .build().getApiService().commentJoke(uid,jid,content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(BaseBean value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                            iCommentJoke.CommentJokeSuccess(value);
                        }
                        else
                        {
                            iCommentJoke.CommentJokeFailure(value);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString()+"====段子评论====");

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void setiCommentJoke(CommentJokeModel.iCommentJoke iCommentJoke) {
        this.iCommentJoke = iCommentJoke;
    }

    public interface iCommentJoke{
        void CommentJokeSuccess(BaseBean baseBean);
        void CommentJokeFailure(BaseBean baseBean);
    }




}
