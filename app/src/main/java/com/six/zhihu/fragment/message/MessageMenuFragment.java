package com.six.zhihu.fragment.message;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.six.zhihu.NormalLog;
import com.six.zhihu.R;
import com.six.zhihu.adapter.MessageDynamicAdapter;
import com.six.zhihu.adapter.MessageMenuAdapter;
import com.six.zhihu.entity.MessageMenuUser;
import com.six.zhihu.entity.MessageUser;

import java.util.ArrayList;
import java.util.List;

public class MessageMenuFragment extends Fragment {
    private ListView listView = null;
    private List<MessageMenuUser> messageMenuUsers = new ArrayList<>();

    public MessageMenuFragment() {

    }

    private void initData() {
        for (int i = 0; i < 7; i++) {
            MessageMenuUser messageUser = new MessageMenuUser();
            messageUser.setIv_user_img(R.mipmap.message_official_account);
            messageUser.setTv_user_name("官方账号消息");
            messageUser.setTv_user_message("亲爱的aaa你好：...");
            messageUser.setTv_message_time("11-15");
            messageMenuUsers.add(messageUser);
        }
    }

    public static MessageMenuFragment newInstance() {
        MessageMenuFragment fragment = new MessageMenuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        NormalLog.log(this.getClass(),2,"onCreateView",0);
        View view = inflater.inflate(R.layout.fragment_message_menu,container,false);
        initData();
        MessageMenuAdapter messageMenuAdapter = new MessageMenuAdapter(messageMenuUsers, getActivity());
        listView = (ListView) view.findViewById(R.id.lv_message);
        listView.setAdapter(messageMenuAdapter);  
        return view;
    }

}
