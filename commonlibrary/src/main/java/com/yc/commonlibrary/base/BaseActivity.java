package com.yc.commonlibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutInflater());
        mUnbinder = ButterKnife.bind(this);
        mContext = this;
        initView();
        initData();
    }
    protected <T> T findId(int id) {
        T view = (T) findViewById(id);
        return view;
    }
    protected abstract int initLayoutInflater(); //初始化布局

    public abstract void initView();

    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null)
            mUnbinder.unbind();
    }
}
