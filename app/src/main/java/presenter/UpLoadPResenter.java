package presenter;

import android.content.Context;

import java.io.File;

import base.BasePresenter;
import bean.UpLoad;
import model.UpLoadModel;
import view.UpLoadView;

/**
 * Created by DELL on 2017/11/30.
 */

public class UpLoadPResenter extends BasePresenter<UpLoadView> implements UpLoadModel.upload {

    private Context context;
    private UpLoadModel upLoadModel;
    /**
     * 绑定P层
     *
     * @param mView
     */
    public UpLoadPResenter(Context context,UpLoadView mView) {
        super(mView);
        upLoadModel=new UpLoadModel();
        upLoadModel.setUpload(this);
    }

    public void upload(String uid, File img){
        upLoadModel.getUpLoad(uid,img);
    }


    @Override
    public void uploadSuccess(UpLoad upLoad) {

    }

    @Override
    public void uploadFailure(UpLoad upLoad) {

    }
}
