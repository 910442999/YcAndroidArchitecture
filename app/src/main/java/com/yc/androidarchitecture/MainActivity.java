package com.yc.androidarchitecture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yc.commonlibrary.base.BaseActivity;
import com.yc.commonlibrary.presenter.BasePresenter;
import com.yc.homelibrary.HomeActivity;
import com.yc.mylibrary.MyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.text1)
    TextView mText1;
    @BindView(R.id.text2)
    TextView mText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int initLayoutInflater() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.text1, R.id.text2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text1:
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.text2:
                startActivity(new Intent(this, MyActivity.class));
                break;
        }
    }
}
