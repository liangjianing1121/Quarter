package model;

import java.io.File;
import java.util.List;

import bean.PublishJoke;
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
 * Created by DELL on 2017/11/28.
 */

public class PublishJokeModel {
    private publishjokemsg publishjokemsg;


    public void getupdateJoke(String uid,String content,List<String> path){

        MultipartBody.Builder b = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("uid",uid)
                .addFormDataPart("content",content);

        for (int i = 0; i < path.size(); i++) {
            File file1 = new File(path.get(i));
            System.out.println(file1.getName()+"++++++++++++++++++++");
            RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file1);
            b.addFormDataPart("jokeFiles",file1.getName(),requestBody);
        }
        List<MultipartBody.Part> parts = b.build().parts();
        new NetRequestUtils.Builder().addConverterFactories(GsonConverterFactory.create())
                .addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .build().getApiService().updateJoke(parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PublishJoke>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PublishJoke value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                            publishjokemsg.publishjokemsgSuccess(value);
                            System.out.println(value.msg+"=======");
                        }
                        else if("1".equals(code))
                        {
                            publishjokemsg.publishjokemsgFailure(value);
                            System.out.println(value.msg+"===========");
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.println("段子请求失败+++++++++++++"+e.toString());
                    }
                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void setPublishjokemsg(PublishJokeModel.publishjokemsg publishjokemsg) {
        this.publishjokemsg = publishjokemsg;
    }

    public interface publishjokemsg{
        void publishjokemsgSuccess(PublishJoke publishJoke);
        void publishjokemsgFailure(PublishJoke publishJoke);

    }
}
