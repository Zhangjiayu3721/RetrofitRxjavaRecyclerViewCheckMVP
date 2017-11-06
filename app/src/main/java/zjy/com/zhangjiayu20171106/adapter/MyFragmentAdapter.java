package zjy.com.zhangjiayu20171106.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangJiaYu on 2017/11/6
 * ViewPager和Fragment联动.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter{
    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }
    List<Fragment> list = new ArrayList<>();

    public void setList(List<Fragment> list) {
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
