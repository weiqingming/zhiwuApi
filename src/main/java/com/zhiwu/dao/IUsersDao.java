package com.zhiwu.dao;

import java.sql.ResultSet;

/**
 * Created by 韦庆明 on 2016/11/30.
 * 用户信息相关操作处理数据层接口类
 */
public interface IUsersDao
{

    /* 注册用户 */
    ResultSet registerUser (String userName, String passWord, String phone);

    /* 用户登录
     * 数据返回字段：uid
     * */
    ResultSet loginUser (String userName,String passWord);

    /* 根据uid更新token
     * 数据返回字段：returnId，returnMsg，token
     * */
    ResultSet updateToken (int uid,String token);

    /* 根据uid获取用户基础信息
     * 数据返回字段：用户表，参照实体类
     * */
    ResultSet getUsersInit (int uid);

    /* 验证token
     * 数据返回字段：uid
     * */
    ResultSet verUsersToken (String token);

}
