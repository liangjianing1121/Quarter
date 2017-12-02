package fragment;

import android.media.ImageReader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.quarter.HomeActivity;
import com.example.dell.quarter.R;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import bean.Ad;
import presenter.getAdpresenter;
import view.getAdView;

/**
 * Created by DELL on 2017/11/26.
 */

public class ReMenFragment extends Fragment implements XBanner.XBannerAdapter, getAdView {

    private View view;
    private XBanner banner;
    private List<String> imglist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       if(view==null)
       {
           view = inflater.inflate(R.layout.remenfragment,null);
       }
        ViewGroup parent = (ViewGroup) view.getParent();
       if(parent!=null)
       {
           parent.removeView(view);
       }

        return this.view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        banner = view.findViewById(R.id.banner);
        imglist = new ArrayList<>();
    }


    @Override
    public void onResume() {
        super.onResume();
        getAdpresenter presenter=new getAdpresenter(getContext(),this);
        presenter.getAd();

    }
    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public void loadBanner(XBanner banner, View view, int position) {
        Glide.with(this).load(imglist.get(position)).into((ImageView) view);

    }

    @Override
    public void RequestSuccess(Ad ad) {
        List<Ad.DataBean> data = ad.data;
        for (int i = 0; i < data.size(); i++) {
            Ad.DataBean dataBean = data.get(i);
            String icon = dataBean.icon;
            imglist.add(icon);
        }
        banner.removeAllViews();
        banner.setData(imglist,null);
        banner.setPoinstPosition(XBanner.RIGHT);
        banner.setmAdapter(this);
        banner.startAutoPlay();
    }

    @Override
    public void RequestFailure(Ad ad) {

    }

    @Override
    public void Failure(Ad ad) {

    }
}
