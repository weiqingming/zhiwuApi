package com.zhiwu.service;

/**
 * Created by weiqingming on 2017/2/27.
 * 测试Redis缓存接口类
 */
public interface IRedisTestService {

    /**
     * 添加缓存
     * */
    void setData();

    /**
     * 读取缓存
     * */
    void getData();

    /**
     * 删除缓存
     * */
    void delData();
}
