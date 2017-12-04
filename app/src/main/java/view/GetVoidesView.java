package view;

import base.BaseView;
import bean.Ad;
import bean.Videos;

/**
 * Created by DELL on 2017/12/2.
 */

public interface GetVoidesView extends BaseView{
   void  getVideoSuccess(Videos videos);
   void getVideoFailure(Videos videos);


   void getAdSuccess(Ad ad);
   void getAdFailure(Ad ad);
}
