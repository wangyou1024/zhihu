package com.six.zhihu.fragment.home;

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
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.six.zhihu.NormalLog;
import com.six.zhihu.R;
import com.six.zhihu.adapter.ConcernPersonAdapter;
import com.six.zhihu.adapter.DynamicAdapter;
import com.six.zhihu.dao.DBOpenHelper;
import com.six.zhihu.entity.ConcernPerson;
import com.six.zhihu.entity.DynamicEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConcernFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConcernFragment extends Fragment {

    private RecyclerView dynamicRecyclerView;
    private RecyclerView personRecyclerView;
    private RefreshLayout refreshLayout;
    private Integer pageNum = 1;
    private Integer limit = 5;
    private DynamicAdapter dynamicAdapter;
    private ConcernPersonAdapter concernPersonAdapter;
    private boolean hiddenHeader = false;


    public ConcernFragment() {
        // Required empty public constructor
    }

    public static ConcernFragment newInstance() {
        ConcernFragment fragment = new ConcernFragment();
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
        NormalLog.log(this.getClass(),2,"onCreateView",0);
        View view= inflater.inflate(R.layout.fragment_concern, container, false);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        this.refreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        this.refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()));
        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            // refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            getDynamic(true);
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout1 -> {
            // refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            getDynamic(false);
        });

        dynamicRecyclerView = view.findViewById(R.id.dynamic_recyclerView);
        dynamicRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        dynamicRecyclerView.setLayoutManager(linearLayoutManager);
        dynamicAdapter = new DynamicAdapter(getActivity());
        dynamicAdapter.setOnItemClickListener(new DynamicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Serializable obj) {
                DynamicEntity dynamicEntity = (DynamicEntity) obj;
                Toast.makeText(getActivity(), dynamicEntity.getTitle(),Toast.LENGTH_SHORT).show();
/*                Intent intent = new Intent();
                intent.setClass(getActivity(), RecommendActivity.class);
                intent.putExtra("recommendEntity", dynamicEntity);
                startActivity(intent);*/
            }
        });
        getDynamic(true);
        dynamicRecyclerView.setAdapter(dynamicAdapter);

        personRecyclerView = view.findViewById(R.id.person_recyclerView);
        LinearLayoutManager personLinearLayout = new LinearLayoutManager(getActivity());
        personLinearLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        personRecyclerView.setLayoutManager(personLinearLayout);
        concernPersonAdapter = new ConcernPersonAdapter(getContext());
        concernPersonAdapter.setOnConcernPersonClickListener(new ConcernPersonAdapter.OnConcernPersonClickListener() {
            @Override
            public void onItemClick(Serializable obj) {
                ConcernPerson concernPerson = (ConcernPerson) obj;
                Toast.makeText(getActivity(), concernPerson.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        getConcernPerson();
        personRecyclerView.setAdapter(concernPersonAdapter);
        NormalLog.log(this.getClass(),2,"onCreateView",1);
        return view;
    }

    public void getDynamic(boolean refresh){
        NormalLog.log(this.getClass(),2,"getDynamic",0,refresh);
        List<DynamicEntity> dynamicEntities = new ArrayList<>();
        if (refresh){
            pageNum = 1;
        } else{
            pageNum++;
        }
        DBOpenHelper dbOpenHelper = new DBOpenHelper(getActivity());
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from dynamic limit ? offset ?", new String[]{String.valueOf(limit), String.valueOf(((pageNum - 1) * limit))});
        while (cursor.moveToNext()){
            Integer authorHeader = cursor.getInt(cursor.getColumnIndex("authorHeader"));
            String authorName = cursor.getString(cursor.getColumnIndex("authorName"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String updateTime = cursor.getString(cursor.getColumnIndex("updateTime"));
            Integer agree = cursor.getInt(cursor.getColumnIndex("agree"));
            Integer comment = cursor.getInt(cursor.getColumnIndex("comment"));
            DynamicEntity dynamicEntity = new DynamicEntity(authorHeader, authorName, updateTime, title, content, agree, comment);
            dynamicEntities.add(dynamicEntity);
        }
        cursor.close();
        db.close();
        /*for (int i = (pageNum-1)*limit; i < pageNum*limit; i++){
            DynamicEntity dynamicEntity = new DynamicEntity();
            dynamicEntity.setAuthorHeader(R.mipmap.header);
            dynamicEntity.setAuthorName("张大人");
            dynamicEntity.setTitle("Dubbo分布式架构搭建教育PC站-微信支付");
            dynamicEntity.setContent("创建二维码安装qrcodejs2（注意：安装的是qrcodejs2，不要安装qrcode，否则会报错）npm install qrcodejs2\n" +
                    "--save页面中引入《el-dialog:visible.sync=\"enadbojowefwfweiwjelksndfiwjkjsldjfow\"");
            dynamicEntity.setAgree(10);
            dynamicEntity.setComment(14);
            dynamicEntities.add(dynamicEntity);
        }*/
        if (refresh){
            dynamicAdapter.setDynamicEntities(dynamicEntities);
            refreshLayout.finishRefresh(true);
            // 重置数据
            dynamicAdapter.notifyDataSetChanged();
        }else{
            dynamicAdapter.getDynamicEntities().addAll(dynamicEntities);
            refreshLayout.finishLoadMore(true);
            // 重置数据
            dynamicAdapter.notifyDataSetChanged();
        }
        NormalLog.log(this.getClass(),2,"getDynamic",1);

    }

    public void getConcernPerson(){
        NormalLog.log(this.getClass(),2,"getConcernPerson",0);
        List<ConcernPerson> concernPeople = new ArrayList<>();
        DBOpenHelper dbOpenHelper = new DBOpenHelper(getActivity());
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from concern", null);
        while (cursor.moveToNext()){
            int imageHeader = cursor.getInt(cursor.getColumnIndex("imageHeader"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            ConcernPerson concernPerson = new ConcernPerson(imageHeader, name);
            concernPeople.add(concernPerson);
        }
        cursor.close();
        db.close();
/*        for (int i = 0; i < 10; i++){
            ConcernPerson concernPerson = new ConcernPerson();
            concernPerson.setImageHeader(R.mipmap.header);
            concernPerson.setName("你好");
            concernPeople.add(concernPerson);
        }*/
        concernPersonAdapter.setConcernPeople(concernPeople);
        dynamicAdapter.notifyDataSetChanged();
        NormalLog.log(this.getClass(),2,"getConcernPerson",1);
    }


}