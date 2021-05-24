package com.zlyandroid.titlebar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zlylib.titlebarlib.ActionBarCommon;
import com.zlylib.titlebarlib.ActionBarSearch;
import com.zlylib.titlebarlib.ActionBarSuper;
import com.zlylib.titlebarlib.OnActionBarChildClickListener;
import com.zlylib.titlebarlib.widget.ActionBarEx;

public class ShowAllActivity  extends AppCompatActivity {

    private ActionBarCommon simple_action_bar_1;
    private ActionBarCommon simple_action_bar_2;
    private ActionBarCommon simple_action_bar_3;
    private ActionBarSearch search_action_bar_1;
    private ActionBarSearch search_action_bar_2;
    private ActionBarEx action_bar;
    private ActionBarEx action_bar_ex_2;
    private ActionBarSuper action_bar_super_2;

    public static void start(Context context) {
        Intent intent = new Intent(context, ShowAllActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        final Context context = this;

        action_bar = findViewById(R.id.action_bar);
        simple_action_bar_1 = findViewById(R.id.simple_action_bar_1);
        simple_action_bar_2 = findViewById(R.id.simple_action_bar_2);
        simple_action_bar_3 = findViewById(R.id.simple_action_bar_3);
        search_action_bar_1 = findViewById(R.id.search_action_bar_1);
        search_action_bar_2 = findViewById(R.id.search_action_bar_2);
        action_bar_ex_2 = findViewById(R.id.action_bar_ex_2);
        action_bar_super_2 = findViewById(R.id.action_bar_super_2);


        action_bar.getView(R.id.tv_title_refresh_bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Math.random() <= 0.1) {
                    action_bar.setBackgroundResource(R.drawable.title_bar_custom_bg);
                } else if (Math.random() <= 0.2) {
                    action_bar.setBackgroundResource(R.mipmap.action_bar_img);
                } else if (Math.random() <= 0.3) {
                    action_bar.setBackgroundResource(R.drawable.ic_launcher_background);
                } else {
                    int r = (int) (Math.random() * 255);
                    int g = (int) (Math.random() * 255);
                    int b = (int) (Math.random() * 255);
                    action_bar.setBackgroundColor(Color.argb(255, r, g, b));
                }
                action_bar.refreshStatusBarMode();
            }
        });
        simple_action_bar_1.setOnLeftIconClickListener(new OnActionBarChildClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "onLeftImageClick", Toast.LENGTH_SHORT).show();
            }
        });
        simple_action_bar_1.setOnLeftTextClickListener(new OnActionBarChildClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "onLeftTextClick", Toast.LENGTH_SHORT).show();
            }
        });
        simple_action_bar_1.setOnRightTextClickListener(new OnActionBarChildClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "onRightTextClick", Toast.LENGTH_SHORT).show();
            }
        });
        simple_action_bar_1.setOnRightIconClickListener(new OnActionBarChildClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "onRightImageClick", Toast.LENGTH_SHORT).show();
            }
        });



        findViewById(R.id.tv_show_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simple_action_bar_1.getForegroundLayer().setVisibility(View.VISIBLE);
                simple_action_bar_2.getForegroundLayer().setVisibility(View.VISIBLE);
                simple_action_bar_3.getForegroundLayer().setVisibility(View.VISIBLE);
                search_action_bar_1.getForegroundLayer().setVisibility(View.VISIBLE);
                search_action_bar_2.getForegroundLayer().setVisibility(View.VISIBLE);
                action_bar_ex_2.getForegroundLayer().setVisibility(View.VISIBLE);
                action_bar_super_2.getForegroundLayer().setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        simple_action_bar_1.getForegroundLayer().setVisibility(View.GONE);
                        simple_action_bar_2.getForegroundLayer().setVisibility(View.GONE);
                        simple_action_bar_3.getForegroundLayer().setVisibility(View.GONE);
                        search_action_bar_1.getForegroundLayer().setVisibility(View.GONE);
                        search_action_bar_2.getForegroundLayer().setVisibility(View.GONE);
                        action_bar_ex_2.getForegroundLayer().setVisibility(View.GONE);
                        action_bar_super_2.getForegroundLayer().setVisibility(View.GONE);
                    }
                }, 2000);
            }
        });

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
