package fragment;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.example.dell.quarter.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.NearVideoAdapter;
import adapter.ReMen3Adapter;
import bean.HotVideo;
import bean.NearVideos;
import presenter.GetHotVideoPresenter;
import presenter.NearVideoPresenter;
import view.NearVideoView;

/**
 * Created by DELL on 2017/11/27.
 */

public class FujinFragment extends Fragment implements NearVideoView, AMapLocationListener, LocationSource {


    private View view;
    private XRecyclerView xrv;
    private NearVideoAdapter nearVideoAdapter;
    private List<NearVideos.DataBean> list;
    private NearVideoPresenter presenter;
    private int i=1;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private MapView mMapView;
    private LocationSource.OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;

    private AMap aMap;
    private int weidu;
    private int jingdu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(view==null)
        {
            view = inflater.inflate(R.layout.fujinfragment,null);
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
        initMap();
        initData();
    }

    private void initMap() {
        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        //设置地图的放缩级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(12));

        // 设置定位监听
        aMap.setLocationSource(this);
        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
        // 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                //从location对象中获取经纬度信息，地址描述信息，建议拿到位置之后调用逆地理编码接口获取

                double latitude = location.getLatitude();
                weidu = (int) latitude;

                double longitude = location.getLongitude();

                jingdu = (int) longitude;

                System.out.println("经度======= " + jingdu +"纬度============"+ weidu);

            }
        });
    }

    private void initData() {
        list=new ArrayList<>();
        xrv.setPullRefreshEnabled(true);
        xrv.setLoadingMoreEnabled(true);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

    }

    private void initView() {
        xrv = view.findViewById(R.id.near_xrv);
        mMapView = new MapView(getActivity());
    }

    @Override
    public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(getActivity());
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(this);

            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }

    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }


    //定位回调  在回调方法中调用“mListener.onLocationChanged(amapLocation);”可以在地图上显示系统小蓝点。
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点

            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("定位AmapErr", errText);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new NearVideoPresenter(getActivity(),this);
        presenter.getNearVideo(i+"",weidu+"",jingdu+"");
    }

    @Override
    public void RequestSuccess(NearVideos nearVideos) {
        Toast.makeText(getContext(), nearVideos.getMsg(), Toast.LENGTH_SHORT).show();
        List<NearVideos.DataBean> data = nearVideos.getData();
        list.addAll(data);

        if(nearVideoAdapter==null)
        {
            nearVideoAdapter = new NearVideoAdapter(getContext(),list);

            staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
            //顶部错位解决
            staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);


            xrv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    staggeredGridLayoutManager.invalidateSpanAssignments();

                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }
            });

            xrv.setLayoutManager(staggeredGridLayoutManager);
            xrv.setAdapter(nearVideoAdapter);

        }
        else
        {
            nearVideoAdapter.notifyDataSetChanged();
        }
        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                presenter.getNearVideo(1+"",weidu+"",jingdu+"");
                xrv.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                i++;
                presenter.getNearVideo(i+"",weidu+"",jingdu+"");
                xrv.loadMoreComplete();
            }
        });
    }

    @Override
    public void RequestFailure(NearVideos nearVideos) {
        Toast.makeText(getActivity(), nearVideos.getMsg(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void Failure(NearVideos nearVideos) {

    }
}
