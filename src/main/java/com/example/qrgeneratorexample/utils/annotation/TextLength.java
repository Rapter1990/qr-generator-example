package com.example.qrgeneratorexample.utils.annotation;

import com.example.qrgeneratorexample.utils.validator.TextLengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TextLengthValidator.class)
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TextLength {
    String message() default "Invalid Text Length";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

