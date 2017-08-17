package com.mobcent.discuz.module.user.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mobcent.discuz.module.user.fragment.BaseUserInnerScrollFragment;
import com.mobcent.discuz.module.user.fragment.UserHomeInformationFragment;
import com.mobcent.discuz.module.user.fragment.UserHomePublishFragment;
import com.mobcent.discuz.module.user.interFace.NetloadFinished;

import java.util.ArrayList;

/**
 * Created by pangxiaomin on 16/11/20.
 */
public class UserHomeAdapter extends FragmentPagerAdapter {

    public static String PAGE_TITLES[] = {"发表","资料"};

    private ArrayList<BaseUserInnerScrollFragment> mFragments;

    public UserHomeAdapter(NetloadFinished netloadFinished,FragmentManager fm) {
        super(fm);
        mFragments =  new ArrayList<>();
        UserHomePublishFragment userHomePostFragment = UserHomePublishFragment.newInstance(netloadFinished );
        UserHomeInformationFragment userHomeInformationFragment = new UserHomeInformationFragment();
        mFragments.add(userHomePostFragment);
        mFragments.add(userHomeInformationFragment);
        //userHomePostFragment.loadFinishedCallBack();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public BaseUserInnerScrollFragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  PAGE_TITLES[position];
    }

    public boolean canScrollVertically(int position, int direction) {
        return getItem(position).canScrollVertically(direction);
    }

}