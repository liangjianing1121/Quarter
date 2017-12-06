package fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.ImageReader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.quarter.HomeActivity;
import com.example.dell.quarter.R;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.jakewharton.rxbinding2.view.RxView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import adapter.ReMenAdapter;
import bean.Ad;
import bean.Videos;
import presenter.GetVideosPresenter;
import presenter.getAdpresenter;
import view.GetVoidesView;
import view.getAdView;

/**
 * Created by DELL on 2017/11/26.
 */

public class ReMenFragment extends Fragment implements XBanner.XBannerAdapter,GetVoidesView{

    private View view;
    private XBanner banner;
    private List<String> imglist;
    private XRecyclerView xrv;
    private int i=1;
    private ReMenAdapter reMenAdapter;
    private List<Videos.DataBean> list;
    private GetVideosPresenter getVideosPresenter;

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

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        imglist = new ArrayList<>();
        list=new ArrayList<>();

        xrv = view.findViewById(R.id.xrv);
        View xbanner = View.inflate(getActivity(), R.layout.xbanner_remen, null);
        banner = xbanner.findViewById(R.id.banner);
        xrv.addHeaderView(xbanner);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrv.setLayoutManager(linearLayoutManager);
        xrv.setRefreshProgressStyle(18);
        xrv.setLaodingMoreProgressStyle(14);
    }

    @Override
    public void onResume() {
        super.onResume();
        getVideosPresenter = new GetVideosPresenter(getContext(),this);
        SharedPreferences uid = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);
        int uid1 = uid.getInt("uid", 0);
        uid.getInt("uid", 0);
        getVideosPresenter.getVideos(uid1+"",1+"",i+"");
        getVideosPresenter.getAd();


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
    public void RequestSuccess(Object o) {

    }

    @Override
    public void RequestFailure(Object o) {

    }


    @Override
    public void Failure(Object o) {

    }
    /**
     * 请求视频成功
     */
    @Override
    public void getVideoSuccess(Videos videos) {
        List<Videos.DataBean> data = videos.data;
        list.addAll(data);

        if(reMenAdapter==null)
        {
            reMenAdapter=new ReMenAdapter(getActivity(),data);
            xrv.setAdapter(reMenAdapter);
        }
        else
        {
            reMenAdapter.notifyDataSetChanged();
        }

        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                SharedPreferences uid = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);
                int uid1 = uid.getInt("uid", 0);
                uid.getInt("uid", 0);
                getVideosPresenter.getVideos(uid1+"",1+"",1+"");
                xrv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                i++;
                SharedPreferences uid = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);
                int uid1 = uid.getInt("uid", 0);
                uid.getInt("uid", 0);
                getVideosPresenter.getVideos(uid1+"",1+"",i+"");
                xrv.loadMoreComplete();
            }
        });

    }

    @Override
    public void getVideoFailure(Videos videos) {
        Toast.makeText(getActivity(), videos.msg, Toast.LENGTH_SHORT).show();

    }

    /**
     * 请求广告
     * @param ad
     */

    @Override
    public void getAdSuccess(Ad ad) {
        List<Ad.DataBean> data = ad.data;
        for (int j = 0; j < data.size(); j++) {
            Ad.DataBean dataBean = data.get(j);
            String icon = dataBean.icon;
            imglist.add(icon);
        }
        banner.removeAllViews();
        banner.setData(imglist,null);
        banner.setPoinstPosition(XBanner.RIGHT);
        banner.setmAdapter(this);

        //Toast.makeText(getActivity(), ad.data.size()+"", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void getAdFailure(Ad ad) {


    }
}
