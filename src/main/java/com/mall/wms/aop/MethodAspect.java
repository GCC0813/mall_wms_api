package com.mall.wms.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author GCC
 * create on 2020/6/12 16:51
 */
@Aspect
@Component
public class MethodAspect implements HandlerInterceptor {

    @Pointcut("execution( * com.mall.wms.controller..*.*(..))")
    public void  methodInAndOut(){
    }

    @Before("methodInAndOut()")
    public void doBefore(JoinPoint joinPoint){
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Class<?> targetClass = method.getDeclaringClass();
            StringBuffer classAndMethod = new StringBuffer();

            System.out.println(targetClass.getName());
            System.out.println(System.currentTimeMillis());
            System.out.println(Arrays.toString(joinPoint.getArgs()));
        } catch (Throwable throwable) {
        }
    }

    @AfterReturning(returning = "ret", pointcut = "methodInAndOut()")
    public void doAfterReturning(Object ret) throws Throwable {
        System.out.println(ret);
    }
}
