package com.six.zhihu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.six.zhihu.R;
import com.six.zhihu.entity.RecommendEntity;

import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<RecommendEntity> recommendEntities;

    public RecommendAdapter(Context context) {
        this.mContext = context;
    }

    public RecommendAdapter(Context context, List<RecommendEntity> recommendEntities) {
        this.mContext = context;
        this.recommendEntities = recommendEntities;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.item_recommend_layout,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        RecommendEntity recommendEntity = recommendEntities.get(position);
        vh.tvTitle.setText(recommendEntity.getTitle());
        vh.tvAuthor.setText(recommendEntity.getAuthor());
        vh.ivHeader.setImageResource(recommendEntity.getHeader());
        if (recommendEntity.getImage() == null){
            vh.ivImage.setVisibility(View.GONE);
        }
        else {
            vh.ivImage.setImageResource(recommendEntity.getImage());
        }
        vh.tvConcern.setText(recommendEntity.getConcern());
        vh.tvIntroduce.setText(recommendEntity.getIntroduce());
        vh.tvAgree.setText(recommendEntity.getAgree().toString());
        vh.tvComment.setText(recommendEntity.getComment().toString());
    }

    @Override
    public int getItemCount() {
        return recommendEntities.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private TextView tvAuthor;
        private ImageView ivHeader;
        private ImageView ivImage;
        private TextView tvConcern;
        private TextView tvIntroduce;
        private TextView tvAgree;
        private TextView tvComment;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_recommend_title);
            tvAuthor = itemView.findViewById(R.id.tv_recommend_author);
            ivHeader = itemView.findViewById(R.id.iv_recommend_header);
            ivImage = itemView.findViewById(R.id.iv_recommend_image);
            tvConcern = itemView.findViewById(R.id.tv_recommend_concern);
            tvIntroduce = itemView.findViewById(R.id.tv_recommend_introduce);
            tvAgree = itemView.findViewById(R.id.tv_recommend_agree);
            tvComment = itemView.findViewById(R.id.tv_recommend_comment);
        }
    }
}
