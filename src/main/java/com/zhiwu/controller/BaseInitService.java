package com.zhiwu.controller;

import com.zhiwu.service.ISortService;
import com.zhiwu.service.ITowSortService;
import com.zhiwu.service.IUsersService;
import com.zhiwu.serviceImpl.SortServiceImpl;
import com.zhiwu.serviceImpl.TowSortServiceImpl;
import com.zhiwu.serviceImpl.UsersServiceImpl;

/**
 * Created by 韦庆明 on 2016/12/1.
 * 初始化所有Service层接口类，可以直接继承使用或实例化后使用
 */
public class BaseInitService {

    /**
     * 实例化用户相关操作接口
     */
    public IUsersService iUsersService() {
        return new UsersServiceImpl();
    }

    /**
     * 实例化用户相关操作接口
     */
    public ISortService iSortServer() {
        return new SortServiceImpl();
    }

    /**
     * 实例化用户相关操作接口
     */
    public ITowSortService iTowSortService() {
        return new TowSortServiceImpl();
    }
}
