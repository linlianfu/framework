package cn.llf.annotation.validator;


import cn.llf.annotation.validator.impl.TelValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 创建者：   linlf
 * 创建时间： 2017/10/27
 * 描述：校验电话号码格式
 *       11位的数字组成的电话号码
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TelValidatorImpl.class)
public @interface TelValidator{

    String message() default "电话号码格式错误";

    /**
     * 是否允许为空
     * @return
     */
    boolean allowBlank() default false;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
