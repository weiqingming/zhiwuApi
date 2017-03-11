package com.zhiwu.aop;

import com.zhiwu.global.ResultState;
import com.zhiwu.serviceImpl.VerSignsImpl;
import com.zhiwu.utils.SetResultModelUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by weiqingming on 2017/3/11.
 * 验证签名AOP切面类
 */

@Component
@Aspect
public class SignAop {

    //定义切入点
    @Pointcut("execution(* com.zhiwu.service.ISortService.*(..))")
    private void aspect(){}

    //定义一个环绕通知，用户验证签名，验证不通过则不能继续往下执行
    @Around("aspect()")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {

        //取出参数
        Object data = (String) jp.getArgs()[0];

        //调用验证签名的方法
        boolean bl = VerSignsImpl.getVerSigns().verSigns(data);
        
        //验证成功后才执行具体的方法
        if (bl){
            return jp.proceed();
        }

        return SetResultModelUtils.getResult().
                setResult(ResultState.VERSIGN).toString();

    }
}


