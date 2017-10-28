package cn.llf.annotation.validator;


import cn.llf.annotation.validator.impl.SelectRangeValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 创建者：   linlf
 * 创建时间： 2017/10/28
 * 描述：校验前端上传的下拉框，复选框，单选框的value的值是否在给定的范围内
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = SelectRangeValidatorImpl.class)
public @interface SelectRangeValidator {

    String message() default "属性值不在给定范围内";

    int min() default 0;

    int max() default 0;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
