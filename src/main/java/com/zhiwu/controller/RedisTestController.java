package com.zhiwu.controller;

import com.zhiwu.service.IRedisTestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by weiqingming on 2017/2/27.
 * 测试Redis接口控制层
 */
@Controller
@RequestMapping("/1.0/zhiwu")
public class RedisTestController {

    @Resource(name = "redisTestService")
    private IRedisTestService redisTestService;

    /**
     * 测试添加缓存数据
     * */
    @RequestMapping(value = "/setData.do",
    method = RequestMethod.GET,
    produces = "application/json; encoding=UTF-8;charset=UTF-8")
    public void setData(){
        redisTestService.setData();
    }

    /**
     * 测试获取缓存数据
     * */
    @RequestMapping(value = "/getData.do",
            method = RequestMethod.GET,
            produces = "application/json; encoding=UTF-8;charset=UTF-8")
    public void getData(){
        redisTestService.getData();
    }


    /**
     * 测试删除缓存数据
     * */
    @RequestMapping(value = "/delData.do",
            method = RequestMethod.GET,
            produces = "application/json; encoding=UTF-8;charset=UTF-8")
    public void delData(){
        redisTestService.delData();
    }
}
