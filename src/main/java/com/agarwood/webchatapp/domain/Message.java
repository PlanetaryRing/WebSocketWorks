package com.agarwood.webchatapp.domain;


public class Message {
//    SQL:SELECT msg.user_id AS user_id,username,head_sculpture_url,group_id,type,content,create_time FROM msg JOIN `user` ON user.user_id=msg.user_id ORDER BY create_time LIMIT 20;
    private Long userId;// 发送者的ID
    private String username;// 发送者的姓名
    private String headSculptureUrl;// 发送者的头像
    private Integer groupId;// 消息所属的群组
    private String type;// 消息的类型
    private String content;// 消息的内容
    private String createTime;// 发送的时间

    public String getHeadSculptureUrl() {
        return headSculptureUrl;
    }

    public void setHeadSculptureUrl(String headSculptureUrl) {
        this.headSculptureUrl = headSculptureUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", groupId=" + groupId +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
