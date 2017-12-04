package model;

import com.google.gson.Gson;

import bean.Ad;
import bean.Videos;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetRequestUtils;

/**
 * Created by DELL on 2017/12/2.
 */

public class GetVideosModel {
    private igetVideos igetVideos;

    public void getVideo(String uid,String type,String page){
        new NetRequestUtils.Builder().addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .addConverterFactories(GsonConverterFactory.create())
                .build()
                .getApiService().getVideos(uid,type,page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Videos>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Videos value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                            igetVideos.getVideosSuccess(value);
                        }
                        else
                        {
                            igetVideos.getVideosFailure(value);
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

    public void getAd(){
        new NetRequestUtils.Builder().addConverterFactories(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .build().getApiService().getAd()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Ad>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Ad value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                            igetVideos.getAdSuccess(value);
                            System.out.println(value.data.size()+"广告");
                            System.out.println(value.msg+"======成功广告 ======");
                        }
                        else
                        {
                            igetVideos.getAdFailure(value);
                            System.out.println(value.msg+"======失败广告 ======");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString()+"======广告 ======");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setIgetVideos(GetVideosModel.igetVideos igetVideos) {
        this.igetVideos = igetVideos;
    }

    public interface  igetVideos{
        void getVideosSuccess(Videos videos);
        void getVideosFailure(Videos videos);

        void getAdSuccess(Ad ad);
        void getAdFailure(Ad ad);
    }
}
