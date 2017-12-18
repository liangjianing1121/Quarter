package model;

import bean.BaseBean;
import common.Common;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetRequestUtils;

/**
 * Created by DELL on 2017/12/14.
 */

public class CommentModel {
    private getComment getComment;

    public void putComment(String uid,String wid,String content){


        new NetRequestUtils.Builder().addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .addConverterFactories(GsonConverterFactory.create())
                .build().getApiService().comment(uid,wid,content)
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
                            getComment.CommonSuccess(value);
                        }
                        else
                        {
                            getComment.commonFailure(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString()+"=========评论");

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void setGetComment(CommentModel.getComment getComment) {
        this.getComment = getComment;
    }

    public  interface getComment{
       void CommonSuccess(BaseBean baseBean);
       void commonFailure(BaseBean baseBean);
    }
}
