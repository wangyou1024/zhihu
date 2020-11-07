package com.six.zhihu.entity;

import java.io.Serializable;

public class ConcernPerson implements Serializable {
    private Integer imageHeader;
    private String name;

    public ConcernPerson() {
    }

    public ConcernPerson(Integer imageHeader, String name) {
        this.imageHeader = imageHeader;
        this.name = name;
    }

    public Integer getImageHeader() {
        return imageHeader;
    }

    public void setImageHeader(Integer imageHeader) {
        this.imageHeader = imageHeader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
