package com.yc.commonlibrary.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by yc on 2018/7/24.
 */

public class BasePresenter<V> {
    //1.view层的引用实际上就相当于activity的视图
    //为了防止内存泄漏 在创建view层的引用的时候 , 通常情况是直接传递要绑定的view 然后在onDetachView的时候 将view 置空
    //这里我们采用弱引用的方式来构造视图层 , 如果有不了解弱引用的 可以去查看一下四种引用的用法,弱引用是经常用到的比如解决网络请求handler造成的内存溢出等,这里不做详细讲解
    //在继承basePresenter的时候传递一个view , 在创建presenter的时候 进行绑定, 当解绑presenter的时候讲view解绑
    //    BaseView mBaseView;
    protected WeakReference<V> mTWeakReference;

    //进行绑定
    public void onAttachView(V view) {
        mTWeakReference = new WeakReference<V>(view);
    }

    //进行解绑
    public void onDetachView() {
        if (mTWeakReference != null)
            mTWeakReference.clear();
    }

    protected V getView() {
        return mTWeakReference.get();
    }

    public boolean isViewAttached() {
        return mTWeakReference != null && mTWeakReference.get() != null;
    }
}

