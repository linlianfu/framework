package cn.llf.annotation.validator.impl;


import cn.llf.annotation.validator.SelectRangeValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 创建者：   linlf
 * 创建时间： 2017/10/28
 * 描述：下拉框注解器的解析类
 */
public class SelectRangeValidatorImpl implements ConstraintValidator<SelectRangeValidator,Integer> {
    int min = 0;
    int max = 0;
    @Override
    public void initialize(SelectRangeValidator constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        return value >=min &&value<=max;
    }
}
