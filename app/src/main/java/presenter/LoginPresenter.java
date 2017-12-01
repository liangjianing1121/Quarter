package presenter;

import android.content.Context;
import android.util.Log;

import base.BasePresenter;
import base.BaseView;
import bean.Login;
import model.LoginModel;
import retrofit2.Converter;
import view.LoginView;

/**
 * Created by DELL on 2017/11/27.
 */

public class LoginPresenter extends BasePresenter<LoginView> implements LoginModel.ilogin {

    private LoginModel loginModel;
    private Context context;
    /**
     * 绑定P层
     *
     * @param mView
     */
    public LoginPresenter(LoginView mView,Context context) {
        super(mView);
        this.context=context;
        loginModel=new LoginModel();
        loginModel.setLogin(this);

    }

    public void login(String mobile,String passward){
        loginModel.getlogin(mobile,passward);

    }


    @Override
    public void getloginsuccess(Login login) {
        mView.RequestSuccess(login);

    }

    @Override
    public void getloginfailure(Login login) {
        mView.RequestFailure(login);

    }
}
