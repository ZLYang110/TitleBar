package com.zlyandroid.titlebar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.zlylib.titlebarlib.ActionBarCommon;
import com.zlylib.titlebarlib.ActionBarSearch;
import com.zlylib.titlebarlib.OnActionBarChildClickListener;
import com.zlylib.titlebarlib.widget.ActionBarEx;

public class ShowBgActivity extends AppCompatActivity {

    private ActionBarCommon action_bar_bg;


    public static void start(Context context) {
        Intent intent = new Intent(context, ShowBgActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bg);
        action_bar_bg = findViewById(R.id.action_bar_bg);
        action_bar_bg.getLeftIconView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        action_bar_bg.getRightIconView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Snackbar.make(action_bar_bg, "点击菜单", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
