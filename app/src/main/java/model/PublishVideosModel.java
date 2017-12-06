package model;

import android.webkit.JavascriptInterface;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import bean.PublishVideos;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetRequestUtils;

/**
 * Created by DELL on 2017/12/5.
 */

public class PublishVideosModel {

    private iPublishVideo iPublishVideo;

    public void pulishVideos(String uid, File videoFile, File coverFile,
                             String videoDesc,String latitude, String longitude){


        MultipartBody.Builder b = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("uid",uid)
                .addFormDataPart("videoDesc",videoDesc)
                .addFormDataPart("latitude",latitude)
                .addFormDataPart("longitude",longitude);
        RequestBody video=RequestBody.create(MediaType.parse("multipart/form-data"),videoFile);
        b.addFormDataPart("videoFile",videoFile.getName(),video);



        RequestBody cover=RequestBody.create(MediaType.parse("multipart/form-data"),coverFile);
        b.addFormDataPart("coverFile",coverFile.getName(),cover);


        List<MultipartBody.Part> parts = b.build().parts();
        new NetRequestUtils.Builder().addConverterFactories(GsonConverterFactory.create())
                .addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .build().getApiService().publishVideo(parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PublishVideos>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(PublishVideos value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                            iPublishVideo.publishVoideoSuccess(value);
                        }
                        else
                        {
                            iPublishVideo.publishVideoFailure(value);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("视频上传失败"+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void setiPublishVideo(PublishVideosModel.iPublishVideo iPublishVideo) {
        this.iPublishVideo = iPublishVideo;
    }

    public interface iPublishVideo{
       void publishVoideoSuccess(PublishVideos publishVideos);
       void publishVideoFailure(PublishVideos publishVideos);
    }
}
