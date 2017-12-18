package presenter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import base.BasePresenter;
import bean.AddFavorite;
import bean.BaseBean;
import bean.Praise;
import model.PraiseModel;
import view.PraiseView;

/**
 * Created by DELL on 2017/12/11.
 */

public class PraisePresenter extends BasePresenter<PraiseView> implements PraiseModel.iGetParise {



    private PraiseModel praiseModel;
    /**
     * 绑定P层
     *
     * @param mView
     */
    public PraisePresenter(PraiseView mView) {
        super(mView);
        praiseModel=new PraiseModel();
        praiseModel.setiGetParise(this);
    }


    public void getPraise(String uid,String wid){
        praiseModel.praise(uid,wid);
    }


    public void cancelFavorite(String uid,String wid){
        praiseModel.cancelFavorite(uid,wid);
    }

    public void addFavorite(String uid,String wid){
        praiseModel.addFavorite(uid,wid);
    }

    public void getComment(String uid,String wid,String conent){
        praiseModel.putComment(uid,wid,conent);
    }
    @Override
    public void getPraiseSuccess(Praise praise) {
        mView.getPraiseSuccess(praise);
    }

    @Override
    public void getPariesFailure(Praise praise) {

        mView.getPariesFailure(praise);
    }

    @Override
    public void addFavoriteSuccess(AddFavorite addFavorite) {

        mView.addFavoriteSuccess(addFavorite);
    }

    @Override
    public void addFavoriteFailure(AddFavorite addFavorite) {

        mView.addFavoriteFailure(addFavorite);
    }

    @Override
    public void cancelFavoriteSuccess(AddFavorite addFavorite) {
        mView.cancelFavoriteSuccess(addFavorite);
    }

    @Override
    public void cancelFavoriteFailure(AddFavorite addFavorite) {
        mView.cancelFavoriteFailure(addFavorite);
    }

    @Override
    public void CommonSuccess(BaseBean baseBean) {
        mView.CommonSuccess(baseBean);
    }

    @Override
    public void commonFailure(BaseBean baseBean) {

        mView.commonFailure(baseBean);
    }


}
