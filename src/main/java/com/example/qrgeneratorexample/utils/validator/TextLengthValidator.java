package com.example.qrgeneratorexample.utils.validator;

import com.example.qrgeneratorexample.utils.annotation.TextLength;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TextLengthValidator implements ConstraintValidator<TextLength, Integer> {

    @Override
    public void initialize(TextLength constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer textSize, ConstraintValidatorContext constraintValidatorContext) {

        return ValidatorUtil.validTextLength(textSize);
    }
}
