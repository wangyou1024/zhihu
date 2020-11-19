package com.six.zhihu.fragment.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.six.zhihu.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CircleFragment#newInstance} factory method to
 * create_center an instance of this fragment.
 */
public class CircleFragment extends Fragment {

    public CircleFragment() {
        // Required empty public constructor
    }

    public static CircleFragment newInstance() {
        CircleFragment fragment = new CircleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the profile for this fragment
        return inflater.inflate(R.layout.fragment_circle, container, false);
    }
}