package com.example.qrgeneratorexample.utils.validator;

import com.example.qrgeneratorexample.utils.annotation.TextLengthConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TextLengthValidator implements ConstraintValidator<TextLengthConstraint, String> {

    @Override
    public void initialize(TextLengthConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String textSize, ConstraintValidatorContext constraintValidatorContext) {
        log.info("TextLengthValidator | isValid is working");
        Integer textSizeLength = textSize.length();
        return ValidatorUtil.validTextLength(textSizeLength);
    }
}
