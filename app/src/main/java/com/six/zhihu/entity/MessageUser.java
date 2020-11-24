package com.six.zhihu.entity;

import java.io.Serializable;

/**
 * 消息发布者
 */
public class MessageUser implements Serializable {
    //消息发布者头像
    private Integer message_user_img;
    //消息发布者名字
    private String message_user_name;
    //消息发布时间
    private String message_user_time_info;
    //消息主体标题
    private String tv_question;
    //消息关注度
    private String tv_question_response;

    public MessageUser() {
    }

    public MessageUser(Integer message_user_img, String message_user_name, String message_user_time_info, String tv_question, String tv_question_response) {
        this.message_user_img = message_user_img;
        this.message_user_name = message_user_name;
        this.message_user_time_info = message_user_time_info;
        this.tv_question = tv_question;
        this.tv_question_response = tv_question_response;
    }

    public Integer getMessage_user_img() {
        return message_user_img;
    }

    public void setMessage_user_img(Integer message_user_img) {
        this.message_user_img = message_user_img;
    }

    public String getMessage_user_name() {
        return message_user_name;
    }

    public void setMessage_user_name(String message_user_name) {
        this.message_user_name = message_user_name;
    }

    public String getMessage_user_time_info() {
        return message_user_time_info;
    }

    public void setMessage_user_time_info(String message_user_time_info) {
        this.message_user_time_info = message_user_time_info;
    }

    public String getTv_question() {
        return tv_question;
    }

    public void setTv_question(String tv_question) {
        this.tv_question = tv_question;
    }

    public String getTv_question_response() {
        return tv_question_response;
    }

    public void setTv_question_response(String tv_question_response) {
        this.tv_question_response = tv_question_response;
    }
}
