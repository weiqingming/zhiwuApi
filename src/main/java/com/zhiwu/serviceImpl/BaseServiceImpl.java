package com.zhiwu.serviceImpl;

import com.zhiwu.dao.ISortDao;
import com.zhiwu.dao.ITowSortDao;
import com.zhiwu.dao.IUsersDao;
import com.zhiwu.daoImpl.SortDaoImpl;
import com.zhiwu.daoImpl.TowSortDaoImpl;
import com.zhiwu.daoImpl.UsersDaoImpl;

/**
 * Created by 韦庆明 on 2016/11/30.
 * 将dao层所有接口类初始化在此封装，逻辑层实际处理类可继承或调用此类
 */
public class BaseServiceImpl {

    /**
     * 用户信息相关操作接口类实例化
     * */
    public IUsersDao iUsersDao()
    {
        return new UsersDaoImpl();
    }

    /**
     * 一级分类相关操作接口类实例化
     * */
    public ISortDao iSortDao()
    {
        return new SortDaoImpl();
    }

    /**
     * 二级分类相关操作接口类实例化
     * */
    public ITowSortDao iTowSortDao()
    {
        return new TowSortDaoImpl();
    }
}
