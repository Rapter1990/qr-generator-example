package com.example.qrgeneratorexample.utils.annotation;

import com.example.qrgeneratorexample.utils.validator.ImageSizeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ImageSizeValidator.class)
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageSize {
    String message() default "Invalid Size Value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
