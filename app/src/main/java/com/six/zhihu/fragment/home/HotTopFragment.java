package com.six.zhihu.fragment.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.six.zhihu.R;
import com.six.zhihu.activity.RecommendActivity;
import com.six.zhihu.adapter.HotTopAdapter;
import com.six.zhihu.adapter.RecommendAdapter;
import com.six.zhihu.entity.HotTopEntity;
import com.six.zhihu.entity.RecommendEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotTopFragment#newInstance} factory method to
 * create an instance of this fragment.
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hot_top, container, false);
        refreshLayout = view.findViewById(R.id.hot_top_refreshLayout);
        this.refreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                // refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                getInfo();
            }
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
        hotTopAdapter.setOnItemClickListener(new RecommendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Serializable obj) {
                HotTopEntity hotTopEntity = (HotTopEntity) obj;
                Toast.makeText(getActivity(), hotTopEntity.getTitle(),Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), RecommendActivity.class);
//                intent.putExtra("recommendEntity", hotTopEntity);
//                startActivity(intent);
            }
        });
        getInfo();
        recyclerView.setAdapter(hotTopAdapter);
        return view;
    }

    public void getInfo(){
        ArrayList<HotTopEntity> hotTopEntities = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            HotTopEntity hotTopEntity = new HotTopEntity();
            hotTopEntity.setGrade(i+1);
            hotTopEntity.setTitle("如何看待越来越多年轻人追捧[摸鱼哲学]，拒绝努力的年轻人真比老一辈活得更通透吗？");
            hotTopEntity.setImage(R.mipmap.recommond_image);
            hotTopEntity.setHot("2304万热度");
            hotTopEntities.add(hotTopEntity);
        }
        hotTopAdapter.setHotTopEntities(hotTopEntities);
        refreshLayout.finishRefresh(true);
        // 重置数据
        hotTopAdapter.notifyDataSetChanged();
        Log.i("hotTopFragment after",hotTopAdapter.getHotTopEntities().size()+"");
    }
}