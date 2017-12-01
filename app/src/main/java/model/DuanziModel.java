package model;

import java.util.List;

import bean.Duanzi;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetRequestUtils;

/**
 * Created by DELL on 2017/11/28.
 */

public class DuanziModel {
    private getduan getduan;

    public void getDuanList(int page){
        new NetRequestUtils.Builder().addConverterFactories(GsonConverterFactory.create())
                .addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .build().getApiService().getduanlist(page+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Duanzi>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Duanzi value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                            getduan.getduanSuccess(value);
                            System.out.println(value+"+==============");
                        }
                        else if("1".equals(code))
                        {
                            getduan.getduanFailure(value);
                            System.out.println(value.msg+"请求失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString()+"+++++++");

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void setGetduan(DuanziModel.getduan getduan) {
        this.getduan = getduan;
    }

    public interface getduan{
        void getduanSuccess(Duanzi duanzi);
        void getduanFailure(Duanzi duanzi);
    }


}
