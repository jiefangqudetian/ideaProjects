package com.kaishengit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class AopAspect {

    @Pointcut("execution(* com.kaishengit.service..*.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void beforeAdvice(JoinPoint joinPoint){

        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName+"berore Advice");
    }

    @AfterReturning(value = "pointcut()",returning = "result")
    public void afterAdvice(JoinPoint joinPoint,Object result){
        String name = joinPoint.getSignature().getName();
        System.out.println(name+"afterReturning Advice"+result);
    }
    @AfterThrowing(value = "pointcut()",throwing = "ex")
    public void afterThrow(JoinPoint joinPoint,Exception ex){
        String name = joinPoint.getSignature().getName();
        System.out.println(name+"afterThrowing"+ex.getMessage());
    }

    @After("pointcut()")
    public void afterFinal(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println(name+"afterFinal");
    }

    @Around("pointcut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint){
        Object result = null;
        try {
            result=joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
