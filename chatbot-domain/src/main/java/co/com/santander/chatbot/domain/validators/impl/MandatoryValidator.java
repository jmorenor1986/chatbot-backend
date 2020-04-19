package co.com.santander.chatbot.domain.validators.impl;

import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import co.com.santander.chatbot.domain.validators.exceptions.MandatoryFieldException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class MandatoryValidator implements ConstraintValidator<MandatoryConstraint, String> {

    private int min;
    private int max;
    private String message;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (!Objects.isNull(s) && !s.isEmpty())
            if (s.length() >= min && s.length() <= max)
                return true;
        throw new MandatoryFieldException(message);
    }

    @Override
    public void initialize(MandatoryConstraint constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
        message = constraintAnnotation.message();
    }
}
