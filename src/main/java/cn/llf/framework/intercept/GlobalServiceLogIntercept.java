package cn.llf.framework.intercept;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Author：calvin
 * Date:  2017/9/3 0003
 * service层日志记录拦截器
 */
@Slf4j
public class GlobalServiceLogIntercept{
    GlobalServiceLogIntercept(){
        log.info("GlobalServiceLogIntercept实例化");
    }
//    @Before("execution(* cn.llf.framework..*.*(..))")
    public boolean preHandle(JoinPoint joinPoint) throws Exception {
        Signature sin = joinPoint.getSignature();
        log.info("方法名1：{}",sin.getName());
        MethodSignature methodSignature = (MethodSignature)sin;
        String methodName =  methodSignature.getName();
        log.info("方法名2：{}",methodName);
        Object[] objects = joinPoint.getArgs();
        for (Object object : objects){
                log.info("参数名：{}",object.toString());
        }
        log.info(">>>>>调用service之前先记录日志。。。");
        return false;
    }

    public void afterCompletion(){
        log.info(">>>>>调用service之后完成资源销毁。。。");
    }
}
