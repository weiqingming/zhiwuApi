package com.zhiwu.model;


/**
 * Created by 韦庆明 on 2016/12/3.
 */
public class SignPackModel extends BaseModel{
    private String signs;//签名
    private String times;//时间戳
    private Integer appId = null;//appid

    public String getSigns() {
        return signs;
    }

    public void setSigns(String signs) {
        this.signs = signs;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }
}
