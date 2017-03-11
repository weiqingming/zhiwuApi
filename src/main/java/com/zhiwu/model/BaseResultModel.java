package com.zhiwu.model;

/**
 * Created by 韦庆明 on 2016/12/1.
 * 固定的返回数据模板，请求返回统一按此模板返回数据
 */
public class BaseResultModel<T> extends BaseModel
{
    //返回状态码，参照ResultState类
    private int state;

    //数据层返回说明
    private String msg;

    //返回的数据类型
    private T body;


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
