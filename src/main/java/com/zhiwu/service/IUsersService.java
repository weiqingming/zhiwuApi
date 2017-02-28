package com.zhiwu.service;

/**
 * Created by 韦庆明 on 2016/11/30.
 * 用户信息相关操作处理逻辑层接口类
 */
public interface IUsersService {

    /* 注册用户 */
    <T> Object registerUser (T data);

    /* 用户登录 */
    <T> Object loginUser (T data);

    /* 根据token获取用户基础信息 */
    <T> String getUsersInit (T data);
}
