package com.example.qrgeneratorexample.utils.annotation;

import com.example.qrgeneratorexample.utils.validator.ColorValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ColorValidator.class)
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Color {
    String message() default "Invalid color hex code";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
