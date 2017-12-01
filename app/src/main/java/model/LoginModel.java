package model;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;

import bean.Login;
import bean.User;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.NetRequestUtils;

/**
 * Created by DELL on 2017/11/27.
 */

public class LoginModel implements ILoginModel{

    private ilogin ilogin;
    @Override
    public void getlogin(String mobile, String password) {


        new NetRequestUtils.Builder().addConverterFactories(GsonConverterFactory.create())
                .addCallAdapterFactories(RxJava2CallAdapterFactory.create())
                .build().getApiService().getlogin(mobile,password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Login>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Login value){
                        try {
                            String code = value.code;

                            if("0".equals(code))
                            {

                                ilogin.getloginsuccess(value);
                                System.out.println(value.msg +"++++++++++++++");
                            }
                            else if("1".equals(code))
                            {
                                ilogin.getloginfailure(value);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
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


    public void setLogin(LoginModel.ilogin ilogin) {
        this.ilogin = ilogin;
    }

    public interface ilogin{
        void getloginsuccess(Login login);
        void getloginfailure(Login login);
    }


}
