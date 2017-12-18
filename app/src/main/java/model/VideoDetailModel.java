package model;

import android.provider.MediaStore;

import bean.VideoDetail;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetRequestUtils;

/**
 * Created by DELL on 2017/12/18.
 */

public class VideoDetailModel  {
    private  iGetVideoDetail iGetVideoDetail;

    public void getVideoDetail(String wid) {
        new NetRequestUtils.Builder().addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .addConverterFactories(GsonConverterFactory.create())
                .build().getApiService().getVideoDetail(wid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VideoDetail value) {

                        String code = value.code;
                        if("0".equals(code))
                        {
                         iGetVideoDetail.getVideoDetailSuccess(value);
                        }
                        else
                        {
                            iGetVideoDetail.getVideoDetailFailure(value);
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

    public void setiGetVideoDetail(VideoDetailModel.iGetVideoDetail iGetVideoDetail) {
        this.iGetVideoDetail = iGetVideoDetail;
    }

    public  interface  iGetVideoDetail{
        void getVideoDetailSuccess(VideoDetail videoDetail);
        void getVideoDetailFailure(VideoDetail videoDetail);
    }

}
