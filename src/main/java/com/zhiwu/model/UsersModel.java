package com.zhiwu.model;

/**
 * Created by 韦庆明 on 2016/11/30.
 */
public class UsersModel extends UsersTokenModel {

    private String userName;//用户账号
    private String passWord;//用户密码
    private String phone;//用户手机号
    private String name;//用户自定义名称
    private String gender;//用户性别
    private String age;//用户年龄
    private String headPortraitUrl;//用户头像URL

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }
}
