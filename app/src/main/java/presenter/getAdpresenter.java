package presenter;

import android.content.Context;

import base.BasePresenter;
import bean.Ad;
import model.getAdModel;
import view.getAdView;

/**
 * Created by DELL on 2017/11/30.
 */

public class getAdpresenter extends BasePresenter<getAdView> implements model.getAdModel.igetAd {


    private getAdModel getAdModel;
    private Context context;
    /**
     * 绑定P层
     *
     * @param mView
     */
    public getAdpresenter(Context context,getAdView mView) {
        super(mView);
        getAdModel=new getAdModel();
        getAdModel.setIgetAd(this);
    }

    public void getAd(){
        getAdModel.getAd();
    }

    @Override
    public void getAdSuccess(Ad ad) {
        mView.RequestSuccess(ad);
    }

    @Override
    public void getAdFailure(Ad ad) {
        mView.RequestFailure(ad);

    }
}
