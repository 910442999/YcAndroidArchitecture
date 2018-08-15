package com.yc.mylibrary.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yc.commonlibrary.base.BaseFragment;
import com.yc.commonlibrary.presenter.BasePresenter;
import com.yc.mylibrary.R;

import junit.framework.Test;


/**
 * Created by zhanghan on 2018/7/31.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener {
    private static MyFragment sMyFragment;
    private TextView mTest;

    public static MyFragment newInstance(String param1) {
        sMyFragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        sMyFragment.setArguments(args);
        return sMyFragment;
    }

    public MyFragment() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int initLayoutInflater() {
        return R.layout.my_fragment;
    }

    @Override
    public void initView(View view) {
        mTest = view.findViewById(R.id.tv_test);
    }

    @Override
    public void initData() {
        mTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_test) {
            // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
            ARouter.getInstance().build("/my/TestActivity").navigation();

            //                // 2. 跳转并携带参数
            //                ARouter.getInstance().build("/test/1")
            //                        .withLong("key1", 666L)
            //                        .withString("key3", "888")
            //                        .navigation();
        }
    }
}
