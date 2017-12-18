package model;

import com.autonavi.amap.mapcore.Inner_3dMap_locationOption;

import bean.GetFavorite;
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

public class GetFavoriteModel  {
    private  iGetFavorite iGetFavorite;

    public  void  getFavorite(String uid){
        new NetRequestUtils.Builder().addConverterFactories(GsonConverterFactory.create())
                .addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .build().getApiService().getFavorite(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetFavorite>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetFavorite value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                            iGetFavorite.getFavoriteSuccess(value);
                        }
                        else
                        {
                            iGetFavorite.getFavoriteFailure(value);
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

    public void setiGetFavorite(GetFavoriteModel.iGetFavorite iGetFavorite) {
        this.iGetFavorite = iGetFavorite;
    }

    public interface iGetFavorite{
        void getFavoriteSuccess(GetFavorite getFavorite);
        void getFavoriteFailure(GetFavorite getFavorite);
    }
}
