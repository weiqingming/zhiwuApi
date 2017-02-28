package com.zhiwu.service;

/**
 * Created by 韦庆明 on 2016/12/5.
 * 接口类统一使用泛型的方式传递参数
 */
public interface IBaseServer {
    /**
     * 固定插入表接口
     * */
    <T> Object insert(T data);

    /**
     * 固定更新表接口
     * */
    <T> Object update(T data);

    /**
     * 固定查询表接口
     * */
    <T> Object select(T data);
}
