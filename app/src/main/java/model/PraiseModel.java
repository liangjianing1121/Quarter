package model;

import bean.AddFavorite;
import bean.Praise;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.CallAdapter;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetRequestUtils;

/**
 * Created by DELL on 2017/12/11.
 */

public class PraiseModel {
    private iGetParise iGetParise;


    public  void  praise(String uid,String wid){
        new NetRequestUtils.Builder().addConverterFactories(GsonConverterFactory.create())
                .addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .build().getApiService().getPraise(uid,wid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Praise>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Praise value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                            iGetParise.getPraiseSuccess(value);
                        }
                        else
                        {
                            iGetParise.getPariesFailure(value);
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


    public void addFavorite(String uid, String  wid){

        new NetRequestUtils.Builder().addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .addConverterFactories(GsonConverterFactory.create())
                .build().getApiService().addFavorite(uid,wid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddFavorite>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddFavorite value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                            iGetParise.addFavoriteSuccess(value);
                        }
                        else
                        {
                            iGetParise.addFavoriteFailure(value);
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

    public void cancelFavorite(String uid,String wid){

        new NetRequestUtils.Builder().addConverterFactories( GsonConverterFactory.create())
                .addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .build().getApiService().cancelFavorite(uid,wid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddFavorite>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddFavorite value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                            iGetParise.cancelFavoriteSuccess(value);
                        }
                        else
                        {
                            iGetParise.cancelFavoriteFailure(value);
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


    public void setiGetParise(PraiseModel.iGetParise iGetParise) {
        this.iGetParise = iGetParise;
    }

    public interface iGetParise{
        void getPraiseSuccess(Praise praise);
        void getPariesFailure(Praise praise);


        void addFavoriteSuccess(AddFavorite addFavorite);
        void addFavoriteFailure(AddFavorite addFavorite);

        void cancelFavoriteSuccess(AddFavorite addFavorite);
        void cancelFavoriteFailure(AddFavorite addFavorite);



    }
}
