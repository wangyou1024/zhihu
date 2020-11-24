package com.six.zhihu.entity;

/**
 * 消息-消息界面
 * 消息发布
 */
public class MessageMenuUser {
    //用户头像
    private Integer iv_user_img;
    //用户名
    private String tv_user_name;
    //消息内容
    private String tv_user_message;
    //收到时间
    private String tv_message_time;

    public MessageMenuUser() {
    }

    public MessageMenuUser(Integer iv_user_img, String tv_user_name, String tv_user_message, String tv_message_time) {
        this.iv_user_img = iv_user_img;
        this.tv_user_name = tv_user_name;
        this.tv_user_message = tv_user_message;
        this.tv_message_time = tv_message_time;
    }

    public Integer getIv_user_img() {
        return iv_user_img;
    }

    public void setIv_user_img(Integer iv_user_img) {
        this.iv_user_img = iv_user_img;
    }

    public String getTv_user_name() {
        return tv_user_name;
    }

    public void setTv_user_name(String tv_user_name) {
        this.tv_user_name = tv_user_name;
    }

    public String getTv_user_message() {
        return tv_user_message;
    }

    public void setTv_user_message(String tv_user_message) {
        this.tv_user_message = tv_user_message;
    }

    public String getTv_message_time() {
        return tv_message_time;
    }

    public void setTv_message_time(String tv_message_time) {
        this.tv_message_time = tv_message_time;
    }
}
