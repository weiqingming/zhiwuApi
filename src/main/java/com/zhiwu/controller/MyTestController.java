package com.zhiwu.controller;

import com.zhiwu.service.IMyTestService;
import com.zhiwu.serviceImpl.MyTestImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by 韦庆明 on 2016/12/14.
 * @Controller 采用注解的方式，明确地定义该类为处理请求的Controller类
 * @RequestMapping 用于定义一个请求映射，value为请求的url，值为 / 说明，method用以指定该请求类型get和post，produces定义请求头
 * @ResponseBody 定义直接返回数据，不定义这个就会根据返回值去找相应的JSP页面
 * @ModelAttribute 可以把POST请求所携带的json字符串自动解析成实体类，例(@ModelAttribute xxEntity name)
 * @Pathvariable 注解绑定它传过来的值到方法的参数上，参数名必须一致，例(@Pathvariable String name)
 * @RequestBody 可以把接收到的POST数据直接转换成String或一个实体类，例(@RequestBody String name)(@RequestBody xxEntity name)
 * @RequestParam 定义请求参数，用法：(@RequestParam String param1,@RequestParam String param2)
 *
 * 接口规范：
 * GET请求有参数则必须使用 @RequestParam 定义请求参数
 * POST请求应尽量使用 @RequestBody 定义请求参数，可以直接转换成实体类
 * @RequestParam("signPack") = 签名相关json，例：{"signs":"MD5(EY+SECRET+时间戳)","times":"时间戳","appId":"客户端ID"}
 *
 */

@Controller
@RequestMapping("/1.0/zhiwu")
public class MyTestController {


    //使用@Resource注释从Spring容器中获取相应的对象
    @Resource(name = "myTestImpl")
    private IMyTestService myTestService;

    /* 注册用户 */
    @RequestMapping(value = "/mytest.do",
            method = RequestMethod.GET,
            produces = "application/json; encoding=UTF-8;charset=UTF-8")
    @ResponseBody
    public Object myTest(@RequestParam String data) {
        Object result= myTestService.selectSort(1);
        return result;
    }

}
