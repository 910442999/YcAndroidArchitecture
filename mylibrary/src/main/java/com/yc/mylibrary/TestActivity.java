package com.yc.mylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.Date;

@Route(path = "/my/TestActivity")
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
    @Override
    public void finish() {
        setResult(1002, getIntent().putExtra("arg2", new Date().toString()));
        super.finish();
    }
}
