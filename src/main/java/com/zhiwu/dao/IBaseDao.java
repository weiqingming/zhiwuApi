package com.zhiwu.dao;

import java.sql.ResultSet;

/**
 * Created by 韦庆明 on 2016/12/4.
 * 接口类统一使用泛型的方式传递参数
 */
public interface IBaseDao {

    /**
     * 固定插入表接口，返回ResultSet中固定包含 returnId,returnMsg 字段
     * */
    <T> ResultSet insert(T data);

    /**
     * 固定更新表接口，返回ResultSet中固定包含 returnId,returnMsg 字段
     * */
    <T> ResultSet update(T data);

    /**
     * 固定查询表接口，返回ResultSet中字段参考相应的实体类
     * */
    <T> ResultSet select(T data);
}
