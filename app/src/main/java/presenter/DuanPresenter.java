package presenter;

import android.content.Context;

import java.util.List;

import base.BasePresenter;
import bean.Duanzi;
import model.DuanziModel;
import view.DuanListView;

/**
 * Created by DELL on 2017/11/28.
 */

public class DuanPresenter extends BasePresenter<DuanListView> implements DuanziModel.getduan {

    private Context context;
    private DuanziModel duanziModel;
    /**
     * 绑定P层
     *
     * @param mView
     */
    public DuanPresenter(DuanListView mView,Context context) {
        super(mView);
        this.context=context;
        duanziModel=new DuanziModel();
        duanziModel.setGetduan(this);
    }


    public void getDuanList(int page){
        duanziModel.getDuanList(page);

    }

    @Override
    public void getduanSuccess(Duanzi duanzi) {
        mView.RequestSuccess(duanzi);

    }

    @Override
    public void getduanFailure(Duanzi duanzi) {
        mView.RequestFailure(duanzi);
    }
}
