package com.yc.homelibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {


    @BindView(R2.id.home)
    TextView mHome;
    @BindView(R2.id.home_to_my)
    TextView mHomeToMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    //在点击事件的id 用 R2 而不是用R
    @OnClick({R2.id.home, R2.id.home_to_my})
    public void onViewClicked(View view) {
        //正确写法

        //不能直接用swich 而是用if else 的方式  并且id不能用R2 而是用R
        if (view.getId() == R.id.home) {
            //点击事件处理
            Toast.makeText(this,"这是首页",Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.home_to_my) {
            //点击事件处理
            Toast.makeText(this,"跳转到到我的页面",Toast.LENGTH_SHORT).show();
        }

    }
}
