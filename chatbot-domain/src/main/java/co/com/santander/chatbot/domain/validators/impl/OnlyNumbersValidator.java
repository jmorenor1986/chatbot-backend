package co.com.santander.chatbot.domain.validators.impl;

import co.com.santander.chatbot.domain.validators.OnlyNumbers;
import co.com.santander.chatbot.domain.validators.exceptions.OnlyNumbersException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Log
@Component
public class OnlyNumbersValidator implements ConstraintValidator<OnlyNumbers, Object> {

    private String message;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value instanceof String){
            try {
                Long.parseLong((String) value);
                return true;
            }catch (Exception e){}
        }else if(value instanceof Long || value instanceof Integer){
            return true;
        }
        throw new OnlyNumbersException(message);
    }

    @Override
    public void initialize(OnlyNumbers onlyNumbers) {
        message = onlyNumbers.message();
    }
}
