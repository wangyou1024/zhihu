package com.six.zhihu.entity;

import java.io.Serializable;

public class HotTopEntity implements Serializable {
    private Integer grade;
    private String title;
    private String hot;
    private Integer image;

    public HotTopEntity() {
    }

    public HotTopEntity(Integer grade, String title, String hot, Integer image) {
        this.grade = grade;
        this.title = title;
        this.hot = hot;
        this.image = image;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
