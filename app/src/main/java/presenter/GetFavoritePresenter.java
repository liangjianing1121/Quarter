package presenter;

import android.content.Context;
import android.text.Html;

import java.util.ConcurrentModificationException;

import base.BasePresenter;
import base.BaseView;
import bean.GetFavorite;
import model.GetFavoriteModel;
import view.GetFavoriteView;

/**
 * Created by DELL on 2017/12/18.
 */

public class GetFavoritePresenter extends BasePresenter<GetFavoriteView> implements GetFavoriteModel.iGetFavorite {

    private Context context;
    private GetFavoriteModel getFavoriteModel;
    /**
     * 绑定P层
     *
     * @param mView
     */
    public GetFavoritePresenter(Context context,GetFavoriteView mView) {
        super(mView);
        this.context=context;
        getFavoriteModel=new GetFavoriteModel();
        getFavoriteModel.setiGetFavorite(this);
    }


    public void getFavorite(String uid){
        getFavoriteModel.getFavorite(uid);
    }

    @Override
    public void getFavoriteSuccess(GetFavorite getFavorite) {

        mView.RequestSuccess(getFavorite);
    }

    @Override
    public void getFavoriteFailure(GetFavorite getFavorite) {

        mView.RequestFailure(getFavorite);
    }
}
