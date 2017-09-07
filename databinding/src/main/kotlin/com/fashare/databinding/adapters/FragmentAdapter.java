package com.fashare.databinding.adapters;

import android.databinding.BindingAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.fashare.databinding.R;

import java.util.List;

/**
 * <pre>
 *     author : jinliangshan
 *     e-mail : jinliangshan@chexiang.com
 *     desc   :
 * </pre>
 */
public class FragmentAdapter {
    public static final String TAG = "FragmentAdapter - binding Fragment...: ";

    @BindingAdapter(value = {"fragments", "curIndex"}, requireAll = false)
    public static void bind(ViewGroup container, List<? extends Fragment> fragments, int curIndex) {
        if(!(container.getContext() instanceof FragmentActivity))
            throw new IllegalArgumentException(TAG + "context must instanceof FragmentActivity");
        FragmentManager fm = ((FragmentActivity) container.getContext()).getSupportFragmentManager();

        int containerId = container.getId();
        if(containerId == View.NO_ID){
            throw new IllegalArgumentException(TAG + "container view must has a id!!!");
        }

        if (fragments != null && !fragments.isEmpty()) {
            boolean isInited = container.getTag(R.id.db_fragment_container_inited) != null;
            if(!isInited) {
                container.setTag(R.id.db_fragment_container_inited, true);
                // initialize, fragments are only added once !!!
                for (Fragment fragment : fragments) {
                    String tag = fragment.toString();

                    if (fm.findFragmentByTag(tag) == null) {
                        fm.beginTransaction()
                                .add(containerId, fragment, tag)
                                .commitAllowingStateLoss();
                    }
                }
            }

            for (Fragment fragment : fragments) {
                fm.beginTransaction()
                        .hide(fragment)
                        .commitAllowingStateLoss();
            }

            if(curIndex >= 0 && curIndex < fragments.size())
                fm.beginTransaction()
                        .show(fragments.get(curIndex))
                        .commitAllowingStateLoss();
        }
    }

    @BindingAdapter(value = {"fragments", "curIndex"}, requireAll = false)
    public static void bind(ViewPager container, final List<? extends Fragment> fragments, int curIndex) {
        if(!(container.getContext() instanceof FragmentActivity))
            throw new IllegalArgumentException(TAG + "context must instanceof FragmentActivity");
        FragmentManager fm = ((FragmentActivity) container.getContext()).getSupportFragmentManager();

        if (fragments != null && !fragments.isEmpty()) {
            boolean isInited = container.getTag(R.id.db_fragment_container_inited) != null;
            if(!isInited) {
                container.setTag(R.id.db_fragment_container_inited, true);
                // initialize, fragments are only added once !!!
                container.setAdapter(new FragmentPagerAdapter(fm) {
                    @Override
                    public Fragment getItem(int position) {
                        return fragments.get(position);
                    }

                    @Override
                    public int getCount() {
                        return fragments.size();
                    }
                });
            }

            if(curIndex >= 0 && curIndex < fragments.size())
                container.setCurrentItem(curIndex);
        }
    }
}
