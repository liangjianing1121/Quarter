package model;

import com.google.gson.Gson;

import bean.HotVideo;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetRequestUtils;

/**
 * Created by DELL on 2017/12/7.
 */

public class GetHotVideoModel  {
    private iGetHotVideo iGetHotVideo;

    public  void getHotVideo(String page){

        new NetRequestUtils.Builder().addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .addConverterFactories(GsonConverterFactory.create())
                .build().getApiService().getHotVideo(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotVideo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(HotVideo value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                            iGetHotVideo.getHotVideoSuccess(value);
                            System.out.println(value.msg);
                        }
                        else
                        {
                            iGetHotVideo.getHotVideoFailure(value);
                            System.out.println(value.msg);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString()+"=======获取热门视频=====");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setiGetHotVideo(GetHotVideoModel.iGetHotVideo iGetHotVideo) {
        this.iGetHotVideo = iGetHotVideo;
    }

    public interface iGetHotVideo{
        void getHotVideoSuccess(HotVideo hotVideo);
        void getHotVideoFailure(HotVideo hotVideo);
    }
}
