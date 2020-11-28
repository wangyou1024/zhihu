package com.six.zhihu.fragment.search;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.six.zhihu.R;


public class SportFragment extends Fragment {

    public SportFragment() {
    }

    public static SportFragment newInstance() {
        SportFragment fragment = new SportFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_sport, container, false);
        return v;
    }
}