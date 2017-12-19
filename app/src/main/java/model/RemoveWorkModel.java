package model;

import android.accounts.NetworkErrorException;

import bean.BaseBean;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetRequestUtils;

/**
 * Created by DELL on 2017/12/19.
 */

public class RemoveWorkModel  {

    private iRemoveWork iRemoveWork;


    public  void removeWork(String uid, String wid){
        new NetRequestUtils.Builder().addConverterFactories(GsonConverterFactory.create())
                .addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .build().getApiService().removeWork(uid,wid)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean value) {
                        String code = value.code;
                        if("0".equals(code))
                        {
                            iRemoveWork.removeWorkSuccess(value);
                        }
                        else
                        {
                            iRemoveWork.removeWorkFailure(value);
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

    public void setiRemoveWork(RemoveWorkModel.iRemoveWork iRemoveWork) {
        this.iRemoveWork = iRemoveWork;
    }

    public interface iRemoveWork{
        void removeWorkSuccess(BaseBean baseBean);
        void removeWorkFailure(BaseBean baseBean);
    }


}
