package com.six.zhihu.entity.content;

public class ContentInfo {
    private Integer id;

    private String title;

    private String nickname;

    private String signature;

    private Integer answerNum;

    private Integer agreeNum;

    private String content;

    private Integer loveNum;

    private Integer commentNum;

    private Integer collectionNum;

    public ContentInfo(Integer id, String title, String nickname, String signature,
                       Integer answerNum, Integer agreeNum, String content,
                       Integer loveNum, Integer commentNum, Integer collectionNum) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.signature = signature;
        this.answerNum = answerNum;
        this.agreeNum = agreeNum;
        this.content = content;
        this.loveNum = loveNum;
        this.commentNum = commentNum;
        this.collectionNum = collectionNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }

    public Integer getAgreeNum() {
        return agreeNum;
    }

    public void setAgreeNum(Integer agreeNum) {
        this.agreeNum = agreeNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLoveNum() {
        return loveNum;
    }

    public void setLoveNum(Integer loveNum) {
        this.loveNum = loveNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
    }

    @Override
    public String toString() {
        return "ContentInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", nickname='" + nickname + '\'' +
                ", signature='" + signature + '\'' +
                ", answerNum=" + answerNum +
                ", agreeNum=" + agreeNum +
                ", content='" + content + '\'' +
                ", loveNum=" + loveNum +
                ", commentNum=" + commentNum +
                ", collectionNum=" + collectionNum +
                '}';
    }
}
