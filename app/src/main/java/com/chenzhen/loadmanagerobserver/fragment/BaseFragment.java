package com.chenzhen.loadmanagerobserver.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenzhen.loadmanagerobserver.R;

/**
 * Created by chenzhen on 2018/5/3.
 */

public class BaseFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base,container,false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.e("BaseFragment"," onHiddenChanged  :" + hidden);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e("BaseFragment"," setUserVisibleHint  :" + isVisibleToUser);
    }
}
