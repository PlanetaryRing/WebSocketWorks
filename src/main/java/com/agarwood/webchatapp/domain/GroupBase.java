package com.agarwood.webchatapp.domain;

public class GroupBase {
    private Long userId;
    private Integer groupId;
    private String groupName;
    private String notice;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "GroupBase{" +
                "userId=" + userId +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", notice='" + notice + '\'' +
                '}';
    }
}
