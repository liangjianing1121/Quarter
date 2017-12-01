package presenter;

import android.content.Context;
import android.widget.Toast;

import base.BasePresenter;
import bean.Register;
import model.RegisterModel;
import view.RegisterView;

/**
 * Created by DELL on 2017/11/28.
 */

public class RegisterPresenter extends BasePresenter<RegisterView> implements RegisterModel.iRegister {
    private Context context;
    private RegisterModel registerModel;
    /**
     * 绑定P层
     *
     * @param mView
     */
    public RegisterPresenter(RegisterView mView,Context context) {
        super(mView);
        this.context=context;
        registerModel =new RegisterModel();
        registerModel.setiRegister(this);

    }


    public  void  Refgister(String mobile,String password){
        registerModel.getRegister(mobile,password);
    }


    @Override
    public void registerSuccess(String msg) {
        mView.RequestSuccess(msg);
    }


    @Override
    public void registerFailure(String msg) {
        mView.RequestFailure(msg);
    }

    @Override
    public void Failure(Throwable e) {
        mView.Failure(e);
    }

}
