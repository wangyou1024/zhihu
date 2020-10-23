package com.six.zhihu.fragment.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.six.zhihu.R;
import com.six.zhihu.adapter.RecommendAdapter;
import com.six.zhihu.entity.RecommendEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecommendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendFragment extends Fragment {

    private RecyclerView recyclerView;

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
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<RecommendEntity> recommendEntities = new ArrayList<>();
        for (int i = 0; i < 8; i++){
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
        RecommendAdapter recommendAdapter = new RecommendAdapter(getActivity(), recommendEntities);
        recyclerView.setAdapter(recommendAdapter);
        return view;
    }
}