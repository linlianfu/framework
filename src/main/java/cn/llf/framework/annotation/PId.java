package cn.llf.framework.annotation;

import java.lang.annotation.*;

/**
 * Author：calvin
 * Date:  2017/8/19 0019
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PId {
    String name() default "";
}
