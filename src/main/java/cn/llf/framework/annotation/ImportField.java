package cn.llf.framework.annotation;

import org.apache.poi.ss.usermodel.Cell;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 导入的配置
 *
 * @author llf
 * @since 2017/8/17
 */
@Documented
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImportField {

    String name() default "";

    /**
     * 对应的列位置，从0开始
     * @return
     */
    int position() default 0;

    int[] cellTypes() default Cell.CELL_TYPE_STRING;

}
