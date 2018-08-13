package com.chenzhen.loadmanagerobserver;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import com.chenzhen.loadmanagerobserver.adapter.CommonAdapter;
import java.util.ArrayList;
import java.util.List;
public class TargetActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        setStatuBar();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("" + i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new CommonAdapter(this,list));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("onScrollStateChanged", "newState : " + newState);


                if (recyclerView.canScrollVertically(1) == false){// 到底
                    Log.e("onScrollStateChanged", "newState : " + newState + " = " + "到底");

                }else { // 到顶
                    Log.e("onScrollStateChanged", "newState : " + newState + " = " + "到顶");
//                    if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                        getWindow().getDecorView().setSystemUiVisibility(~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//                    }
                }
                switch (newState){
                    // 静止
                    case RecyclerView.SCROLL_STATE_IDLE:
                        Log.e("onScrollStateChanged", "newState : " + newState + " = " + "静止");
//                        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//                        }

                        break;

                    //手指拖拽滑动
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        Log.e("onScrollStateChanged", "newState : " + newState + " = " + "手指拖拽滑动");
                        break;

                    //惯性自动滑动
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        Log.e("onScrollStateChanged", "newState : " + newState + " = " + "惯性自动滑动");
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0){
                    Log.e("onScrolled", "dy : " + dy  + " = " + "向上滑动");
                }else {
                    Log.e("onScrolled", "dy : " + dy  + " = " + "向下滑动");
                }
            }
        });

    }


    // 透明状态栏
    void setStatuBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        if (getSupportActionBar() != null){
            setSupportActionBar(toolBar);
        }
    }

}
