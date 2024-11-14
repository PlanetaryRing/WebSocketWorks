package com.agarwood.webchatapp.domain;

public class User {
    private Integer userId;
    private String username;
    private String cookie;
    private String headSculptureUrl;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", cookie='" + cookie + '\'' +
                ", headSculptureUrl='" + headSculptureUrl + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getHeadSculptureUrl() {
        return headSculptureUrl;
    }

    public void setHeadSculptureUrl(String headSculptureUrl) {
        this.headSculptureUrl = headSculptureUrl;
    }
}
