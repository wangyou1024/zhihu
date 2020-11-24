package com.six.zhihu.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.six.zhihu.R;
import com.six.zhihu.activity.ProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create_center an instance of this fragment.
 */
public class MyFragment extends Fragment {
    TextView profile;
    
    public MyFragment() {
        // Required empty public constructor
    }

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
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
//        edit_my = (TextView) findViewById(R.layout.profile);
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        profile = view.findViewById(R.id.profile);
        profile.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), ProfileActivity.class);
            startActivity(intent);
        });
        return view;
    }
}