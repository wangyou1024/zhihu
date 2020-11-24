package com.six.zhihu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.six.zhihu.NormalLog;
import com.six.zhihu.R;
import com.six.zhihu.entity.MessageUser;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息发布者适配器
 */
public class MessageDynamicAdapter extends BaseAdapter {
    private final String TAG = "MessageDynamicAdapter";
    private LayoutInflater inflater;
    private List<MessageUser> messageUsers = new ArrayList<>();
    private Context context;

    public MessageDynamicAdapter(List<MessageUser> messageUsers, Context context) {
        this.messageUsers = messageUsers;
        this.context = context;
        inflater = inflater.from(context);
    }

    @Override
    public int getCount() {
        if (null != messageUsers) {
            return messageUsers.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return messageUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NormalLog.log(this.getClass(),2,"getView",0);
        ViewHolder viewHolder;
        if (convertView == null) {
            //获得界面
            convertView = inflater.inflate(R.layout.item_message_dynamic_layout,null);
            viewHolder = new ViewHolder();
            //设置相关属性
            viewHolder.user_img = (ImageView) convertView.findViewById(R.id.message_user_img);
            viewHolder.user_name = (TextView) convertView.findViewById(R.id.message_user_name);
            viewHolder.user_time_info = (TextView) convertView.findViewById(R.id.message_user_time_info);
            viewHolder.tv_question = (TextView) convertView.findViewById(R.id.tv_question);
            viewHolder.tv_question_response = (TextView) convertView.findViewById(R.id.tv_question_response);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.user_img.setImageResource(messageUsers.get(position).getMessage_user_img());
        viewHolder.user_name.setText(messageUsers.get(position).getMessage_user_name());
        viewHolder.user_time_info.setText(messageUsers.get(position).getMessage_user_time_info());
        viewHolder.tv_question.setText(messageUsers.get(position).getTv_question());
        viewHolder.tv_question_response.setText(messageUsers.get(position).getTv_question_response());
        NormalLog.log(this.getClass(),2,"getView",1);
        return convertView;
    }

    class ViewHolder {
        public ImageView user_img;
        public TextView user_name;
        public TextView user_time_info;
        public TextView tv_question;
        public TextView tv_question_response;
    }
}
