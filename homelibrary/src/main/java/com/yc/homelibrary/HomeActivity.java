package com.yc.homelibrary;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yc.commonlibrary.base.BaseActivity;
import com.yc.homelibrary.presenter.HomePresenter;
import com.yc.homelibrary.view.IHomeView;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/home/HomeActivity")
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
        //        basePresenter.getHomeData();
        String str = getIntent().getStringExtra("key2");
        if (!TextUtils.isEmpty(str)) {
            ((TextView) findViewById(R.id.home_to_my)).setText(str);
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }
    }

    //在点击事件的id 用 R2 而不是用R
    @OnClick({R2.id.home, R2.id.home_to_my})
    public void onViewClicked(View view) {
        //正确写法

        //不能直接用swich 而是用if else 的方式  并且id不能用R2 而是用R
        if (view.getId() == R.id.home) {
            //点击事件处理
            ARouter.getInstance().build("/my/TestActivity")
                    .withLong("key1", 666L)
                    .withString("key2", "这是从我的界面传递过来的")
                    .navigation(this, 1002);
        } else if (view.getId() == R.id.home_to_my) {
            //点击事件处理
            //            Toast.makeText(this, "跳转到到我的页面", Toast.LENGTH_SHORT).show();
            setResult(1002, getIntent().putExtra("arg2", "这是从首页返回的数据"));
            finish();
        }

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

    @Override
    public void finish() {
        setResult(1002, getIntent().putExtra("arg2", "首页: " + new Date().toString()));
        super.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1002) {
            String arg2 = data.getStringExtra("arg2");
            Toast.makeText(HomeActivity.this, arg2, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showhome(List apiResult) {

    }
}
