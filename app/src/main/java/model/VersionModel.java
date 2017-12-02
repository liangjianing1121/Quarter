package model;

import bean.Version;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import presenter.VersionPresenter;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetRequestUtils;

/**
 * Created by DELL on 2017/12/1.
 */

public class VersionModel {

    private igetVersion igetVersion;

    public void getVersion(){
        new NetRequestUtils.Builder().addConverterFactories(GsonConverterFactory.create())
                .addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .build().getApiService().getVersion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Version>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Version value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                            igetVersion.getVerxionSuccess(value);
                            System.out.println("版本更新"+value.msg+"========");
                        }
                        else
                        {
                            igetVersion.getVersionFailure(value);
                            System.out.println("版本更新"+value.msg+"========");
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.println("版本更新"+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setIgetVersion(VersionModel.igetVersion igetVersion) {
        this.igetVersion = igetVersion;
    }

    public interface igetVersion{
        void getVerxionSuccess(Version version);
        void getVersionFailure(Version version);
    }
}
