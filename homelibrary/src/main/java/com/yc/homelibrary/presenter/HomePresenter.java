package com.yc.homelibrary.presenter;

import com.yc.commonlibrary.interfaces.onCallBackListener;
import com.yc.commonlibrary.presenter.BasePresenter;
import com.yc.homelibrary.bean.HomeBean;
import com.yc.homelibrary.model.IHomeModelImpl;
import com.yc.homelibrary.view.IHomeView;

import java.util.List;

/**
 * Created by yc on 2018/7/25.
 */

public class HomePresenter<T extends IHomeView> extends BasePresenter<T> {

    //2。model层的引用 （model 可以直接new创建）
    IHomeModelImpl mIHomeModel = new IHomeModelImpl();

    //3.构造方法 (因为 view 相当于activity 不能直接new创建 ，所以通过构造方法将view视图层传递过来 ，此view继承自 IHomeView)
    public HomePresenter() {
    }

    //4执行获取数据的方法
    public void getHomeData() {
        if (mTWeakReference.get() != null) {
            mTWeakReference.get().showLoading();
            if (mIHomeModel != null) {
                mIHomeModel.loadDate(new onCallBackListener<List<HomeBean>>() {
                    @Override
                    public void onComplete(List<HomeBean> homeBeans) {
                        mTWeakReference.get().showhome(homeBeans);
                    }
                });
            }
        }
    }
}
