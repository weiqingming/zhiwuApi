package com.zhiwu.model.receiveModel;

import com.zhiwu.model.SignPackModel;

/**
 * Created by 韦庆明 on 2016/12/4.
 */
public class LoginModel extends SignPackModel{

    private String userName;//用户账号
    private String passWord;//用户密码

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
