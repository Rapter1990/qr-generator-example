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

        if(size.equals("small") || size.equals("medium") || size.equals("large")){
            return true;
        }
        return false;
    }
}
