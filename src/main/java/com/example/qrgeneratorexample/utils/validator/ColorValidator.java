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

        // Only Expecting 6 Digits Hex Code
        if (color == null || !color.startsWith("#") || color.length() != 7) {
            return false;
        }

        return Pattern.compile("[a-fA-F0-9]{6}").matcher(color.substring(1)).matches();
    }
}
