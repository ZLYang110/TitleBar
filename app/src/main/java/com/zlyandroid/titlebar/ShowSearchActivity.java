package com.zlyandroid.titlebar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.zlylib.titlebarlib.ActionBarCommon;
import com.zlylib.titlebarlib.ActionBarSearch;
import com.zlylib.titlebarlib.ActionSearch;
import com.zlylib.titlebarlib.OnActionBarChildClickListener;
import com.zlylib.titlebarlib.widget.ActionBarEx;

public class ShowSearchActivity extends AppCompatActivity {
    private static String TAG="ShowSearchActivity";
    private ActionSearch simple_action_bar_search;
    private TextView tv_msg;

    Toast toast;
    public static void start(Context context) {
        Intent intent = new Intent(context, ShowSearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        tv_msg = findViewById(R.id.tv_msg);
        simple_action_bar_search = findViewById(R.id.simple_action_bar_search);
        simple_action_bar_search.setOnSearchActionListener(new ActionSearch.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(enabled){
                    Snackbar.make(simple_action_bar_search, "打开了搜索框" , Snackbar.LENGTH_SHORT).show();
                }else {
                    Snackbar.make(simple_action_bar_search, "关闭了搜索框" , Snackbar.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                Log.i(TAG, "Edit: 内容" +text);
                Snackbar.make(simple_action_bar_search, "点击软件盘搜索按钮，内容="+text, Snackbar.LENGTH_SHORT).show();
            }
        });
        simple_action_bar_search.getSearchEditView().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i(TAG, "beforeTextChanged: 输入过程中执行该方法");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG, "onTextChanged: 输入前确认执行该方法");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i(TAG, "afterTextChanged: 输入结束执行该方法 = " +s.toString());
                tv_msg.setText(s.toString());
            }
        });

    }
}
