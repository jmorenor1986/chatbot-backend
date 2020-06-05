package co.com.santander.chatbot.domain.validators.impl;

import co.com.santander.chatbot.domain.validators.LengthValues;
import co.com.santander.chatbot.domain.validators.exceptions.LengthValuesException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class LengthValueValidator implements ConstraintValidator<LengthValues, Object> {

    private String message;
    private int min;
    private int max;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value instanceof String){
            int tamanio = String.valueOf(value).length();
            if (tamanio >= min && tamanio <= max){
                return true;
            }
        }
        throw new LengthValuesException(message);
    }

    @Override
    public void initialize(LengthValues lengthValues) {
        message = lengthValues.message();
        min = lengthValues.min();
        max = lengthValues.max();
    }
}
