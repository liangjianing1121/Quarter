package presenter;

import android.content.Context;
import android.provider.UserDictionary;

import com.facebook.imagepipeline.request.MediaVariations;

import base.BasePresenter;
import bean.User;
import model.UserModel;
import retrofit2.CallAdapter;
import view.UserView;

/**
 * Created by DELL on 2017/11/28.
 */

public class UserPresenter extends BasePresenter<UserView> implements UserModel.igetUser {
    private Context context;
    private UserModel userModel;
  /*  *
     * 绑定P层
     *
     * @param mView
     */
    public UserPresenter(UserView mView,Context context) {
        super(mView);
        this.context=context;
        userModel=new UserModel();
        userModel.setIgetUser(this);
    }

    public void getUser(String uid){
        userModel.getUser(uid);
    }

    @Override
    public void getUserSuccess(User user) {
        mView.RequestSuccess(user);
    }

    @Override
    public void getUserFailure(User user) {
        mView.RequestFailure(user);
    }

}
