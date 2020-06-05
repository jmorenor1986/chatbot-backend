package co.com.santander.chatbot.domain.validators.impl;

import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import co.com.santander.chatbot.domain.validators.exceptions.MandatoryFieldException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Component
public class MandatoryValidator implements ConstraintValidator<MandatoryConstraint, Object> {

    private String message;
    private boolean zeroIsValid;

    @Override
    public boolean isValid(Object s, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.nonNull(s)){
            Boolean valida = validateObject(s);
            if ( Boolean.TRUE.equals(valida) ) {
                return true;
            }
        }
        throw new MandatoryFieldException(message);
    }

    private boolean validateObject(Object s) {
        if( s instanceof String){
            Boolean valida = validaString(String.valueOf(s));
            if(Boolean.TRUE.equals(valida)){
                return true;
            }
        }else if(s instanceof Long){
            Boolean valida = validaLong( (Long) s);
            if(Boolean.TRUE.equals(valida)){
                return true;
            }
        }
        return false;
    }

    public Boolean validaString(String value){
        if( !value.isEmpty() ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean validaLong(Long value){
        if( !zeroIsValid && value.equals(0L) ){
            message = message.concat(", Zero is a invalid value");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public void initialize(MandatoryConstraint constraintAnnotation) {
        message = constraintAnnotation.message();
        zeroIsValid = constraintAnnotation.zeroIsValid();
    }
}
