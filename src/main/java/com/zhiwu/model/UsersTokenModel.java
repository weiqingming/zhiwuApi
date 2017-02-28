package com.zhiwu.model;

/**
 * Created by 韦庆明 on 2016/11/30.
 */
public class UsersTokenModel extends SignPackModel{
    private String token;//用户token
    private String endDate;//有效期截止时间

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
