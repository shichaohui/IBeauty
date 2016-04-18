package com.sch.ibeauty.entity;

/**
 * Created by shichaohui on 16/4/14.
 * <p>
 * 登录注册的返回结果
 */
public class LoginRegisterResult {

    private String access_token;
    private String refresh_token;
    private long id;
    private boolean status;
    private String msg;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
