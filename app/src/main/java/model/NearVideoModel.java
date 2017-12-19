package model;

import bean.NearVideos;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetRequestUtils;

/**
 * Created by DELL on 2017/12/19.
 */

public class NearVideoModel  {

    private iGetNearVideo iGetNearVideo;


    public  void getNearVideo(String page,String latitude,String longitude){
        new NetRequestUtils.Builder().addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .addConverterFactories(GsonConverterFactory.create())
                .build().getApiService().getNearVideos(page,latitude,longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NearVideos>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NearVideos value) {
                        String code = value.getCode();
                        if("0".equals(code))
                        {
                            iGetNearVideo.getNearVideoSuccess(value);
                            System.out.println(value.getMsg()+"==========附近=========");
                            System.out.println(value.getData().size()+"==========附近=========");

                        }
                        else
                        {
                            iGetNearVideo.getNearVideoFailure(value);
                            System.out.println(value.getMsg()+"==========附近=========");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("=====附近视频======"+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setiGetNearVideo(NearVideoModel.iGetNearVideo iGetNearVideo) {
        this.iGetNearVideo = iGetNearVideo;
    }

    public interface iGetNearVideo{
        void getNearVideoSuccess(NearVideos nearVideos);
        void getNearVideoFailure(NearVideos nearVideos);

    }
}
