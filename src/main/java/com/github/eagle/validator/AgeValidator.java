package com.github.eagle.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Edwin Wu
 * @since 1.0.0
 */
public class AgeValidator implements ConstraintValidator<Age, Integer> { private Integer max;

    private Integer min;


    @Override
    public void initialize(Age age) {
        ConstraintValidator.super.initialize(age);
        this.max = age.max();
        this.min = age.min();
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
