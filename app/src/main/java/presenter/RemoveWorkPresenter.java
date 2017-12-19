package presenter;

import android.content.Context;

import base.BasePresenter;
import bean.BaseBean;
import model.RemoveWorkModel;
import view.RemoveWorkView;

/**
 * Created by DELL on 2017/12/19.
 */

public class RemoveWorkPresenter extends BasePresenter<RemoveWorkView> implements RemoveWorkModel.iRemoveWork {

    private RemoveWorkModel removeWorkModel;
    private Context context;
    /**
     * 绑定P层
     *
     * @param mView
     */
    public RemoveWorkPresenter(Context context,RemoveWorkView mView) {
        super(mView);
        this.context=context;
        removeWorkModel=new RemoveWorkModel();
        removeWorkModel.setiRemoveWork(this);
    }

    public void removework(String uid,String wid){
        removeWorkModel.removeWork(uid,wid);
    }

    @Override
    public void removeWorkSuccess(BaseBean baseBean) {
        mView.RequestSuccess(baseBean);
    }

    @Override
    public void removeWorkFailure(BaseBean baseBean) {
        mView.RequestFailure(baseBean);
    }
}
