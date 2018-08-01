package com.yc.homelibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.yc.commonlibrary.base.BaseActivity;
import com.yc.commonlibrary.bean.ApiResult;
import com.yc.homelibrary.presenter.HomePresenter;
import com.yc.homelibrary.view.IHomeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity<IHomeView, HomePresenter<IHomeView>> implements IHomeView {


    @BindView(R2.id.home)
    TextView mHome;
    @BindView(R2.id.home_to_my)
    TextView mHomeToMy;

    @Override
    protected HomePresenter<IHomeView> createPresenter() {
        return new HomePresenter<>();
    }

    @Override
    protected int initLayoutInflater() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {


    }

    @Override
    public void initData() {
        basePresenter.getHomeData();
    }

    //在点击事件的id 用 R2 而不是用R
    @OnClick({R2.id.home, R2.id.home_to_my})
    public void onViewClicked(View view) {
        //正确写法

        //不能直接用swich 而是用if else 的方式  并且id不能用R2 而是用R
        if (view.getId() == R.id.home) {
            //点击事件处理
            Toast.makeText(this, "这是首页", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.home_to_my) {
            //点击事件处理
            Toast.makeText(this, "跳转到到我的页面", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void showhome(ApiResult apiResult) {

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
