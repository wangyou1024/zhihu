package com.six.zhihu.entity;

import java.io.Serializable;

public class DynamicEntity implements Serializable {
    private Integer authorHeader;
    private String authorName;
    private String updateTime;
    private String title;
    private String content;
    private Integer agree;
    private Integer comment;

    public DynamicEntity() {
    }

    public DynamicEntity(Integer authorHeader, String authorName, String updateTime, String title, String content, Integer agree, Integer comment) {
        this.authorHeader = authorHeader;
        this.authorName = authorName;
        this.updateTime = updateTime;
        this.title = title;
        this.content = content;
        this.agree = agree;
        this.comment = comment;
    }

    public Integer getAuthorHeader() {
        return authorHeader;
    }

    public void setAuthorHeader(Integer authorHeader) {
        this.authorHeader = authorHeader;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAgree() {
        return agree;
    }

    public void setAgree(Integer agree) {
        this.agree = agree;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }
}
