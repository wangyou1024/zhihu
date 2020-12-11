package com.six.zhihu.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.six.zhihu.NormalLog;
import com.six.zhihu.R;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.six.zhihu.activity.CreationActivity;
import com.six.zhihu.activity.MyAttentionActivity;
import com.six.zhihu.activity.ProfileActivity;
import com.six.zhihu.dao.DBOpenHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {
    private TextView profile;
    private DBOpenHelper helper;
    private TextView my_name;
    private RelativeLayout rlCreation;
    private RelativeLayout rlConcern;

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
    public void onResume() {
        super.onResume();
        NormalLog.log(this.getClass(), 2, "onResume", 0);
        helper = new DBOpenHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        cursor.moveToFirst();
        if (my_name != null) {
            my_name.setText(cursor.getString(2));
        }
        NormalLog.log(this.getClass(), 2, "onResume", 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the profile for this fragment
//        edit_my = (TextView) findViewById(R.layout.profile);
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        helper = new DBOpenHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        cursor.moveToFirst();
        my_name = view.findViewById(R.id.my_name);
        my_name.setText(cursor.getString(2));
        rlCreation = view.findViewById(R.id.rl_creation);
        rlCreation.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CreationActivity.class);
            startActivity(intent);
        });
        rlConcern = view.findViewById(R.id.rl_concern);
        rlConcern.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MyAttentionActivity.class);
            startActivity(intent);
        });
        profile = view.findViewById(R.id.profile);
        profile.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), ProfileActivity.class);
            startActivity(intent);
        });
        db.close();
        cursor.close();
        return view;
    }
}