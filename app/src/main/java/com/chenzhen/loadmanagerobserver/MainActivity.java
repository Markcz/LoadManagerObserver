package com.chenzhen.loadmanagerobserver;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.chenzhen.loadmanagerobserver.fragment.AudioFragment;
import com.chenzhen.loadmanagerobserver.fragment.ImageFragment;
import com.chenzhen.loadmanagerobserver.fragment.MeFragment;
import com.chenzhen.loadmanagerobserver.fragment.VideoFragment;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    BottomNavigationBar bottomBar;
    ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            AudioFragment audio = AudioFragment.newInstance("audio", "audio");
            fragments.add(audio);
            getSupportFragmentManager().beginTransaction().add(R.id.fl_container, fragments.get(0), "0").commit();
        }
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        init();
    }


    void init() {
        bottomBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomBar.addItem(new BottomNavigationItem(R.drawable.ic_audio_black_24dp, "音乐").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.ic_image_black_24dp, "图片").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.ic_video_black_24dp, "视频").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.ic_me_black_24dp, "我的").setActiveColorResource(R.color.colorAccent))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomBar.setTabSelectedListener(this);

        ImageFragment image = ImageFragment.newInstance("image", "image");
        VideoFragment video = VideoFragment.newInstance("video", "video");
        MeFragment me = MeFragment.newInstance("me", "me");

        fragments.add(image);
        fragments.add(video);
        fragments.add(me);

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            setFull();
        }
    }

    //完全沉浸式状态栏
    void setFull(){
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }

    void switchFragment(Fragment from, Fragment to, int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (!to.isAdded()) {
            transaction.hide(from).add(R.id.fl_container, to, position + "").commit();
        } else {
            transaction.hide(from).show(to).commit();
        }
    }


    @Override
    public void onTabSelected(int position) {
        Log.e("onTab", "onTabSelected 1 : " + position + "");
        Log.e("onTab", "onTabSelected 2 : " + toPosition + "");
        toPosition = position;

    }

    int toPosition = 0;

    @Override
    public void onTabUnselected(int position) {
        Log.e("onTab", "onTabUnselected 2 : " + position + "");
        Fragment from = fragments.get(position);
        Fragment to = fragments.get(toPosition);
        switchFragment(from, to, position);
    }

    @Override
    public void onTabReselected(int position) {
        ///Log.e("onTab","onTabReselected : "+position+"");
        return;
    }
}
