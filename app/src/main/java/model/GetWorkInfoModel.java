package model;

import bean.GetWorkInfo;
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

public class GetWorkInfoModel {

    private  iGetWorkInfo iGetWorkInfo;



    public void getWorkInfo(String uid){
        new NetRequestUtils.Builder().addConverterFactories(GsonConverterFactory.create())
                .addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .build().getApiService().getWorkInfo(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetWorkInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetWorkInfo value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                          iGetWorkInfo.getWorkInfoSuccess(value);
                        }
                        else
                        {
                            iGetWorkInfo.getWorkInfoFailure(value);
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


    public void setiGetWorkInfo(GetWorkInfoModel.iGetWorkInfo iGetWorkInfo) {
        this.iGetWorkInfo = iGetWorkInfo;
    }

    public interface iGetWorkInfo{
        void getWorkInfoSuccess(GetWorkInfo getWorkInfo);
        void getWorkInfoFailure(GetWorkInfo getWorkInfo);
    }
}
