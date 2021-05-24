package com.zlyandroid.titlebar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zlylib.titlebarlib.ActionBarCommon;
import com.zlylib.titlebarlib.ActionBarSearch;
import com.zlylib.titlebarlib.OnActionBarChildClickListener;
import com.zlylib.titlebarlib.widget.ActionBarEx;

/**
 * @author zhangliyang
 * @date 2019/11/16
 * @github https://github.com/ZLYang110
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout ll_all, ll_search, ll_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_all = findViewById(R.id.ll_all);
        ll_search = findViewById(R.id.ll_search);
        ll_bg = findViewById(R.id.ll_bg);
        ll_all.setOnClickListener(this);
        ll_search.setOnClickListener(this);
        ll_bg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_all:
                ShowAllActivity.start(this);
                break;
            case R.id.ll_search:
                ShowSearchActivity.start(this);
                break;
            case R.id.ll_bg:
                ShowBgActivity.start(this);
                break;

        }
    }
}
