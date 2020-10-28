package com.six.zhihu.fragment.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.six.zhihu.R;
import com.six.zhihu.activity.RecommendActivity;
import com.six.zhihu.adapter.RecommendAdapter;
import com.six.zhihu.entity.RecommendEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecommendFragment#newInstance} factory method to
 * create an instance of this fragment.
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommond, container, false);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        this.refreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        this.refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                // refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                getInfo(true);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                // refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                getInfo(false);
            }
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
        recommendAdapter.setOnItemClickListener(new RecommendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Serializable obj) {
                RecommendEntity recommendEntity = (RecommendEntity) obj;
                Toast.makeText(getActivity(), recommendEntity.getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getActivity(), RecommendActivity.class);
                intent.putExtra("recommendEntity", recommendEntity);
                startActivity(intent);
            }
        });
        getInfo(true);
        recyclerView.setAdapter(recommendAdapter);
        return view;
    }

    public void getInfo(boolean refresh){
        List<RecommendEntity> recommendEntities = new ArrayList<>();
        if (refresh){
            pageNum = 1;
        } else{
            pageNum++;
        }
        for (int i = (pageNum-1)*limit; i < pageNum*limit; i++){
            RecommendEntity recommendEntity = new RecommendEntity();
            recommendEntity.setTitle(i+"有哪些值得大学生收藏的网站？");
            recommendEntity.setHeader(R.drawable.shape_search);
            if (i%2 == 0) {
                recommendEntity.setImage(R.mipmap.recommond_image);
            }
            recommendEntity.setAuthor("见长");
            recommendEntity.setConcern("超过13.8万的用户关注了TA");
            recommendEntity.setIntroduce("大学时期时间相对充裕，在认真学习本专业的同时，学习更多的技能也是十分必要,学习更多的技能也是十分必要");
            recommendEntity.setAgree(110000);
            recommendEntity.setComment(104);
            recommendEntities.add(recommendEntity);
        }
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
    }
}