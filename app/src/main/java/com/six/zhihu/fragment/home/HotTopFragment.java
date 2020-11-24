package com.six.zhihu.fragment.home;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.six.zhihu.NormalLog;
import com.six.zhihu.R;
import com.six.zhihu.adapter.HotTopAdapter;
import com.six.zhihu.dao.DBOpenHelper;
import com.six.zhihu.entity.HotTopEntity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotTopFragment#newInstance} factory method to
 * create_center an instance of this fragment.
 */
public class HotTopFragment extends Fragment {

    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private HotTopAdapter hotTopAdapter;
    private boolean hiddenHeader = false;


    public HotTopFragment() {
        // Required empty public constructor
    }

    public static HotTopFragment newInstance() {
        HotTopFragment fragment = new HotTopFragment();
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
        NormalLog.log(this.getClass(),2,"onCreateView",0);
        View view = inflater.inflate(R.layout.fragment_hot_top, container, false);
        refreshLayout = view.findViewById(R.id.hot_top_refreshLayout);
        this.refreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            // refreshLayout1.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            getInfo();
            Toast.makeText(getActivity(), "刷新成功",Toast.LENGTH_SHORT).show();
        });

        recyclerView = view.findViewById(R.id.hot_top_recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (hiddenHeader){
                    getActivity().findViewById(R.id.ll_home_header).setVisibility(View.GONE);
                }else {
                    getActivity().findViewById(R.id.ll_home_header).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy>0) {
                    hiddenHeader = true;
                }else {
                    hiddenHeader = false;
                }
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        hotTopAdapter = new HotTopAdapter(getActivity());
        hotTopAdapter.setOnItemClickListener(obj -> {
            HotTopEntity hotTopEntity = (HotTopEntity) obj;
            Toast.makeText(getActivity(), hotTopEntity.getTitle(),Toast.LENGTH_SHORT).show();
/*                Intent intent = new Intent();
                intent.setClass(getActivity(), RecommendActivity.class);
                intent.putExtra("recommendEntity", hotTopEntity);
                startActivity(intent);*/
        });
        getInfo();
        recyclerView.setAdapter(hotTopAdapter);
        NormalLog.log(this.getClass(),2,"onCreateView",1);
        return view;
    }

    public void getInfo(){
        NormalLog.log(this.getClass(),2,"getInfo",0);
        ArrayList<HotTopEntity> hotTopEntities = new ArrayList<>();
        DBOpenHelper dbOpenHelper = new DBOpenHelper(getActivity());
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from hottop", null);
        while (cursor.moveToNext()){
            Integer grade = cursor.getInt(cursor.getColumnIndex("grade"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            Integer image = cursor.getInt(cursor.getColumnIndex("image"));
            String hot = cursor.getString(cursor.getColumnIndex("hot"));
            HotTopEntity hotTopEntity = new HotTopEntity(grade, title, hot, image);
            hotTopEntities.add(hotTopEntity);
        }
        cursor.close();
        db.close();
        hotTopAdapter.setHotTopEntities(hotTopEntities);
        refreshLayout.finishRefresh(true);
        // 重置数据
        hotTopAdapter.notifyDataSetChanged();
        NormalLog.log(this.getClass(),2,"getInfo",1);
    }
}