package com.six.zhihu.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.six.zhihu.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoundFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoundFragment extends Fragment {


    public FoundFragment() {
        // Required empty public constructor
    }

    public static FoundFragment newInstance() {
        FoundFragment fragment = new FoundFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_found, container, false);
    }
}