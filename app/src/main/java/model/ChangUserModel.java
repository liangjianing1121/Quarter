package model;

import java.io.File;

import bean.UpLoad;
import bean.UpdateNickname;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.CallAdapter;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetRequestUtils;

/**
 * Created by DELL on 2017/11/30.
 */

public class ChangUserModel {

    private upload upload;

    public void updateNickName(String uid,String name){
        new NetRequestUtils.Builder().addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .addConverterFactories(GsonConverterFactory.create())
                .build().getApiService().updatenickname(uid,name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateNickname>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdateNickname value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                            upload.updateNickNameSuccess(value);
                        }
                        else if("1".equals(code))
                        {
                            upload.updateNickNameFailure(value);

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










    /**
     * 修改头像
     * @param uid
     * @param img
     */
    public void getUpLoad(String uid,File img) {

        //File img=new File("mnt/sdcard/mo.jpg");
        //System.out.println(img.getAbsolutePath()+img.getName()+"++++++++++图片地址+++++++++++");
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), img);

        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", img.getName(), requestBody);
        new NetRequestUtils.Builder().addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .addConverterFactories(GsonConverterFactory.create())
                .build().getApiService().upload(uid, multipartBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpLoad>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpLoad value) {
                        String code = value.code;
                        if ("1".equals(code)) {
                            upload.uploadSuccess(value);
                            System.out.println("上传头像++++++++++" + value.msg);
                        } else {
                            upload.uploadFailure(value);
                            System.out.println("上传头像++++++++++" + value.msg);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        System.out.println(e.toString() + "上传头像");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void setUpload(ChangUserModel.upload upload) {
        this.upload = upload;
    }

    public  interface upload{
        void uploadSuccess(UpLoad upLoad);
        void uploadFailure(UpLoad upLoad);
        void updateNickNameSuccess(UpdateNickname updateNickname);
        void updateNickNameFailure(UpdateNickname updateNickname);
    }


}


