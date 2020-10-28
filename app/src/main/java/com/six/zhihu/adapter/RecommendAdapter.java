package com.six.zhihu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.six.zhihu.R;
import com.six.zhihu.entity.RecommendEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<RecommendEntity> recommendEntities;
    private OnItemClickListener onItemClickListener;

    public RecommendAdapter(Context context) {
        this.mContext = context;
        this.recommendEntities = new ArrayList<>();
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
        vh.recommendEntity = recommendEntity;
        vh.popupMenu.setOnClickListener(bindOnClickListener(position));
    }

    public View.OnClickListener bindOnClickListener(final int position){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(mContext,view);
                popupMenu.getMenuInflater().inflate(R.menu.item_recommend_menu,popupMenu.getMenu());

                //弹出式菜单的菜单项点击事件
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.block_program:
                                Toast.makeText(mContext, recommendEntities.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.block_key_word:
                                Toast.makeText(mContext, "关键词："+recommendEntities.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.report:
                                Toast.makeText(mContext, "举报："+recommendEntities.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });

                //弹出式菜单的菜单的关闭事件
                popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu menu) {
//                        Toast.makeText(mContext, "menu close.", Toast.LENGTH_SHORT).show();
                    }
                });
                popupMenu.show();
            }
        };
    }

    @Override
    public int getItemCount() {
        return recommendEntities.size();
    }

    public List<RecommendEntity> getRecommendEntities() {
        return recommendEntities;
    }
    
    public void setRecommendEntities(List<RecommendEntity> recommendEntities) {
        this.recommendEntities = recommendEntities;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private TextView tvAuthor;
        private ImageView ivHeader;
        private ImageView ivImage;
        private TextView tvConcern;
        private TextView tvIntroduce;
        private TextView tvAgree;
        private TextView tvComment;
        private RecommendEntity recommendEntity;
        private TextView popupMenu;

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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(recommendEntity);
                }
            });
            popupMenu = itemView.findViewById(R.id.tv_recommend_more_action);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Serializable obj);
    }
}
