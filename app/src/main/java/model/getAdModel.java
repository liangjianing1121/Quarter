package model;
import android.renderscript.AllocationAdapter;

import com.google.gson.Gson;

import bean.Ad;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetRequestUtils;

/**
 * Created by DELL on 2017/11/30.
 */

public class getAdModel {
    private igetAd igetAd;

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
                            igetAd.getAdSuccess(value);
                        }
                        else
                        {
                            igetAd.getAdFailure(value);
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


    public void setIgetAd(getAdModel.igetAd igetAd) {
        this.igetAd = igetAd;
    }

    public interface igetAd{
        void getAdSuccess(Ad ad);
        void getAdFailure(Ad ad);
    }


}
