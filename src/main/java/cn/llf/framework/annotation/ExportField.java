package cn.llf.framework.annotation;

import org.apache.poi.ss.usermodel.Cell;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段导出的配置
 *
 * @author llf
 * @since 2017/1/19
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExportField {

    String name() default "";

    /**
     * 对应的列位置，从0开始
     * @return
     */
    int position() default 0;

    int format() default Cell.CELL_TYPE_STRING;

}
