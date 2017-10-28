package cn.llf.annotation.validator.impl;

import cn.llf.annotation.validator.TelValidator;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创建者：   linlf
 * 创建时间： 2017/10/27
 * 描述：电话号码校验注解实现类
 */
public class TelValidatorImpl implements ConstraintValidator<TelValidator,String> {

    boolean allowBlank;
    @Override
    public void initialize(TelValidator constraintAnnotation) {

        this.allowBlank = constraintAnnotation.allowBlank();

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.isBlank(value)) return allowBlank;

        if (value.length() !=11){
            return false;
        }
        Pattern p  = Pattern.compile("^\\d{11}$");
        Matcher m= p.matcher(value);
        return m.matches( );
    }
}
