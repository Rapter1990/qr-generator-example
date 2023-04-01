package com.example.qrgeneratorexample.utils.validator;

import com.example.qrgeneratorexample.utils.annotation.ImageSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImageSizeValidator implements ConstraintValidator<ImageSize, String> {


    @Override
    public void initialize(ImageSize constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String size, ConstraintValidatorContext constraintValidatorContext) {

        return ValidatorUtil.validImageSize(size);

    }
}
