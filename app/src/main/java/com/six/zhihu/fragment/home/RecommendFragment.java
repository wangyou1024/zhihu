package com.six.zhihu.fragment.home;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.six.zhihu.NormalLog;
import com.six.zhihu.R;
import com.six.zhihu.activity.RecommendActivity;
import com.six.zhihu.activity.content.ContentActivity;
import com.six.zhihu.adapter.RecommendAdapter;
import com.six.zhihu.dao.DBOpenHelper;
import com.six.zhihu.entity.RecommendEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecommendFragment#newInstance} factory method to
 * create an instance of this fragment.
 * create_center an instance of this fragment.
 */
public class RecommendFragment extends Fragment {

    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private Integer pageNum = 1;
    private Integer limit = 5;
    private RecommendAdapter recommendAdapter;
    private boolean hiddenHeader = false;

    public RecommendFragment() {
        // Required empty public constructor
    }


    public static RecommendFragment newInstance() {
        RecommendFragment fragment = new RecommendFragment();
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
        View view = inflater.inflate(R.layout.fragment_recommond, container, false);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        this.refreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        this.refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()));
        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            // refreshLayout1.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            getInfo(true);
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout1 -> {
            // refreshLayout1.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            getInfo(false);
        });

        recyclerView = view.findViewById(R.id.recyclerView);
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
        recommendAdapter = new RecommendAdapter(getActivity());
        recommendAdapter.setOnItemClickListener(obj -> {
            RecommendEntity recommendEntity = (RecommendEntity) obj;
            Toast.makeText(getActivity(), recommendEntity.getTitle(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), ContentActivity.class);
            intent.putExtra("id", (int) (1 + Math.random() * 20));
            intent.putExtra("recommendEntity", recommendEntity);
            startActivity(intent);
        });
        getInfo(true);
        recyclerView.setAdapter(recommendAdapter);
        NormalLog.log(this.getClass(),2,"onCreateView",1);

        return view;
    }

    public void getInfo(boolean refresh){
        NormalLog.log(this.getClass(),2,"getInfo",0);
        if (refresh){
            pageNum = 1;
        } else{
            pageNum++;
        }
        List<RecommendEntity> recommendEntities = new ArrayList<>();
        DBOpenHelper dbOpenHelper = new DBOpenHelper(getActivity());
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from recommend limit ? offset ?", new String[]{String.valueOf(limit), String.valueOf((pageNum - 1) * limit)});
        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String author = cursor.getString(cursor.getColumnIndex("author"));
            String concern = cursor.getString(cursor.getColumnIndex("concern"));
            Integer header = cursor.getInt(cursor.getColumnIndex("header"));
            Integer image = cursor.getInt(cursor.getColumnIndex("image"));
            if (image == 0){
                image = null;
            }
            String introduce = cursor.getString(cursor.getColumnIndex("introduce"));
            Integer agree = cursor.getInt(cursor.getColumnIndex("agree"));
            Integer comment = cursor.getInt(cursor.getColumnIndex("comment"));
            RecommendEntity recommendEntity = new RecommendEntity(title, author, concern, header, image, introduce, agree, comment);
            recommendEntities.add(recommendEntity);
        }
        cursor.close();
        db.close();
        if (refresh){
            recommendAdapter.setRecommendEntities(recommendEntities);
            refreshLayout.finishRefresh(true);
            // 重置数据
            recommendAdapter.notifyDataSetChanged();
        }else{
            recommendAdapter.getRecommendEntities().addAll(recommendEntities);
            refreshLayout.finishLoadMore(true);
            // 重置数据
            recommendAdapter.notifyDataSetChanged();
        }
        NormalLog.log(this.getClass(),2,"getInfo",1);
    }

}