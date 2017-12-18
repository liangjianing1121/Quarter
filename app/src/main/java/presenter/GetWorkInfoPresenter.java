package presenter;

import android.content.Context;

import base.BasePresenter;
import bean.GetWorkInfo;
import model.GetWorkInfoModel;
import view.GetWorkInfoView;

/**
 * Created by DELL on 2017/12/15.
 */

public class GetWorkInfoPresenter extends BasePresenter<GetWorkInfoView> implements GetWorkInfoModel.iGetWorkInfo {
    private Context context;
    private GetWorkInfoModel getWorkInfoModel;
    /**
     * 绑定P层
     *
     * @param mView
     */
    public GetWorkInfoPresenter(Context context,GetWorkInfoView mView) {
        super(mView);
        this.context=context;
        getWorkInfoModel=new GetWorkInfoModel();
        getWorkInfoModel.setiGetWorkInfo(this);
    }

    public void getWorkInfo(String uid){
        getWorkInfoModel.getWorkInfo(uid);
    }

    @Override
    public void getWorkInfoSuccess(GetWorkInfo getWorkInfo) {
        mView.RequestSuccess(getWorkInfo);
    }

    @Override
    public void getWorkInfoFailure(GetWorkInfo getWorkInfo) {
        mView.RequestFailure(getWorkInfo);
    }
}
