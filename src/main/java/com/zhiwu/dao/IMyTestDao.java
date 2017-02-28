package com.zhiwu.dao;

import com.zhiwu.model.SortModel;

import java.util.List;

/**
 * Created by 韦庆明 on 2016/12/13.
 * 测试MyBatis接口类
 */
public interface IMyTestDao {

    /**
     * 根据ID获取Sort表的数据
     * */
    SortModel selectSort(int id);

    /**
     * 获取Sort表所有的数据
     * */
    List<SortModel> allSort();

    /**
     * 根据多条件条件获取Sort表的数据
     * */
    <T> List<SortModel> querySort(T model);
}
