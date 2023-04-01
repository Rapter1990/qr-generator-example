package com.example.qrgeneratorexample.utils.validator;

import com.example.qrgeneratorexample.utils.annotation.Color;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

public class ColorValidator implements ConstraintValidator<Color, String> {

    @Override
    public void initialize(Color constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String color, ConstraintValidatorContext constraintValidatorContext) {

        return ValidatorUtil.validColor(color);
    }
}
