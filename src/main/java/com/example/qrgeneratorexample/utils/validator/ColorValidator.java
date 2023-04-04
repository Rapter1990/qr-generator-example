package com.example.qrgeneratorexample.utils.validator;

import com.example.qrgeneratorexample.utils.annotation.ColorConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Slf4j
@Component
public class ColorValidator implements ConstraintValidator<ColorConstraint, String> {

    @Override
    public void initialize(ColorConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String color, ConstraintValidatorContext constraintValidatorContext) {
        log.info("ColorValidator | isValid is working");
        return ValidatorUtil.validColor(color);
    }
}
