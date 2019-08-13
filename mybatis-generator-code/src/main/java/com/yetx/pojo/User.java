package com.yetx.pojo;

import java.util.Date;

public class User {
    private String id;

    private String openid;

    private String nickname;

    private String avatar;

    private String bgimage;

    private Integer followCounts;

    private Integer fansCounts;

    private Integer collectCounts;

    private Integer likeCounts;

    private Date createTime;

    public User(String id, String openid, String nickname, String avatar, String bgimage, Integer followCounts, Integer fansCounts, Integer collectCounts, Integer likeCounts, Date createTime) {
        this.id = id;
        this.openid = openid;
        this.nickname = nickname;
        this.avatar = avatar;
        this.bgimage = bgimage;
        this.followCounts = followCounts;
        this.fansCounts = fansCounts;
        this.collectCounts = collectCounts;
        this.likeCounts = likeCounts;
        this.createTime = createTime;
    }

    public User() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getBgimage() {
        return bgimage;
    }

    public void setBgimage(String bgimage) {
        this.bgimage = bgimage == null ? null : bgimage.trim();
    }

    public Integer getFollowCounts() {
        return followCounts;
    }

    public void setFollowCounts(Integer followCounts) {
        this.followCounts = followCounts;
    }

    public Integer getFansCounts() {
        return fansCounts;
    }

    public void setFansCounts(Integer fansCounts) {
        this.fansCounts = fansCounts;
    }

    public Integer getCollectCounts() {
        return collectCounts;
    }

    public void setCollectCounts(Integer collectCounts) {
        this.collectCounts = collectCounts;
    }

    public Integer getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(Integer likeCounts) {
        this.likeCounts = likeCounts;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}