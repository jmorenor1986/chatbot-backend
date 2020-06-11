package co.com.santander.chatbot.domain.validators.impl;

import co.com.santander.chatbot.domain.validators.AllowedValues;
import co.com.santander.chatbot.domain.validators.exceptions.AllowedValuesException;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class AllowedValuesValidator implements ConstraintValidator<AllowedValues, Object> {

    private String message;
    @Setter
    private String[] stringValues;
    @Setter
    private long[] longValues;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value instanceof String){
            Boolean valid = validStringValues( String.valueOf(value) );
            if(Boolean.TRUE.equals(valid)){
                return true;
            }
        }else if(value instanceof Long){
            Boolean valid = validLongValues((Long) value);
            if(Boolean.TRUE.equals(valid)){
                return true;
            }
        }
        throw new AllowedValuesException(message);
    }

    private boolean validLongValues(Long value){
        //Iteramos los valores permitidos
        for(Long allowed : longValues){
            if(allowed.equals(value)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private boolean validStringValues(String value){
        //Iteramos los valores permitidos
        for(String allowed : stringValues){
            if(allowed.equals(value)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public void initialize(AllowedValues allowedValues) {
        message = allowedValues.message();
        stringValues = allowedValues.stringValues();
        longValues = allowedValues.longValues();
    }
}
