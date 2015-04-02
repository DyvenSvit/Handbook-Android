package com.dyvensvit.handbook;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dyvensvit.handbook.entities.Community;

/**
 * Created by Maksym on 3/29/2015.
 */
public class DepartmentCollectionPagerAdapter extends FragmentStatePagerAdapter {

    Community[] mCommunities;

    public DepartmentCollectionPagerAdapter(FragmentManager fm, Community[] communities) {
        super(fm);
        mCommunities = communities;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = DepartmentObjectFragment.newInstance(mCommunities[i]);
        return fragment;
    }

    @Override
    public int getCount() {
        return mCommunities.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return mCommunities[position].mTitle;
    }
}
