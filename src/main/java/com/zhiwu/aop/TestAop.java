package com.zhiwu.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by weiqingming on 2017/3/9.
 * Aop测试切面类
 */

@Aspect
@Component
public class TestAop {


    //配置一个切入点，可以直接配置目录，也可以同时指定几个接口类
    @Pointcut("execution(* com.zhiwu.service.IRedisTestService.*(..))," +
            "execution(* com.zhiwu.service.ISortService.*(..))")


    //@Pointcut("execution(* com.zhiwu.service.IRedisTestService.*(..)) || " +
    //"execution(* com.zhiwu.service.ISortService.*(..))")
    public void aspect(){}//定义一个切入点，方便后面引入

    /**
     * 前置通知
     * */
    @Before("aspect()")
    public String doBefore(){
        System.out.println("前置通知");
        return "heheda";
    }


    /**
     * 后置通知（连接点退出后通知，方法执行完毕）
     * */
    @After("aspect()")
    public void doAfter(){
        System.out.println("后置通知");
    }

    /**
     * 返回后通知（返回后通知）
     * */
    @AfterReturning("aspect()")
    public void doAfterReturning(){
        System.out.println("返回后通知");
    }

    /**
     * 异常通知（当出现异常的时候通知）
     * */
    @AfterThrowing("aspect()")
    public void doAfterThrowing(){
        System.out.println("异常通知");
    }

    /**
     * 环绕通知，环绕通知可以使用ProceedingJoinPoint作为方法参数
     * */
    @Around("aspect()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        String str = "AAAAAA";
        Object o;
        System.out.println("进入环绕方法");
        o = point.proceed();
        System.out.println("执行第一次");
        o = point.proceed();
        System.out.println("执行第二次");

        return str;
    }

}
