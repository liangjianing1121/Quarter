package presenter;

import android.content.Context;

import java.io.File;

import base.BasePresenter;
import bean.UpLoad;
import bean.UpdateNickname;
import model.ChangUserModel;
import view.ChangUserView;

/**
 * Created by DELL on 2017/11/30.
 */

public class ChangUserPresenter extends BasePresenter<ChangUserView> implements  ChangUserModel.upload {

    private Context context;
    private ChangUserModel changUserModel;

    /**
     * 绑定P层
     *
     * @param mView
     */
    public ChangUserPresenter(ChangUserView mView,Context context) {
        super(mView);
        this.context=context;
        changUserModel=new ChangUserModel();
        changUserModel.setUpload(this);
    }

    public void getUpdateNickNamse(String uid, String nickname){
        changUserModel.updateNickName(uid,nickname);
    }

    public void upload(String uid, File img){
        changUserModel.getUpLoad(uid,img);
    }


    @Override
    public void uploadSuccess(UpLoad upLoad) {

    }

    @Override
    public void uploadFailure(UpLoad upLoad) {

    }

    @Override
    public void updateNickNameSuccess(UpdateNickname updateNickname) {

    }

    @Override
    public void updateNickNameFailure(UpdateNickname updateNickname) {

    }
}
