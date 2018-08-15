package com.yc.homelibrary.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yc.commonlibrary.base.BaseFragment;
import com.yc.commonlibrary.bean.ApiResult;
import com.yc.commonlibrary.presenter.BasePresenter;
import com.yc.homelibrary.R;
import com.yc.homelibrary.presenter.HomePresenter;
import com.yc.homelibrary.view.IHomeView;

import java.util.List;

/**

 */

public class HomeFragment extends BaseFragment<IHomeView, HomePresenter<IHomeView>> implements IHomeView {
    private static HomeFragment sHomeFragment;

    public static HomeFragment newInstance(String param1) {
        sHomeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        sHomeFragment.setArguments(args);
        return sHomeFragment;
    }

    public HomeFragment() {

    }


    @Override
    protected HomePresenter<IHomeView> createPresenter() {
        return new HomePresenter<>();
    }

    @Override
    protected int initLayoutInflater() {
        return R.layout.home_fragment;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {
        basePresenter.getHomeData();
    }

    @Override
    public void showhome(ApiResult apiResult) {
        Toast.makeText(getActivity(), apiResult.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading(boolean b) {

    }

    @Override
    public void onError(Object result) {

    }
}
