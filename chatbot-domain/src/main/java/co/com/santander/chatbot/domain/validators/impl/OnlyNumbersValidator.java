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
        if(value instanceof Long ||
                value instanceof Integer ||
                ( value instanceof String && validateString( (String) value) ) ){
            return true;
        }
        throw new OnlyNumbersException(message);
    }

    public Boolean validateString(String value){
        try {
            Long.parseLong((String) value);
            return Boolean.TRUE;
        }catch (Exception e){
            log.severe(e.getMessage());
        }
        return Boolean.FALSE;
    }

    @Override
    public void initialize(OnlyNumbers onlyNumbers) {
        message = onlyNumbers.message();
    }
}
