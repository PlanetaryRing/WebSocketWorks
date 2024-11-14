package com.agarwood.webchatapp.domain;

public class ResponseMsg {
    private String msg;
    private Integer code;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ResponseMsg{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data='" + data + '\'' +
                '}';
    }
}
