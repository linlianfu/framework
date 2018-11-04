package cn.llf.framework.annotation;

import java.lang.annotation.*;

/**
 * @author eleven
 * @date 2018/11/4
 * @description 方法调用统计
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInvocationStatistic {
    /**
     * 方法作用
     * @return
     */
    String methodName() default "";
    /**
     * 方法类型
     */
    int type() default 1;
}
