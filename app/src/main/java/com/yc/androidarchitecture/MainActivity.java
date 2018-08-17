package com.yc.androidarchitecture;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.yc.commonlibrary.base.BaseActivity;
import com.yc.commonlibrary.presenter.BasePresenter;
import com.yc.homelibrary.fragment.HomeFragment;
import com.yc.mylibrary.fragment.MyFragment;

import java.util.ArrayList;



import static com.yc.commonlibrary.base.Constants.currentFragment;

public class MainActivity extends BaseActivity {
    private ArrayList<Fragment> fragments;
    private FragmentTransaction transaction;
    private Fragment fragment;
    private HomeFragment mHomeFragment;
    private MyFragment mMyFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showFragment(0);
                    return true;
                case R.id.navigation_notifications:
                    showFragment(1);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected int initLayoutInflater() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        prepareFragments();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    public void initData() {
        //默认到首页
        showFragment(0);
    }

    private void prepareFragments() {
        fragments = new ArrayList<>();
        if (mHomeFragment == null) {
            mHomeFragment = HomeFragment.newInstance("首页");
        }
        fragments.add(mHomeFragment);

        if (mMyFragment == null) {
            mMyFragment = MyFragment.newInstance("设置");
        }
        fragments.add(mMyFragment);

    }

    public void showFragment(int position) {
        currentFragment = position;
        FragmentManager fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        //先隐藏其他的
        for (int i = 0; i < fragments.size(); i++) {
            fragment = fragments.get(i);
            if (i == position) {
                if (fragment.isAdded()) {
                    transaction.show(fragment);
                } else {
                    //add
                    transaction.add(R.id.fl_main, fragment);
                }
            } else {
                if (fragment.isAdded()) {
                    transaction.hide(fragment);
                }
            }
        }
        //commit
        transaction.commitAllowingStateLoss();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1002) {
            mMyFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
