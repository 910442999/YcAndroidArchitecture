package com.yc.commonlibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.yc.commonlibrary.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 * BaseActivity设置两个泛型——V和P，分别代表对应的View和Presenter。
 * @param <V>
 * @param <P>
 */
public abstract class BaseActivity<V, P extends BasePresenter<V>> extends AppCompatActivity {
    public String TAG = getClass().getSimpleName().toString();
    public Context mContext;
    private Unbinder mUnbinder;
    //表示层的引用
    protected P basePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutInflater());
        mUnbinder = ButterKnife.bind(this);
        mContext = this;
        //将子类创建的presenter 赋值给基类表示层
        basePresenter = createPresenter();
        if (basePresenter != null)
            //绑定
            basePresenter.onAttachView((V) this);
        initView();
        initData();
    }


    protected <T> T findId(int id) {
        T view = (T) findViewById(id);
        return view;
    }

    protected abstract P createPresenter();

    protected abstract int initLayoutInflater(); //初始化布局

    public abstract void initView();

    public abstract void initData();

    private P getPresenter() {
        return basePresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null)
            mUnbinder.unbind();
        if (basePresenter != null)
            //解绑
            basePresenter.onDetachView();
        Toast.makeText(this, "父类base销毁", Toast.LENGTH_SHORT).show();
    }
}
