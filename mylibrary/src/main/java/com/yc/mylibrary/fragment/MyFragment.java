package com.yc.mylibrary.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yc.mylibrary.R;


/**
 * Created by zhanghan on 2018/7/31.
 */

public class MyFragment extends Fragment {
    private static MyFragment sMyFragment;

    public static MyFragment newInstance(String param1) {
        sMyFragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        sMyFragment.setArguments(args);
        return sMyFragment;
    }

    public MyFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment, container, false);
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");
        return view;
    }

}
