package com.yc.mylibrary.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yc.commonlibrary.base.BaseFragment;
import com.yc.commonlibrary.presenter.BasePresenter;
import com.yc.mylibrary.R;


/**
 * Created by zhanghan on 2018/7/31.
 */
@Route(path = "/my/MyFragment")
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
            //            ARouter.getInstance().build("/home/HomeActivity").navigation();

            // 2. 跳转并携带参数
            //                        ARouter.getInstance().build("/home/HomeActivity")
            //                                .withLong("key1", 666L)
            //                                .withString("key2", "这是从我的界面传递过来的")
            //                                .navigation();

            // 3. 跳转并携带参数 ,携带返回值
            // navigation的第一个参数必须是Activity，第二个参数则是RequestCode
            ARouter.getInstance().build("/home/HomeActivity")
                    .withLong("key1", 666L)
                     .withString("key2", "这是从我的界面传递过来的")
                    .navigation(getActivity(), 1002);

            //            startActivityForResult(new Intent(getActivity(), TestActivity.class),1002);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1002) {
            String arg2 = data.getStringExtra("arg2");
            Toast.makeText(getActivity(), arg2, Toast.LENGTH_SHORT).show();
            mTest.setText(arg2);
        }
    }
}
