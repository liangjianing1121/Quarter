package view;

import base.BaseView;
import bean.AddFavorite;
import bean.Praise;

/**
 * Created by DELL on 2017/12/11.
 */

public interface PraiseView extends BaseView {

    void getPraiseSuccess(Praise praise);
    void getPariesFailure(Praise praise);


    void addFavoriteSuccess(AddFavorite addFavorite);
    void addFavoriteFailure(AddFavorite addFavorite);

    void cancelFavoriteSuccess(AddFavorite addFavorite);
    void cancelFavoriteFailure(AddFavorite addFavorite);



}
