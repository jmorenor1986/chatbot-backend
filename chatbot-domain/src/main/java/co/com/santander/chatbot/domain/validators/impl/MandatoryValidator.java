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
        String mensajeExc = message;
        if(Objects.nonNull(s)){
            if(s instanceof String){
                if( ! String.valueOf(s).isEmpty()  ){
                    return true;
                }
            }else if(s instanceof Long){
                if( !zeroIsValid ){
                    if(s.equals(0L)){
                        mensajeExc = mensajeExc.concat(", Zero is a invalid value");
                    }else{
                        return true;
                    }
                }else if( zeroIsValid ){
                    return true;
                }
            }
        }
        throw new MandatoryFieldException(mensajeExc);
    }

    @Override
    public void initialize(MandatoryConstraint constraintAnnotation) {
        message = constraintAnnotation.message();
        zeroIsValid = constraintAnnotation.zeroIsValid();
    }
}
