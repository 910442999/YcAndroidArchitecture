package com.yc.commonlibrary.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yc.commonlibrary.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V, P extends BasePresenter<V>> extends Fragment {
    public String TAG = getClass().getSimpleName().toString();
    private Unbinder mUnbinder;
    //表示层的引用
    protected P basePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(initLayoutInflater(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        //将子类创建的presenter 赋值给基类表示层
        basePresenter = createPresenter();
        if (basePresenter != null)
            //绑定
            basePresenter.onAttachView((V) this);
        initView(view);
        initData();
        return view;
    }


    protected abstract P createPresenter();

    protected abstract int initLayoutInflater(); //初始化布局

    public abstract void initView(View view);

    public abstract void initData();

    private P getPresenter() {
        return basePresenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null)
            mUnbinder.unbind();
        if (basePresenter != null)
            //解绑
            basePresenter.onDetachView();
        Toast.makeText(getActivity(), "父类fragment_base销毁", Toast.LENGTH_SHORT).show();
    }
}
