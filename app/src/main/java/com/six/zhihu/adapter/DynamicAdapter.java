package com.six.zhihu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.six.zhihu.NormalLog;
import com.six.zhihu.R;
import com.six.zhihu.entity.DynamicEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DynamicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context mContext;
    private List<DynamicEntity> dynamicEntities;
    private OnItemClickListener onItemClickListener;

    public DynamicAdapter(Context mContext) {
        this.mContext = mContext;
        this.dynamicEntities = new ArrayList<>();
    }

    public DynamicAdapter(Context mContext, List<DynamicEntity> dynamicEntities) {
        NormalLog.log(this.getClass(),2,"DynamicAdapter",0);
        this.mContext = mContext;
        this.dynamicEntities = dynamicEntities;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NormalLog.log(this.getClass(),2,"DynamicAdapter",0);
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.item_dynamic_layout,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NormalLog.log(this.getClass(),2,"onBindViewHolder",0, position);
        ViewHolder vh = (ViewHolder) holder;
        DynamicEntity dynamicEntity = dynamicEntities.get(position);
        vh.authorHeader.setImageResource(dynamicEntity.getAuthorHeader());
        vh.authorName.setText(dynamicEntity.getAuthorName());
        vh.updateTime.setText(dynamicEntity.getUpdateTime());
        vh.title.setText(dynamicEntity.getTitle());
        vh.content.setText(dynamicEntity.getContent());
        vh.agree.setText(dynamicEntity.getAgree().toString());
        vh.comment.setText(dynamicEntity.getComment().toString());
        vh.dynamicEntity = dynamicEntity;
        NormalLog.log(this.getClass(),2,"onBindViewHolder",1);

    }

    @Override
    public int getItemCount() {
        return this.dynamicEntities.size();
    }

    public List<DynamicEntity> getDynamicEntities() {
        return dynamicEntities;
    }

    public void setDynamicEntities(List<DynamicEntity> dynamicEntities) {
        this.dynamicEntities = dynamicEntities;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView authorHeader;
        private TextView authorName;
        private TextView updateTime;
        private TextView title;
        private TextView content;
        private TextView agree;
        private TextView comment;
        private DynamicEntity dynamicEntity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NormalLog.log(this.getClass(),2,"ViewHolder",0);
            authorHeader = itemView.findViewById(R.id.dynamic_author_header);
            authorName = itemView.findViewById(R.id.dynamic_author_name);
            updateTime = itemView.findViewById(R.id.dynamic_update_time);
            title = itemView.findViewById(R.id.dynamic_title);
            content = itemView.findViewById(R.id.dynamic_content);
            agree = itemView.findViewById(R.id.dynamic_agree);
            comment = itemView.findViewById(R.id.dynamic_comment);
            itemView.setOnClickListener(view -> {
                onItemClickListener.onItemClick(dynamicEntity);
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Serializable obj);
    }

}
