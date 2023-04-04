package com.example.qrgeneratorexample.utils.validator;

import com.example.qrgeneratorexample.utils.annotation.ImageSizeConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ImageSizeValidator implements ConstraintValidator<ImageSizeConstraint, String> {


    @Override
    public void initialize(ImageSizeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String size, ConstraintValidatorContext constraintValidatorContext) {
        log.info("ImageSizeValidator | isValid is working");
        return ValidatorUtil.validImageSize(size);

    }
}
