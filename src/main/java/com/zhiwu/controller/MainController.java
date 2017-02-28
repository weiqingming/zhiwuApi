package com.zhiwu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 韦庆明 on 2016/11/30.
 *
 *  * 注释：
 * @Controller 采用注解的方式，明确地定义该类为处理请求的Controller类
 * @RequestMapping 用于定义一个请求映射，value为请求的url，值为 / 说明，method用以指定该请求类型get和post，produces定义请求头
 * @ResponseBody 定义直接返回数据，不定义这个就会根据返回值去找相应的JSP页面
 * @ModelAttribute 可以把POST请求所携带的json字符串自动解析成实体类，例(@ModelAttribute xxEntity name)
 * @Pathvariable 注解绑定它传过来的值到方法的参数上，参数名必须一致，例(@Pathvariable String name)
 * @RequestBody 可以把接收到的POST数据直接转换成String或一个实体类，例(@RequestBody String name)(@RequestBody xxEntity name)
 * @RequestParam 定义请求参数，用法：(@RequestParam String param1,@RequestParam String param2)
 *
 * 接口规范：
 * GET请求必须使用 @RequestParam 定义请求参数
 * POST请求必须使用 @RequestBody 定义请求参数，可以直接转换成实体类
 */

@Controller
public class MainController
{

    /* 定义一个注解，进入首页 */
    @RequestMapping(value = "/",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public Object index()
    {
        return "index";
    }

}
