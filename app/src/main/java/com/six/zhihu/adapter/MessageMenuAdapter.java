package com.six.zhihu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.six.zhihu.NormalLog;
import com.six.zhihu.R;
import com.six.zhihu.entity.MessageMenuUser;

import java.util.ArrayList;
import java.util.List;

public class MessageMenuAdapter  extends BaseAdapter {
    private final String TAG = "MessageMenuAdapter";
    private LayoutInflater inflater;
    private List<MessageMenuUser> messageMenuUsers = new ArrayList<>();
    private Context context;

    public MessageMenuAdapter(List<MessageMenuUser> messageMenuUsers, Context context) {
        this.messageMenuUsers = messageMenuUsers;
        this.context = context;
        inflater = inflater.from(context);
    }

    @Override
    public int getCount() {
        if (null == messageMenuUsers) {
            return 0;
        } else {
            return messageMenuUsers.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return messageMenuUsers.get(position);
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
            convertView = inflater.inflate(R.layout.item_message,null);
            viewHolder = new ViewHolder();
            //设置相关属性
            viewHolder.iv_user_img = (ImageView) convertView.findViewById(R.id.iv_user_img);
            viewHolder.tv_user_name = (TextView) convertView.findViewById(R.id.tv_user_name);
            viewHolder.tv_user_message = (TextView) convertView.findViewById(R.id.tv_user_message);
            viewHolder.tv_message_time = (TextView) convertView.findViewById(R.id.tv_message_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.iv_user_img.setImageResource(messageMenuUsers.get(position).getIv_user_img());
        viewHolder.tv_user_name.setText(messageMenuUsers.get(position).getTv_user_name());
        viewHolder.tv_user_message.setText(messageMenuUsers.get(position).getTv_user_message());
        viewHolder.tv_message_time.setText(messageMenuUsers.get(position).getTv_message_time());
        NormalLog.log(this.getClass(),2,"getView",1);
        return convertView;
    }

    class ViewHolder {
        public ImageView iv_user_img;
        public TextView tv_user_name;
        public TextView tv_user_message;
        public TextView tv_message_time;
    }
}
