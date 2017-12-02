package fragment;

import android.content.res.Resources;
import android.media.DrmInitData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;

import com.example.dell.quarter.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import adapter.TabAdapter;

/**
 * Created by DELL on 2017/11/23.
 */

public class Fragment1 extends Fragment{

    private View view;
    private TabLayout tablayout;
    private ViewPager vp;
    private List<Fragment> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null)
        {
            view = inflater.inflate(R.layout.fragment1,null);
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
        initData();
        MysetIndicator(tablayout,40,40);
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new ReMenFragment());
        list.add(new GuanZhuFragment());
        tablayout.setupWithViewPager(vp);
        TabAdapter tabAdapter=new TabAdapter(getChildFragmentManager(),getContext(),list);
        vp.setAdapter(tabAdapter);
    }

    private void initView() {
        tablayout = view.findViewById(R.id.tablayout);
        vp = view.findViewById(R.id.vp);
    }

    public void MysetIndicator(TabLayout tabs,int leftDip,int rightDip){
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }

    }

}
