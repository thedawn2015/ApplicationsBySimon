package com.simon.sample.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * desc: ViewPagerAdapter
 * author: xw
 * time: 2017/4/5
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;
    private List<String> mTitleList;
    private List<Integer> mCountList;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> mFragmentList, List<String> mTitleList) {
        super(fm);
        this.mFragmentList = mFragmentList;
        this.mTitleList = mTitleList;
    }

    @Override
    public Fragment getItem(int position) {//必须实现
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {//必须实现
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {//选择性实现
        return mTitleList.get(position);
    }

    public void setmCountList(List<Integer> mCountList) {
        this.mCountList = mCountList;
    }

    public List<Integer> getmCountList() {
        return mCountList;
    }
}
