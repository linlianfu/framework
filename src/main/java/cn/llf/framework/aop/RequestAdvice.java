package cn.llf.framework.aop;

import cn.llf.framework.annotation.MethodInvocationStatistic;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author eleven
 * @date 2018/11/4
 * @description AOP
 */
@Slf4j
@Aspect
public class RequestAdvice {

    public RequestAdvice(){
        log.warn("实例化");
    }

    /**
     * 定义一个通用的切点，解决切点复用问题
     */
    @Pointcut("execution(* cn.llf.framework.services..*.*(..))")
    public void pointcut(){}

    /**
     * 直接在通知方法定义切点
     * @param joinPoint
     */
    @Before("pointcut()")
    public void before(JoinPoint joinPoint){

        log.warn(">>>>>前置通知");
    }

    /**
     * 引用定义好的切点
     * @param joinPoint
     */
    @After("pointcut()")
    public void after(JoinPoint joinPoint){
        log.warn(">>>>>后置通知");
    }

    /**
     * 切面范围为有使用 {@link MethodInvocationStatistic}的方法
     * @param joinPoint
     */
    @Before("@annotation(cn.llf.framework.annotation.MethodInvocationStatistic)")
    public void actionRecord(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        log.info("标有注解的【{}】的方法被AOP切面切到，方法签名：【{}】", MethodInvocationStatistic.class.getName(),signature);
    }
}
