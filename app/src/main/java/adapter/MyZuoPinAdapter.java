package adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by DELL on 2017/12/7.
 */

public class MyZuoPinAdapter  extends FragmentPagerAdapter {

    private Context context;
    private List<Fragment> list;
    private String[] title={"本地作品","已上传"};

    public MyZuoPinAdapter(FragmentManager fm, Context context, List<Fragment> list) {
        super(fm);
        this.context = context;
        this.list = list;
    }

    public MyZuoPinAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}