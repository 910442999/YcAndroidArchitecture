package com.yc.mylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyActivity extends AppCompatActivity {

    @BindView(R2.id.my)
    TextView mMy;
    @BindView(R2.id.my_to_home)
    TextView mMyToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.my, R2.id.my_to_home})
    public void onViewClicked(View view) {
        //正确写法

        //不能直接用swich 而是用if else 的方式  并且id不能用R2 而是用R
        if (view.getId() == R.id.my) {
            //点击事件处理
            Toast.makeText(this,"这是我的页面",Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.my_to_home) {
            //点击事件处理
            Toast.makeText(this,"跳转到到首页页面",Toast.LENGTH_SHORT).show();
        }
    }
}
