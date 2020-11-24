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
import com.six.zhihu.entity.MessageUser;

import java.util.ArrayList;
import java.util.List;

public class DynamicFragment extends Fragment {
    private ListView listView = null;
    private List<MessageUser> messageUsers = new ArrayList<>();


    public DynamicFragment() {

    }

    private void initData() {
        for (int i = 0; i < 7; i++) {
            MessageUser messageUser = new MessageUser();
            messageUser.setMessage_user_img(R.mipmap.head_img);
            messageUser.setMessage_user_name("知乎日报");
            messageUser.setMessage_user_time_info("关注了问题 · 11-19");
            messageUser.setTv_question("如何看待北京师范大学心理学部成立个国内第一个临床与咨询学院？");
            messageUser.setTv_question_response("93回答 · 410关注");
            messageUsers.add(messageUser);
        }
    }

    public static DynamicFragment newInstance() {
        DynamicFragment fragment = new DynamicFragment();
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
        View view = inflater.inflate(R.layout.fragment_dynamic,container,false);
        initData();
        Log.i("messageUserSize", "onCreateView: "+messageUsers.size());
        MessageDynamicAdapter messageDynamicAdapter = new MessageDynamicAdapter(messageUsers, getActivity());
        listView = (ListView) view.findViewById(R.id.lv_dynamic);
        listView.setAdapter(messageDynamicAdapter);

        return view;
    }

}
