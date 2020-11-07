package com.six.zhihu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.six.zhihu.NormalLog;
import com.six.zhihu.R;
import com.six.zhihu.entity.ConcernPerson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConcernPersonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<ConcernPerson> concernPeople;
    private OnConcernPersonClickListener onConcernPersonClickListener;

    public ConcernPersonAdapter(Context mContext) {
        NormalLog.log(this.getClass(),2,"ConcernPersonAdapter", 0);
        this.mContext = mContext;
        this.concernPeople = new ArrayList<>();
    }

    public ConcernPersonAdapter(Context mContext, List<ConcernPerson> concernPeople) {
        NormalLog.log(this.getClass(),2,"ConcernPersonAdapter", 0);
        this.mContext = mContext;
        this.concernPeople = concernPeople;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NormalLog.log(this.getClass(),2,"onCreatedViewHolder",0,parent,viewType);
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.item_concern_person, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        NormalLog.log(this.getClass(),2,"onCreatedViewHolder",2,viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NormalLog.log(this.getClass(),2,"onBindViewHolder",0,holder,position);
        ViewHolder vh = (ViewHolder) holder;
        ConcernPerson concernPerson = concernPeople.get(position);
        vh.imageHeader.setImageResource(concernPerson.getImageHeader());
        vh.name.setText(concernPerson.getName());
        vh.concernPerson = concernPerson;
        NormalLog.log(this.getClass(),2,"onBindViewHolder",1);

    }

    @Override
    public int getItemCount() {
        return concernPeople.size();
    }

    public List<ConcernPerson> getConcernPeople() {
        return concernPeople;
    }

    public void setConcernPeople(List<ConcernPerson> concernPeople) {
        this.concernPeople = concernPeople;
    }

    public OnConcernPersonClickListener getOnConcernPersonClickListener() {
        return onConcernPersonClickListener;
    }

    public void setOnConcernPersonClickListener(OnConcernPersonClickListener onConcernPersonClickListener) {
        this.onConcernPersonClickListener = onConcernPersonClickListener;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageHeader;
        private TextView name;
        private ConcernPerson concernPerson;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NormalLog.log(this.getClass(),2,"ViewHolder",0);
            imageHeader = itemView.findViewById(R.id.iv_concern_header);
            name = itemView.findViewById(R.id.tv_concern_name);
            itemView.setOnClickListener(view -> onConcernPersonClickListener.onItemClick(concernPerson));
            NormalLog.log(this.getClass(),2,"ViewHolder",1);
        }
    }

    public interface OnConcernPersonClickListener{
        void onItemClick(Serializable obj);
    }
}
