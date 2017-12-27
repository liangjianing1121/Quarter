package fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.quarter.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.roughike.bottombar.TabSelectionInterceptor;

/**
 * Created by DELL on 2017/12/20.
 */

public class Fragment4 extends Fragment {

    private View view;
    private BottomBar bottomBar;
    private BottomBarTab nearby;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getActivity(), R.layout.fragment4, null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        bottomBar = (BottomBar)view.findViewById(R.id.bottomBar);
         bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_nearby) {
                  /*  // 选择指定 id 的标签
                    nearby = bottomBar.getTabWithId(R.id.tab_nearby);
                    nearby.setBadgeCount(5);*/

                  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer,new BottomFragment1()).commit();

                }
                else if(tabId==R.id.tab_favorites)
                {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer,new BottomFragment2()).commit();

                }
                else {

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer,new BottomFragment3()).commit();

                }
            }
        });
       bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_favorites) {
                    // 已经选择了这个标签，又点击一次。即重选。
                    nearby.removeBadge();
                }
            }
        });

       /* bottomBar.setTabSelectionInterceptor(new TabSelectionInterceptor() {
            @Override
            public boolean shouldInterceptTabSelection(@IdRes int oldTabId, @IdRes int newTabId) {
                // 点击无效
                if (newTabId == R.id.tab ) {
                    // ......
                    // 返回 true 。代表：这里我处理了，你不用管了。
                    return true;
                }

                return false;
            }
        });*/




    }
}
