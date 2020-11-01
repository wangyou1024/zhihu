package com.six.zhihu.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.six.zhihu.R;
import com.six.zhihu.entity.HotTopEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HotTopAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<HotTopEntity> hotTopEntities;
    private RecommendAdapter.OnItemClickListener onItemClickListener;

    public HotTopAdapter(Context context) {
        this.mContext = context;
        this.hotTopEntities = new ArrayList<>();
    }

    public HotTopAdapter(Context mContext, List<HotTopEntity> hotTopEntities) {
        this.mContext = mContext;
        this.hotTopEntities = hotTopEntities;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.item_hot_top_layout,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.i("adapter", "onBindViewHolder: ");
        ViewHolder vh = (ViewHolder) holder;
        vh.tvGrade.setText(hotTopEntities.get(position).getGrade().toString());
        vh.tvTitle.setText(hotTopEntities.get(position).getTitle());
        vh.tvHot.setText(hotTopEntities.get(position).getHot());
        vh.imageView.setImageResource(hotTopEntities.get(position).getImage());
        switch (position){
            case 0:vh.tvGrade.setBackgroundResource(R.color.hot_top_first);break;
            case 1:vh.tvGrade.setBackgroundResource(R.color.hot_top_second);break;
            case 2:vh.tvGrade.setBackgroundResource(R.color.hot_top_third);break;
            default:vh.tvGrade.setBackgroundResource(R.color.white);vh.tvGrade.setTextColor(R.color.four);break;
        }
        vh.hotTopEntity = hotTopEntities.get(position);
    }

    @Override
    public int getItemCount() {
        return hotTopEntities.size();
    }

    public RecommendAdapter.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(RecommendAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<HotTopEntity> getHotTopEntities() {
        return hotTopEntities;
    }

    public void setHotTopEntities(List<HotTopEntity> hotTopEntities) {
        this.hotTopEntities = hotTopEntities;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvGrade;
        private TextView tvTitle;
        private TextView tvHot;
        private ImageView imageView;
        private HotTopEntity hotTopEntity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGrade = itemView.findViewById(R.id.tv_hot_top_grade);
            tvTitle = itemView.findViewById(R.id.tv_hot_top_title);
            tvHot = itemView.findViewById(R.id.tv_hot_top_hot);
            imageView = itemView.findViewById(R.id.iv_hot_top_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(hotTopEntity);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Serializable obj);
    }
}
