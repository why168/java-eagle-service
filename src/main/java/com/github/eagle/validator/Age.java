package com.github.eagle.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {AgeValidator.class})
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Age {

    String message() default "年龄是非法的，最大不能超过{max}岁，最小不能小于{min}岁";

    int max() default 120;

    int min() default 1;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
