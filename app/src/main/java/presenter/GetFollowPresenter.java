package presenter;

import android.content.Context;

import com.example.dell.quarter.GetFollowUser;

import base.BasePresenter;
import model.GetFollowUserModel;
import view.GetFollowUserView;

/**
 * Created by DELL on 2017/12/14.
 */

public class GetFollowPresenter extends BasePresenter<GetFollowUserView> implements GetFollowUserModel.iGetFollowUser {
    private GetFollowUserModel getFollowUserModel;
    private Context context;
    /**
     * 绑定P层
     *
     * @param mView
     */
    public GetFollowPresenter(Context context,GetFollowUserView mView) {
        super(mView);
        this.context=context;
        getFollowUserModel=new GetFollowUserModel();
        getFollowUserModel.setiGetFollowUser(this);

    }

    public  void getFollowUser(String uid){
        getFollowUserModel.getFollowUser(uid);
    }

    @Override
    public void getFollowUserSuccess(GetFollowUser getFollowUser) {
        mView.RequestSuccess(getFollowUser);
    }

    @Override
    public void getFollowUserFailuer(GetFollowUser getFollowUser) {

        mView.RequestFailure(getFollowUser);
    }
}
