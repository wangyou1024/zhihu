package com.six.zhihu.entity;

import java.io.Serializable;

public class RecommendEntity implements Serializable {
    private String title;
    private String author;
    private String concern;
    private Integer header;
    private Integer image;
    private String introduce;
    private Integer agree;
    private Integer comment;

    public RecommendEntity() {
    }

    public RecommendEntity(String title, String author, String concern, Integer header, Integer image, String introduce, Integer agree, Integer comment) {
        this.title = title;
        this.author = author;
        this.concern = concern;
        this.header = header;
        this.image = image;
        this.introduce = introduce;
        this.agree = agree;
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getConcern() {
        return concern;
    }

    public void setConcern(String concern) {
        this.concern = concern;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
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

    public Integer getHeader() {
        return header;
    }

    public void setHeader(Integer header) {
        this.header = header;
    }
}
