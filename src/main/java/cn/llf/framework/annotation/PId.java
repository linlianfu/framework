package cn.llf.framework.annotation;

import java.lang.annotation.*;

/**
 * @author: eleven
 * @since:  2017/8/19 0019
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PId {
    String name() default "";
}
